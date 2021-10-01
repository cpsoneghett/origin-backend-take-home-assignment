package com.origin.challenge.api.service.impl;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.origin.challenge.api.dto.request.RequestDto;
import com.origin.challenge.api.dto.response.ResponseDto;
import com.origin.challenge.api.enums.MaritalStatus;
import com.origin.challenge.api.enums.OwnershipStatus;
import com.origin.challenge.api.enums.ScoreEvaluation;
import com.origin.challenge.api.service.InsuranceRiskService;

@Service
public class InsuranceRiskServiceImpl implements InsuranceRiskService {

	private static final Logger log = LoggerFactory.getLogger(InsuranceRiskServiceImpl.class);

	@Value("${max.eligible.age}")
	private Integer maxEligibleAge;

	@Value("${min.income.reduce.risk}")
	private Double minimumIncomeToReduceRisk;

	public ResponseDto calculateInsuranceRisk(RequestDto input) {

		log.info("Initiating the Insurance Risk Calculation for input: {}", input.toString());
		ResponseDto response = new ResponseDto();

		calculateBaseScore(input, response);
		checkIneligibleParameters(input, response); // 1, 2
		calculateRiskPointsByAge(input, response); // 3
		calculateRiskPointsByIncome(input, response); // 4
		calculateRiskPointsByHouse(input, response); // 5
		calculateRiskPointsByDependents(input, response); // 6
		calculateRiskPointsByMaritalStatus(input, response); // 7
		calculateRiskPointsByVehicle(input, response); // 8

		defineScoreEvaluation(response);

		return response;
	}

	private void calculateBaseScore(RequestDto input, ResponseDto response) {

		int sum = 0;
		for (Integer i : input.getRiskQuestions())
			sum += i;
		response.addScore(sum, sum, sum, sum);
	}

	private void checkIneligibleParameters(RequestDto input, ResponseDto response) {

		if (input.getIncome() == 0)
			response.setDisability(ScoreEvaluation.INELIGIBLE);
		if (input.getHouse() == null)
			response.setHome(ScoreEvaluation.INELIGIBLE);
		if (input.getVehicle() == null)
			response.setAuto(ScoreEvaluation.INELIGIBLE);

		if (input.getAge() > maxEligibleAge) {
			response.setDisability(ScoreEvaluation.INELIGIBLE);
			response.setLife(ScoreEvaluation.INELIGIBLE);
		}
	}

	private void calculateRiskPointsByAge(RequestDto input, ResponseDto response) {

		if (input.getAge() < 30)
			response.addScore(-2, -2, -2, -2);
		else if (input.getAge() >= 30 && input.getAge() <= 40)
			response.addScore(-1, -1, -1, -1);
	}

	private void calculateRiskPointsByIncome(RequestDto input, ResponseDto response) {

		if (input.getIncome() > minimumIncomeToReduceRisk)
			response.addScore(-1, -1, -1, -1);
	}

	private void calculateRiskPointsByHouse(RequestDto input, ResponseDto response) {

		if (input.getHouse() != null && input.getHouse().getOwnershipStatus().equals(OwnershipStatus.MORTGAGED))
			response.addScore(0, 1, 1, 0);
	}

	private void calculateRiskPointsByDependents(RequestDto input, ResponseDto response) {

		if (input.getDependents() > 0)
			response.addScore(0, 1, 0, 1);
	}

	private void calculateRiskPointsByMaritalStatus(RequestDto input, ResponseDto response) {

		if (input.getMaritalStatus() != null && input.getMaritalStatus().equals(MaritalStatus.MARIED))
			response.addScore(0, -1, 0, 1);
	}

	private void calculateRiskPointsByVehicle(RequestDto input, ResponseDto response) {

		if (input.getVehicle() != null && input.getVehicle().getYear() >= Calendar.getInstance().get(Calendar.YEAR) - 5)
			response.addScore(1, 0, 0, 0);
	}

	private void defineScoreEvaluation(ResponseDto dto) {

		dto.evaluateAutoScore();

		dto.evaluateDisabilityScore();

		dto.evaluateHomeScore();

		dto.evaluateLifeScore();
	}
}

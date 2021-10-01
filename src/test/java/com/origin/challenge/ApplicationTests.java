package com.origin.challenge;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.origin.challenge.api.dto.HouseDto;
import com.origin.challenge.api.dto.VehicleDto;
import com.origin.challenge.api.dto.request.RequestDto;
import com.origin.challenge.api.dto.response.ResponseDto;
import com.origin.challenge.api.enums.MaritalStatus;
import com.origin.challenge.api.enums.OwnershipStatus;
import com.origin.challenge.api.enums.ScoreEvaluation;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApplicationTests {

	@LocalServerPort
	private int randomServerPort;

	@Test
	void testInsuranceRiskForClientWithAllStatsIneligible() throws URISyntaxException {

		/* given a user with no income, vehicles or houses */
		RequestDto user = mockUserWithNoPosessionsAndOverMaxEligibleRate();

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/insurance-risk/calc";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<RequestDto> request = new HttpEntity<>(user, headers);

		/* when consuming calculation api of this user */
		ResponseEntity<ResponseDto> result = restTemplate.postForEntity(uri, request, ResponseDto.class);

		/*
		 * then the client is ineligible for disability, auto, and home insurance,
		 * respectively.
		 */
		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertEquals(ScoreEvaluation.INELIGIBLE, result.getBody().getAuto());
		Assertions.assertEquals(ScoreEvaluation.INELIGIBLE, result.getBody().getHome());
		Assertions.assertEquals(ScoreEvaluation.INELIGIBLE, result.getBody().getDisability());
		Assertions.assertEquals(ScoreEvaluation.INELIGIBLE, result.getBody().getLife());
	}

	@Test
	void testInsuranceRiskForClientWithAllRegularStats() throws URISyntaxException {

		/* given a user with no income, vehicles or houses */
		RequestDto user = mockUserWithRegularProfile();

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/insurance-risk/calc";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<RequestDto> request = new HttpEntity<>(user, headers);

		/* when consuming calculation api of this user */
		ResponseEntity<ResponseDto> result = restTemplate.postForEntity(uri, request, ResponseDto.class);

		/*
		 * then the client is ineligible for disability, auto, and home insurance,
		 * respectively.
		 */
		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertEquals(ScoreEvaluation.REGULAR, result.getBody().getAuto());
		Assertions.assertEquals(ScoreEvaluation.REGULAR, result.getBody().getHome());
		Assertions.assertEquals(ScoreEvaluation.REGULAR, result.getBody().getDisability());
		Assertions.assertEquals(ScoreEvaluation.REGULAR, result.getBody().getLife());
	}

	@Test
	void testInsuranceRiskForClientWithAllEconomicStats() throws URISyntaxException {

		/* given a user with no income, vehicles or houses */
		RequestDto user = mockUserWithEconomicProfile();

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/insurance-risk/calc";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<RequestDto> request = new HttpEntity<>(user, headers);

		/* when consuming calculation api of this user */
		ResponseEntity<ResponseDto> result = restTemplate.postForEntity(uri, request, ResponseDto.class);

		/*
		 * then the client is ineligible for disability, auto, and home insurance,
		 * respectively.
		 */
		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertEquals(ScoreEvaluation.ECONOMIC, result.getBody().getAuto());
		Assertions.assertEquals(ScoreEvaluation.ECONOMIC, result.getBody().getHome());
		Assertions.assertEquals(ScoreEvaluation.ECONOMIC, result.getBody().getDisability());
		Assertions.assertEquals(ScoreEvaluation.ECONOMIC, result.getBody().getLife());
	}
	
	@Test
	void testInsuranceRiskForClientWithAllResponsibleStats() throws URISyntaxException {

		/* given a user with no income, vehicles or houses */
		RequestDto user = mockUserWithResponsibleProfile();

		RestTemplate restTemplate = new RestTemplate();
		final String baseUrl = "http://localhost:" + randomServerPort + "/insurance-risk/calc";
		URI uri = new URI(baseUrl);

		HttpHeaders headers = new HttpHeaders();
		headers.set("X-COM-PERSIST", "true");

		HttpEntity<RequestDto> request = new HttpEntity<>(user, headers);

		/* when consuming calculation api of this user */
		ResponseEntity<ResponseDto> result = restTemplate.postForEntity(uri, request, ResponseDto.class);

		/*
		 * then the client is ineligible for disability, auto, and home insurance,
		 * respectively.
		 */
		Assertions.assertEquals(200, result.getStatusCodeValue());
		Assertions.assertEquals(ScoreEvaluation.RESPONSIBLE, result.getBody().getAuto());
		Assertions.assertEquals(ScoreEvaluation.RESPONSIBLE, result.getBody().getHome());
		Assertions.assertEquals(ScoreEvaluation.RESPONSIBLE, result.getBody().getDisability());
		Assertions.assertEquals(ScoreEvaluation.RESPONSIBLE, result.getBody().getLife());
	}

	/**
	 * Mocking user that has no house, income or vehicle
	 */
	private RequestDto mockUserWithNoPosessionsAndOverMaxEligibleRate() {
		RequestDto dto = new RequestDto();

		dto.setAge(61);
		dto.setDependents(Mockito.anyInt());
		dto.setHouse(null);
		dto.setIncome(0d);
		dto.setMaritalStatus(MaritalStatus.SINGLE);
		dto.setRiskQuestions(List.of(0, 1, 0));
		dto.setVehicle(null);

		return dto;
	}

	/**
	 * Mocking user with mortgaged house, old vehicle, under 30 and no income
	 */
	private RequestDto mockUserWithRegularProfile() {
		RequestDto dto = new RequestDto();

		dto.setAge(29);
		dto.setDependents(0);
		dto.setHouse(new HouseDto(OwnershipStatus.MORTGAGED));
		dto.setIncome(1000d);
		dto.setMaritalStatus(MaritalStatus.SINGLE);
		dto.setRiskQuestions(List.of(1, 1, 1));
		dto.setVehicle(new VehicleDto(2000));

		return dto;
	}

	/**
	 * Mocking user with data that defines all scores as ECONOMIC
	 */
	private RequestDto mockUserWithEconomicProfile() {
		RequestDto dto = new RequestDto();

		dto.setAge(35);
		dto.setDependents(0);
		dto.setHouse(new HouseDto(OwnershipStatus.OWNED));
		dto.setIncome(300000d);
		dto.setMaritalStatus(MaritalStatus.MARIED);
		dto.setRiskQuestions(List.of(0, 0, 0));
		dto.setVehicle(new VehicleDto(2000));

		return dto;
	}
	
	/**
	 * Mocking user with data that defines all scores as ECONOMIC
	 */
	private RequestDto mockUserWithResponsibleProfile() {
		RequestDto dto = new RequestDto();

		dto.setAge(50);
		dto.setDependents(3);
		dto.setHouse(new HouseDto(OwnershipStatus.OWNED));
		dto.setIncome(1000d);
		dto.setMaritalStatus(MaritalStatus.SINGLE);
		dto.setRiskQuestions(List.of(1, 1, 1));
		dto.setVehicle(new VehicleDto(2020));

		return dto;
	}
}

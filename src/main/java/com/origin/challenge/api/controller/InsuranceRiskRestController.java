package com.origin.challenge.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.origin.challenge.api.dto.request.RequestDto;
import com.origin.challenge.api.dto.response.ResponseDto;
import com.origin.challenge.api.service.impl.InsuranceRiskServiceImpl;

@RestController
@RequestMapping("/insurance-risk")
public class InsuranceRiskRestController {

	@Autowired
	private InsuranceRiskServiceImpl insuranceRiskService;

	@PostMapping("/calc")
	public ResponseEntity<ResponseDto> calculateInsuranceRisk(@Valid @RequestBody RequestDto input) {

		return ResponseEntity.status(HttpStatus.OK).body(insuranceRiskService.calculateInsuranceRisk(input));
	}

}

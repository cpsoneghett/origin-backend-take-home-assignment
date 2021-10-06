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

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/insurance-risk")
public class InsuranceRiskRestController {

	@Autowired
	private InsuranceRiskServiceImpl insuranceRiskService;

	@PostMapping("/calc")
	@ApiOperation(value = "Get the insurance risk information of a User")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Insurance risk calculated successfully!"),
			@ApiResponse(code = 400, message = "Bad request") })
	public ResponseEntity<ResponseDto> calculateInsuranceRisk(@Valid @RequestBody RequestDto input) {

		return ResponseEntity.status(HttpStatus.OK).body(insuranceRiskService.calculateInsuranceRisk(input));
	}

}

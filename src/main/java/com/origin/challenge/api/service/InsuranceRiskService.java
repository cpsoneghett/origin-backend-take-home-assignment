package com.origin.challenge.api.service;

import com.origin.challenge.api.dto.request.RequestDto;
import com.origin.challenge.api.dto.response.ResponseDto;

public interface InsuranceRiskService {

	public ResponseDto calculateInsuranceRisk(RequestDto input);
}

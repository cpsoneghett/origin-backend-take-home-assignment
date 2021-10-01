package com.origin.challenge.api.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ScoreEvaluation {

	ECONOMIC("owned"), REGULAR("regular"), RESPONSIBLE("responsible"), INELIGIBLE("ineligible");

	private String evaluation;

	ScoreEvaluation(String status) {
		this.evaluation = status;
	}

	@JsonValue
	public String getEvaluation() {
		return this.evaluation;
	}
}

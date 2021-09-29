package com.origin.challenge.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
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

	@JsonCreator
	public static ScoreEvaluation fromInput(String input) {

		for (ScoreEvaluation se : values()) {
			if (se.getEvaluation().equals(input))
				return se;
		}

		throw new IllegalArgumentException(
				"It was not possible convert to class " + ScoreEvaluation.class + " from input string " + input);

	}

	@Override
	public String toString() {
		return this.evaluation;
	}
}

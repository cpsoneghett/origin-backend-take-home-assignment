package com.origin.challenge.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum MaritalStatus {

	SINGLE("single"), MARIED("married");

	private String status;

	MaritalStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	@JsonCreator
	public static MaritalStatus fromInput(String input) {

		for (MaritalStatus ms : values()) {
			if (ms.getStatus().equals(input))
				return ms;
		}

		throw new IllegalArgumentException(
				"It was not possible convert to class " + MaritalStatus.class + " from input string " + input);
	}

	@Override
	public String toString() {
		return this.status;
	}
}

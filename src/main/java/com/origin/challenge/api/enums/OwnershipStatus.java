package com.origin.challenge.api.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum OwnershipStatus {

	OWNED("owned"), MORTGAGED("mortgaged");

	private String status;

	OwnershipStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return this.status;
	}

	@JsonCreator
	public static OwnershipStatus fromInput(String input) {

		for (OwnershipStatus os : values()) {
			if (os.getStatus().equals(input))
				return os;
		}

		throw new IllegalArgumentException(
				"It was not possible convert to class " + OwnershipStatus.class + " from input string " + input);

	}

	@Override
	public String toString() {
		return this.status;
	}
}
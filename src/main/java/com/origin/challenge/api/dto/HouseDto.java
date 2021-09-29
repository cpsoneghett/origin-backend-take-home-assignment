package com.origin.challenge.api.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.origin.challenge.api.enums.OwnershipStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HouseDto {

	@NotNull
	@JsonProperty("ownership_status")
	private OwnershipStatus ownershipStatus;

	public OwnershipStatus getOwnershipStatus() {
		return ownershipStatus;
	}

	public void setOwnershipStatus(OwnershipStatus ownershipStatus) {
		this.ownershipStatus = ownershipStatus;
	}

}

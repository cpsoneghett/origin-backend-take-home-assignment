package com.origin.challenge.api.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VehicleDto {

	@NotNull
	private Integer year;

	@JsonCreator
	public VehicleDto(@NotNull Integer year) {
		this.year = year;
	}

	public Integer getYear() {
		return year;
	}

	@Override
	public String toString() {
		return "VehicleDto [year=" + year + "]";
	}

}

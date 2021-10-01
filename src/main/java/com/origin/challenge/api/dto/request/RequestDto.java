package com.origin.challenge.api.dto.request;

import java.util.List;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.origin.challenge.api.dto.HouseDto;
import com.origin.challenge.api.dto.VehicleDto;
import com.origin.challenge.api.enums.MaritalStatus;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDto {

	@Min(value = 0, message = "Age should not be less than zero")
	private Integer age;

	@Min(value = 0, message = "Dependents should not be less than zero")
	private Integer dependents;

	private HouseDto house;

	@Min(value = 0, message = "Incole should not be less than zero")
	private Double income;

	@JsonProperty("marital_status")
	private MaritalStatus maritalStatus;

	@JsonProperty("risk_questions")
	private List<Integer> riskQuestions;

	private VehicleDto vehicle;

	public Integer getAge() {
		return age;
	}

	public Integer getDependents() {
		return dependents;
	}

	public HouseDto getHouse() {
		return house;
	}

	public Double getIncome() {
		return income;
	}

	public MaritalStatus getMaritalStatus() {
		return maritalStatus;
	}

	public List<Integer> getRiskQuestions() {
		return riskQuestions;
	}

	public VehicleDto getVehicle() {
		return vehicle;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public void setDependents(Integer dependents) {
		this.dependents = dependents;
	}

	public void setHouse(HouseDto house) {
		this.house = house;
	}

	public void setIncome(Double income) {
		this.income = income;
	}

	public void setMaritalStatus(MaritalStatus maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public void setRiskQuestions(List<Integer> riskQuestions) {
		this.riskQuestions = riskQuestions;
	}

	public void setVehicle(VehicleDto vehicle) {
		this.vehicle = vehicle;
	}

	@Override
	public String toString() {
		return "RequestDto [age=" + age + ", dependents=" + dependents + ", house=" + house + ", income=" + income
				+ ", maritalStatus=" + maritalStatus + ", riskQuestions=" + riskQuestions
				+ ", vehicle year=" + vehicle + "]";
	}

}

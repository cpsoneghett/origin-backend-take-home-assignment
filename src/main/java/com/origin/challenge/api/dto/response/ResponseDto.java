package com.origin.challenge.api.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.origin.challenge.api.enums.ScoreEvaluation;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDto {

	@JsonIgnore
	private Integer autoRiskBaseScore;
	@JsonIgnore
	private Integer disabilityRiskBaseScore;
	@JsonIgnore
	private Integer homeRiskBaseScore;
	@JsonIgnore
	private Integer lifeRiskBaseScore;

	private ScoreEvaluation auto;
	private ScoreEvaluation disability;
	private ScoreEvaluation home;
	private ScoreEvaluation life;

	public ResponseDto() {
		this.autoRiskBaseScore = 0;
		this.disabilityRiskBaseScore = 0;
		this.homeRiskBaseScore = 0;
		this.lifeRiskBaseScore = 0;
	}

	public ScoreEvaluation getAuto() {
		return auto;
	}

	public void setAuto(ScoreEvaluation auto) {
		this.auto = auto;
	}

	public ScoreEvaluation getDisability() {
		return disability;
	}

	public void setDisability(ScoreEvaluation disability) {
		this.disability = disability;
	}

	public ScoreEvaluation getHome() {
		return home;
	}

	public void setHome(ScoreEvaluation home) {
		this.home = home;
	}

	public ScoreEvaluation getLife() {
		return life;
	}

	public void setLife(ScoreEvaluation life) {
		this.life = life;
	}

	public void addScore(Integer autoRiskScore, Integer disabilityRiskScore, Integer homeRiskScore,
			Integer lifeRiskScore) {

		this.autoRiskBaseScore += autoRiskScore;
		this.disabilityRiskBaseScore += disabilityRiskScore;
		this.homeRiskBaseScore += homeRiskScore;
		this.lifeRiskBaseScore += lifeRiskScore;
	}

	public void evaluateAutoScore() {

		if (!ScoreEvaluation.INELIGIBLE.equals(this.auto)) {

			if (this.autoRiskBaseScore <= 0)
				this.auto = ScoreEvaluation.ECONOMIC;
			else if (this.autoRiskBaseScore >= 1 && this.autoRiskBaseScore <= 2)
				this.auto = ScoreEvaluation.REGULAR;
			else
				this.auto = ScoreEvaluation.RESPONSIBLE;
		}
	}

	public void evaluateDisabilityScore() {

		if (!ScoreEvaluation.INELIGIBLE.equals(this.disability)) {

			if (this.disabilityRiskBaseScore <= 0)
				this.disability = ScoreEvaluation.ECONOMIC;
			else if (this.disabilityRiskBaseScore >= 1 && this.disabilityRiskBaseScore <= 2)
				this.disability = ScoreEvaluation.REGULAR;
			else
				this.disability = ScoreEvaluation.RESPONSIBLE;
		}
	}

	public void evaluateHomeScore() {

		if (!ScoreEvaluation.INELIGIBLE.equals(this.home)) {

			if (this.homeRiskBaseScore <= 0)
				this.home = ScoreEvaluation.ECONOMIC;
			else if (this.homeRiskBaseScore >= 1 && this.homeRiskBaseScore <= 2)
				this.home = ScoreEvaluation.REGULAR;
			else
				this.home = ScoreEvaluation.RESPONSIBLE;
		}
	}

	public void evaluateLifeScore() {

		if (!ScoreEvaluation.INELIGIBLE.equals(this.life)) {
			if (this.lifeRiskBaseScore <= 0)
				this.life = ScoreEvaluation.ECONOMIC;
			else if (this.lifeRiskBaseScore >= 1 && this.lifeRiskBaseScore <= 2)
				this.life = ScoreEvaluation.REGULAR;
			else
				this.life = ScoreEvaluation.RESPONSIBLE;
		}

	}

}

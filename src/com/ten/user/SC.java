package com.ten.user;


public class SC {
	private int SNo;
	private int lessonNo;

	private float dailyScore;

	private float middleScore;

	private float finalScore;

	private float totalScore;

	/**
	 * @param SNo
	 * @param lessonNo
	 * @param dailyScore
	 * @param middleScore
	 * @param finalScore
	 * @param totalScore
	 */
	public SC(int SNo, int lessonNo, float dailyScore, float middleScore, float finalScore, float totalScore) {
		this.SNo = SNo;
		this.lessonNo = lessonNo;
		this.dailyScore = dailyScore;
		this.middleScore = middleScore;
		this.finalScore = finalScore;
		this.totalScore = totalScore;
	}

	public SC(int SNo, int lessonNo) {
		this.SNo = SNo;
		this.lessonNo = lessonNo;
	}

	public SC() {
	}

	public int getSNo() {
		return SNo;
	}

	public void setSNo(int SNo) {
		this.SNo = SNo;
	}

	public int getLessonNo() {
		return lessonNo;
	}

	public void setLessonNo(int lessonNo) {
		this.lessonNo = lessonNo;
	}

	public float getDailyScore() {
		return dailyScore;
	}

	public void setDailyScore(float dailyScore) {
		this.dailyScore = dailyScore;
	}

	public float getMiddleScore() {
		return middleScore;
	}

	public void setMiddleScore(float middleScore) {
		this.middleScore = middleScore;
	}

	public float getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(float finalScore) {
		this.finalScore = finalScore;
	}

	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}
}

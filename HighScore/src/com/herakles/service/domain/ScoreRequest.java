package com.herakles.service.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScoreRequest implements Comparable<ScoreRequest>{
	private String	playerOne, playerTwo;
	private int		scoreOne, scoreTwo;
	
	public String getPlayerOne() {
		return playerOne;
	}
	public void setPlayerOne(String playerOne) {
		this.playerOne = playerOne;
	}
	public String getPlayerTwo() {
		return playerTwo;
	}
	public void setPlayerTwo(String playerTwo) {
		this.playerTwo = playerTwo;
	}
	public int getScoreOne() {
		return scoreOne;
	}
	public void setScoreOne(int scoreOne) {
		this.scoreOne = scoreOne;
	}
	public int getScoreTwo() {
		return scoreTwo;
	}
	public void setScoreTwo(int scoreTwo) {
		this.scoreTwo = scoreTwo;
	}

	@Override
	public String toString() {
		return "ScoreRequest [playerOne=" + playerOne + ", playerTwo="
				+ playerTwo + ", scoreOne=" + scoreOne + ", scoreTwo="
				+ scoreTwo + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((playerOne == null) ? 0 : playerOne.hashCode());
		result = prime * result
				+ ((playerTwo == null) ? 0 : playerTwo.hashCode());
		result = prime * result + scoreOne;
		result = prime * result + scoreTwo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ScoreRequest other = (ScoreRequest) obj;
		if (playerOne == null) {
			if (other.playerOne != null)
				return false;
		} else if (!playerOne.equals(other.playerOne))
			return false;
		if (playerTwo == null) {
			if (other.playerTwo != null)
				return false;
		} else if (!playerTwo.equals(other.playerTwo))
			return false;
		if (scoreOne != other.scoreOne)
			return false;
		if (scoreTwo != other.scoreTwo)
			return false;
		return true;
	}
	
	public int compareTo(ScoreRequest other) {
		if (this == other)
			return 0;
		if (other == null)
			return -1;
		
		if ((scoreOne>other.scoreOne)&& (scoreTwo>other.scoreTwo)) return 1;
		if ((scoreOne<other.scoreOne)&& (scoreTwo<other.scoreTwo)) return -1;

		if ((scoreOne+scoreTwo) > (other.scoreOne+other.scoreTwo))
			return 1;
		else if ((scoreOne+scoreTwo) < (other.scoreOne+other.scoreTwo))
			return -1;
		else
			return 0;	
	}
}

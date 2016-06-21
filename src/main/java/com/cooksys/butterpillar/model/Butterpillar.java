package com.cooksys.butterpillar.model;

public class Butterpillar implements IButterpillar {

	private double leavesEaten;
	private double length;

	@Override
	public int compareTo(IButterpillar o) {
		if((length > o.getLength()) || ((o.getLength() == length) && (leavesEaten > o.getLeavesEaten()))) {
			return 1;
		} else if((length == o.getLength()) &&(leavesEaten == o.getLeavesEaten())) {
			return 0;
		} else {
			return -1;
		}
	}

	@Override
	public void setLength(double length) {
		this.length = length;
	}

	@Override
	public double getLength() {
		return length;
	}

	@Override
	public void setLeavesEaten(double leavesEaten) {
		this.leavesEaten = leavesEaten;
		
	}

	@Override
	public double getLeavesEaten() {
		return leavesEaten;
	}

}

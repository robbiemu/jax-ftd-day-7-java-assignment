package com.cooksys.butterpillar.model;

public interface IButterpillar extends Comparable<IButterpillar>{
	
	void setLength(double length);
	
	double getLength();
	
	void setLeavesEaten(double leavesEaten);
	
	double getLeavesEaten();

}

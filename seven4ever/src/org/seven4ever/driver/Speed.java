package org.seven4ever.driver;

public enum Speed {
	LOW(200), MEDIUM(500), FAST(1000);
	private int speed;
	Speed(int speed){
		this.speed = speed;
	}
	
	public int getSpeed(){
		return speed;
	}
}

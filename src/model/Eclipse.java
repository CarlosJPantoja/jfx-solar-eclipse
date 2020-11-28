package model;

public class Eclipse {
	
	private double x;
	private double rad;
	private double xObst;
	private double max;
	private boolean moving;
	
	public Eclipse(double x, double rad, double xObst, double radObst, double max) {
		this.x = x;
		this.rad = rad;
		this.xObst = xObst;
		this.max = max;
	}
	
	public double move() {
		if(x<max+rad) {
			x = x+1;
		} else {
			x = -1*rad;
		}
		return x;
	}
	
	public boolean isMoving() {
		return moving;
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public double getLight() {
    	return Math.abs(xObst-x)/(rad*2);
	}
}

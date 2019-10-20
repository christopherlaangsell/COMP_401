package a3novice;

public class GrayPixel implements Pixel {
	
	private double intensity;
	
	public GrayPixel(double intensity) {
		if(intensity > 1 || intensity < 0) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		
		this.intensity = intensity;
	}
	
	public double getIntensity() {
		return intensity;
	}
	
	public double getRed() {
		return intensity;
	}
	public double getGreen() {
		return intensity;
	}
	public double getBlue() {
		return intensity;
	}
	
	public char getChar() {
		double i = getIntensity();
		if(0.0<=i && i<0.10) {
			return '#';
		} else if(0.10<=i&& i<0.20) {
			return 'M';
		} else if(0.20<=i&& i<0.30) {
			return 'X';
		} else if(0.30<=i&& i<0.4) {
			return 'D';
		} else if(0.40<=i&& i<0.5) {
			return '<';
		} else if(0.50<=i&& i<0.6) {
			return '>';
		} else if(0.60<=i&& i<0.7) {
			return 's';
		} else if(0.70<=i&& i<0.8) {
			return ':';
		} else if(0.80<=i&& i<0.9) {
			return '-';
		} else {
			return ' ';
		}
	}
	
	
}

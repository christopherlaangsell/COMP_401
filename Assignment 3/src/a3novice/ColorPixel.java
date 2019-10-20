package a3novice;

public class ColorPixel implements Pixel {

	private	double red;
	private double green;
	private double blue;

	public ColorPixel(double red, double green, double blue) {
		if (red > 1 || red<0) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		if (green > 1 || green<0) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		if (blue > 1 || blue<0) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		
		this.red = red;
		this.green = green;
		this.blue = blue;
		
	}
	
	public double getRed() {
		return red;
	}
	public double getGreen() {
		return green;
	}
	public double getBlue() {
		return blue;
	}
	public double getIntensity() {
		return 0.299 * red + 0.587 * green + 0.114 * blue;
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

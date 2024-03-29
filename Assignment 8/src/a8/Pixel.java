package a8;

public interface Pixel {

	public double getRed();
	public double getBlue();
	public double getGreen();
	public double getIntensity();
	public char getChar();	
	
	
	
	public Pixel blend(Pixel p, double weight);
	
	public Pixel lighten(double scalar);
	public Pixel darken(double scalar);
}

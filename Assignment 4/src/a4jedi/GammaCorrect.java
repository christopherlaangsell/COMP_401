package a4jedi;

public class GammaCorrect implements PixelTransformation {
	private double gamma;
	
	public GammaCorrect (double gamma) {
		this.gamma = gamma;
	}

	
	public Pixel transform(Pixel p) {
		double red = p.getRed();
		double green = p.getGreen();
		double blue = p.getBlue();
		double ratio = (1.0/gamma);
		
		ColorPixel cp = new ColorPixel(Math.pow(red, ratio),
				Math.pow(green, ratio),
				Math.pow(blue,ratio));
		
		return cp;
	}
}

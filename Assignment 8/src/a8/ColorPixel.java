package a8;



public class ColorPixel implements Pixel {

	private double red;
	private double green;
	private double blue;
	
	private static final double RED_INTENSITY_FACTOR = 0.299;
	private static final double GREEN_INTENSITY_FACTOR = 0.587;
	private static final double BLUE_INTENSITY_FACTOR = 0.114;

	private static final char[] PIXEL_CHAR_MAP = {'#', 'M', 'X', 'D', '<', '>', 's', ':', '-', ' ', ' '};
	
	public ColorPixel(double r, double g, double b) {
		if (r > 1.0 || r < 0.0) {
			throw new IllegalArgumentException("Red out of bounds");
		}
		if (g > 1.0 || g < 0.0) {
			throw new IllegalArgumentException("Green out of bounds");
		}
		if (b > 1.0 || b < 0.0) {
			throw new IllegalArgumentException("Blue out of bounds");
		}
		red = r;
		green = g;
		blue = b;
	}
	
	@Override
	public double getRed() {
		return red;
	}

	@Override
	public double getBlue() {
		return blue;
	}

	@Override
	public double getGreen() {
		return green;
	}

	@Override
	public double getIntensity() {
		return RED_INTENSITY_FACTOR*getRed() + 
				GREEN_INTENSITY_FACTOR*getGreen() + 
				BLUE_INTENSITY_FACTOR*getBlue();
	}
	
	
	
	@Override
	public char getChar() {
		int char_idx = (int) (getIntensity()*10.0);
		return PIXEL_CHAR_MAP[char_idx];
	}
	
	public Pixel blend(Pixel p, double weight) {
		double red_double = (p.getRed()*(1.0-weight)+getRed() * weight);
		double green_double = (p.getGreen()*(1.0-weight)+getGreen()*weight);
		double blue_double = (p.getBlue()*(1.0-weight)+getBlue()*weight);
		
		return new ColorPixel(red_double, green_double, blue_double);
	}
	
	
	
	public Pixel lighten(double scalar) {
		if (scalar < 0.0 || scalar > 1.0) {
			throw new RuntimeException("Lighten factor out of range");
		}
		return blend(new ColorPixel(1.0,1.0,1.0), (1.0-scalar));
		
	}
	
	public Pixel darken(double scalar) {
		if (scalar < 0.0 || scalar > 1.0) {
			throw new RuntimeException("Lighten factor out of range");
		}
		return blend(new ColorPixel(0.0,0.0,0.0), (1.0-scalar));
		
	}
}

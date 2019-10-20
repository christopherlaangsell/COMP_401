package a3jedi;

public class ColorPixel implements Pixel {

	private	double red;
	private double green;
	private double blue;

	public ColorPixel(double red, double green, double blue) {
		if (!(red <= 1 && red>=0) ) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		if (!(green <= 1 && green>=0) ) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		if (!(blue <= 1 && blue>=0) ) {
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
	
	
	public Pixel blend(Pixel p, double weight) {
		if (weight > 1.0 || weight < 0.0 ) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		
		if (p == null) {
			throw new RuntimeException("Parameter not in bounds");
		}
		
		ColorPixel current = new ColorPixel(this.getRed(), this.getGreen(), this.getBlue());
		double red = current.getRed()*weight+p.getRed()*(1-weight);
		double green = current.getGreen()*weight+p.getGreen()*(1-weight);
		double blue = current.getBlue()*weight+p.getBlue()*(1-weight);
		ColorPixel c = new ColorPixel(red, green, blue);
		return (Pixel)c;
		
	}
	
	/*public Pixel lighten(double factor) {
		//when zero, same as og intensity. When 1, white.
		if (!(factor <= 1 && factor>=0) ) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		ColorPixel current = new ColorPixel(this.getRed(), this.getGreen(), this.getBlue());
		double intensity = current.getIntensity()-(current.getIntensity())*factor;
		return new ColorPixel(current.getRed()*(intensity/current.getIntensity()), 
								current.getGreen()*(intensity/current.getIntensity()), 
								current.getBlue()*(intensity/current.getIntensity()));
	}*/
	
	public Pixel lighten(double factor) {
		if(factor > 1 || factor < 0) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}
		
		ColorPixel p = new ColorPixel(1,1,1);
		return this.blend(p, 1-factor);
		
	}
	
	public Pixel darken(double factor) {
		//when zero, same as og intensity. When 1, black.
		if (factor > 1 || factor < 0 ) {
			throw new RuntimeException("Parameter not between 0 and 1");
		}/*
		ColorPixel current = new ColorPixel(this.getRed(), this.getGreen(), this.getBlue());
		double intensity = current.getIntensity()+(1-current.getIntensity())*factor;
		return new ColorPixel(current.getRed()*(intensity/current.getIntensity()), 
				current.getGreen()*(intensity/current.getIntensity()), 
				current.getBlue()*(intensity/current.getIntensity()));*/
		ColorPixel p = new ColorPixel(0,0,0);
		return blend(p, 1-factor);
	}
	
	public boolean equals(Pixel p) {
		if (p == null) {
			throw new RuntimeException("Parameter not in bounds");
		}
		double intensity;
		if(p.getIntensity() > this.getIntensity()) {
			intensity = p.getIntensity();
		} else {
			intensity = this.getIntensity();
		}
		if((Math.abs(this.getRed() - p.getRed())<(.1*intensity)) &&
			(Math.abs(this.getGreen() - p.getGreen())<(.1*intensity)) &&
			(Math.abs(this.getBlue() - p.getBlue())<(.1*intensity))) {
				return true;
			} else {
				return false;
			}
		
				
				
				
		}
			
	}


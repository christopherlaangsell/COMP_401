package a3jedi;

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
	
		public Pixel blend(Pixel p, double weight) {
			if (weight > 1 || weight < 0)  {
				throw new RuntimeException("Parameter not between 0 and 1");
			}
			
			if (p == null) {
				throw new RuntimeException("Parameter not in bounds");
			}
		GrayPixel current = new GrayPixel(this.getIntensity());
		double intensity = current.getIntensity()*weight+p.getIntensity()*(1-weight);
		GrayPixel g = new GrayPixel(intensity);
		return (Pixel)g;
	}
		
		
		public Pixel lighten(double factor) {
			//when zero, same as og intensity. When 1, white.
			if (factor > 1 || factor < 0) {
				throw new RuntimeException("Parameter not between 0 and 1");
			}
			GrayPixel current = new GrayPixel(this.getIntensity());
			double intensity = current.getIntensity()-(current.getIntensity())*factor;
			GrayPixel g = new GrayPixel(intensity);
			return (Pixel)g;
		}
		
		public Pixel darken(double factor) {
			//when zero, same as og intensity. When 1, black.
			if (factor > 1 || factor < 0)  {
				throw new RuntimeException("Parameter not between 0 and 1");
			}
			GrayPixel current = new GrayPixel(this.getIntensity());
			double intensity = current.getIntensity()+(1-current.getIntensity())*factor;
			GrayPixel g = new GrayPixel(intensity);
			return (Pixel)g;
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
			if((Math.abs(this.getIntensity() - p.getIntensity())<(.1*intensity))) {
					return true;
				} else {
					return false;
				}
			
					
					
					
			}
				
		
		
	
}

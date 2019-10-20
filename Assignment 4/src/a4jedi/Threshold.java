package a4jedi;

public class Threshold implements PixelTransformation {

	private double threshold;
	
		public Threshold (double threshold) {
			this.threshold = threshold;
		}

		
		public Pixel transform(Pixel p) {
			
			if(p == null) {
				throw new IllegalArgumentException("nah");
			}
			if( p.getIntensity()>threshold) {
				return new GrayPixel(0.0);
			} else {
				return new GrayPixel(1.0);
			}
			
		}
}

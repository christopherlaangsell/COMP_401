package a3jedi;

public class PictureImpl implements Picture {
	private int width;
	private int height;
	private Pixel p;
	private Pixel[][] pic;
	public PictureImpl(int width, int height) {
		
		if(width<=0 || height <=0 ) {
			throw new RuntimeException("Nice try dawg");
		}
		
		this.width = width;
		this.height = height;
		pic = new Pixel[height][width];
		GrayPixel a = new GrayPixel(.5);
		for(int i=0; i< height; i++) {
			for(int j=0; j<(width);j++) {
				pic[i][j] = a;
			}
	 	}
		
		
	}
	
	
	
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	//int[][] pic = new int[Picture.getHeight()][Picture.getWidth()];

	
	public void setPixel(int x, int y, Pixel p) {
		if(x<0 || x>this.width) {
			throw new RuntimeException("Pnib");
		}
		if(y<0 || y>this.height) {
			throw new RuntimeException("Pnib");
		}
		if(p == null) {
			throw new RuntimeException("Parameter cannot be null");
		}
		this.p=p;
		pic[y][x] = p;
		
	}
	
	public Pixel getPixel(int x, int y) {
		if(x<0 || x > this.width) {
			throw new RuntimeException("Pnib");
		}
		if(y<0 || y > this.height) {
			throw new RuntimeException("Pnib");
		}
		
		
		
		return pic[y][x];
	}
	
	public int countRange(double low, double high) {
		int count=0;
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				if(pic[i][j].getIntensity()>=low && pic[i][j].getIntensity()<=high) {
					count++;
				}
			}	
		}
		return count;
	}
	
	public void print() {
		for(int i=0;i<(this.height); i++) {
			for(int j=0;j<(this.width); j++) {
				System.out.print(pic[i][j].getChar());
			}
			System.out.println();
		}
	}
	
	public double unequalPixelRatio(Picture p) {
		if(p==null) {
			throw new RuntimeException("C'mon man");
		}
		double num = 0.0;
		int denom = 0;
		int total = 0;
		PictureImpl current = new PictureImpl(this.width, this.height);
		if((current.getWidth() == p.getWidth()) && 
				(current.getHeight() == p.getHeight())) {
			for(int i=0; i<this.height; i++) {
				for(int j=0; j<this.width; j++) {
					total ++;
					if(pic[i][j] == p) {
						num ++;
					} else {
						denom ++;
					}
				}
			}
			double result = (double)(1.0-(num/total));
			return result;
		} else {
			throw new RuntimeException("pics not same");
		}
	}
	
	public double calculatePSNR(Picture p) {
		if(p==null) {
			throw new RuntimeException("C'mon man");
		}
		
		double count =0.0;
		double avg = 0.0;
		if((this.width == p.getWidth()) && (this.height == p.getHeight())) {
			for(int i=0; i<this.height; i++) {
				for(int j=0; j<this.width; j++) {
					double mse = ((p.getPixel(i,j).getIntensity()-pic[i][j].getIntensity())*
							(p.getPixel(i,j).getIntensity()-pic[i][j].getIntensity()));
					count = count + mse;
				}
			}
			avg = count/(height*width);
			if(avg == 0) {
				throw new RuntimeException("infinity brah");
			}
			 double psnr = 20*Math.log10(1.0)-10*Math.log10(avg);
			 return psnr;
		}
		
		else {
			throw new RuntimeException("one size dsnt fit all");
		}
		
	}
}

package a6adept;

import java.util.Iterator;

public class PictureImpl extends AnyPicture implements Picture {
	private Pixel[][] pic;
	
	public PictureImpl(int width, int height) {
		super(width, height);
		
		if(width<=0 || height <=0 ) {
			throw new RuntimeException("Nice try dawg");
		}

		pic = new Pixel[height][width];
		GrayPixel a = new GrayPixel(.5);
		for(int i=0; i< height; i++) {
			for(int j=0; j<(width);j++) {
				pic[i][j] = a;
			}
	 	}
		
		
	}
	
	//int[][] pic = new int[Picture.getHeight()][Picture.getWidth()];

	
	public void setPixel(int x, int y, Pixel p) {
		if(x<0 || x>this.getWidth()) {
			throw new RuntimeException("Pnib");
		}
		if(y<0 || y>this.getHeight()) {
			throw new RuntimeException("Pnib");
		}
		if(p == null) {
			throw new RuntimeException("Parameter cannot be null");
		}
		pic[y][x] = p;
		
	}
	
	public Pixel getPixel(int x, int y) {
		if(x<0 || x > this.getWidth()) {
			throw new RuntimeException("Pnib");
		}
		
		if(y<0 || y > this.getHeight()) {
			throw new RuntimeException("Pnib");
		}

		return pic[y][x];
	}
	
	public int countRange(double low, double high) {
		int count=0;
		for(int i=0; i<this.getHeight(); i++) {
			for(int j=0; j<this.getWidth(); j++) {
				if(pic[i][j].getIntensity()>=low && pic[i][j].getIntensity()<=high) {
					count++;
				}
			}	
		}
		return count;
	}
	
	public void print() {
		for(int i=0;i<(this.getHeight()); i++) {
			for(int j=0;j<(this.getWidth()); j++) {
				System.out.print(pic[i][j].getChar());
			}
			System.out.println();
		}
	}
}

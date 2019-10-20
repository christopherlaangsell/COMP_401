package a4jedi;

public class PictureImpl extends AnyPicture implements Picture {
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


	@Override
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		SubPicture sub = new SubPictureImpl(this , xOffset, yOffset, width, height);
		return sub;
	}



	@Override
	public TransformedPicture transform(PixelTransformation xform) {
		// TODO Auto-generated method stub
		return null;
	}

}

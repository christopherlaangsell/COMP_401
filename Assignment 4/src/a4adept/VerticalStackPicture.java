package a4adept;

public class VerticalStackPicture implements Picture {

	private Picture top;
	private Picture bottom;
	
	public VerticalStackPicture(Picture top, Picture bottom) {
		if( top == null || bottom == null) {
			throw new IllegalArgumentException("top and bottom null");
		}
		if( top.getWidth() != bottom.getWidth()) {
			throw new IllegalArgumentException("widths not compatable");
		}
		
		
		this.top = top;
		this.bottom = bottom;
	}
	
	
	public int getWidth() {
		return top.getWidth();
	}

	@Override
	public int getHeight() {
		return top.getHeight()+bottom.getHeight();
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight() || p == null) {
			throw new IllegalArgumentException("invalid parameters");
		}
		
		if(y<top.getHeight()) {
			top.setPixel(x, y, p);
		} else { 
			bottom.setPixel(x, y-top.getHeight(), p);
		}
	}

	@Override
	public Pixel getPixel(int x, int y) {
		if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight()) {
			throw new IllegalArgumentException("invalid parameters");
		}
		
		if(y<top.getHeight()) {
			return top.getPixel(x, y);
		} else { 
			return bottom.getPixel(x, y-top.getHeight());
		}
	}

	@Override
	public int countRange(double low, double high) {
		int count = 0;
		for(int i =0; i<top.getWidth(); i++) {
			for(int j=0; j<top.getHeight(); j++) {
				if(top.getPixel(i,j).getIntensity()<=high && top.getPixel(i,j).getIntensity()>=low) {
					count ++;
				}
			}
		}
		
		for(int i =0; i<bottom.getWidth(); i++) {
			for(int j=0; j<bottom.getHeight(); j++) {
				if(bottom.getPixel(i,j).getIntensity()<=high && bottom.getPixel(i,j).getIntensity()>=low) {
					count ++;
				}
			}
		}
		
		return count;
	}	
	
	
	public void print() {
		for(int i=0; i<top.getHeight(); i++) {
			for( int j=0; j<top.getWidth(); j++) {
				System.out.print(top.getPixel(i, j).getChar());
			}
			System.out.println();
		}
		
		for(int i=0; i<bottom.getHeight(); i++) {
			for( int j=0; j<bottom.getWidth(); j++) {
				System.out.print(bottom.getPixel(i, j).getChar());
			}
			System.out.println();
		}
	}
	
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		return new SubPictureImpl(this, xOffset, yOffset, width, height);
	}
}

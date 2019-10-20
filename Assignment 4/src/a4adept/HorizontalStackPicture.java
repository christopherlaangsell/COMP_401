package a4adept;

public class HorizontalStackPicture implements Picture {

	private Picture left;
	private Picture right;
	private Picture newPic;
	
	public HorizontalStackPicture(Picture left, Picture right) {
		if( left == null || right == null) {
			throw new IllegalArgumentException("left and right null");
		}
		if( left.getHeight() != right.getHeight()) {
			throw new IllegalArgumentException("heights not compatable");
		}
		
		
		this.left = left;
		this.right = right;
		
	}
	
	public int getWidth() {
		return left.getWidth()+right.getWidth();
		
	}
	public int getHeight() {
		return left.getHeight();
	}
	
	public void setPixel (int x, int y, Pixel p) {
		if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight() || p == null) {
			throw new IllegalArgumentException("invalid parameters");
		}
		
		if(x<left.getWidth()) {
			left.setPixel(x, y, p);
		} else {
			right.setPixel(x-left.getWidth(), y, p);
		}
	}
	
	public Pixel getPixel (int x, int y) {
		if (x < 0 || x > this.getWidth() || y < 0 || y > this.getHeight()) {
			throw new IllegalArgumentException("invalid parameters");
		}
		if(x<left.getWidth()) {
			return left.getPixel(x, y);
		} else {
			return right.getPixel(x-left.getWidth(), y);
		}
	}
	
	public int countRange(double low, double high) {
		int count = 0;
		for(int i =0; i<left.getWidth(); i++) {
			for(int j=0; j<left.getHeight(); j++) {
				if(left.getPixel(i,j).getIntensity()<=high && left.getPixel(i,j).getIntensity()>=low) {
					count ++;
				}
			}
		}
		
		for(int i =0; i<right.getWidth(); i++) {
			for(int j=0; j<right.getHeight(); j++) {
				if(right.getPixel(i,j).getIntensity()<=high && right.getPixel(i,j).getIntensity()>=low) {
					count ++;
				}
			}
		}
		
		return count;
	}
	
	public void print() {
		for(int i=0; i<left.getHeight(); i++) {
			for(int j=0; j<left.getWidth(); j++) {
				System.out.print(left.getPixel(i,j).getChar());
			}
			
			for(int k=0; k<right.getWidth(); k++) {
				System.out.print(right.getPixel(i,k).getChar());
			}
			System.out.println();
		}
	}
	
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		return new SubPictureImpl(this, xOffset, yOffset, width, height);
	}
}

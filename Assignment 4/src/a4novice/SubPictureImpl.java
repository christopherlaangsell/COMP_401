package a4novice;

public class SubPictureImpl extends AnyPicture implements SubPicture {
	
	private int XOffset;
	private int YOffset;
	private Picture source;
	private int width;
	private int height;
	
	
	public SubPictureImpl(Picture source, int xOffset, int yOffset, int width, int height) {
		if(source == null) {
			throw new IllegalArgumentException("Picture null");
		}
		
		if((xOffset+width)>source.getWidth()) {
			throw new IllegalArgumentException("Width too large");
		}
		if((yOffset+height)>source.getHeight()) {
			throw new IllegalArgumentException("Height too large");
		}
		
		if(xOffset < 0 || yOffset <0) {
			throw new IllegalArgumentException("Does not exist");
		}
		
		this.XOffset = xOffset;
		this.YOffset = yOffset;
		this.width = width;
		this.source = source;
		this.height = height;
		
		
		
		
		
	}
	
	public int getXOffset() {
		return XOffset;
	}
	
	public int getYOffset() {
		return YOffset;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setPixel(int x, int y, Pixel p) {
		source.setPixel(getXOffset() + x, getYOffset()+y, p);
	}

	@Override
	public Pixel getPixel(int x, int y) {
		return source.getPixel(getXOffset()+x, getYOffset() + y);
	}

	@Override
	public int countRange(double low, double high) {
		int count=0;
		for(int i=0; i<getHeight(); i++) {
			for(int j=0; j<getWidth(); j++) {
				double intensity = source.getPixel(getXOffset()+i, getYOffset()+j).getIntensity();
				if(intensity >= low && intensity <= high) {
					count++;
				}
			}	
		}
		return count;
	}

	@Override
	public void print() {
		for(int i=0;i<getHeight(); i++) {
			for(int j=0;j<(getWidth()); j++) {
				System.out.print(this.getPixel(getXOffset() + j, getYOffset()+j).getChar());
			}
			if (i<getHeight()-1) {
			System.out.println();
			}
		}
		
	}

	@Override
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		SubPicture sub = new SubPictureImpl(this , xOffset, yOffset, width, height);
		return sub;
	}

	@Override
	public Picture getSource() {
		return this.source;
	}

}

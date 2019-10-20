package a4jedi;

public class TransformedPicture implements Picture {
	
	private Picture source;
	private PixelTransformation xform;
	public TransformedPicture (Picture source, PixelTransformation xform) {
		this.source = source;
		this.xform=xform;
	}
	
	public int getWidth() {
		return source.getWidth();
	}
	
	public int getHeight() {
		return source.getHeight();
	}
	
	public void setPixel(int x, int y, Pixel p) {
		throw new UnsupportedOperationException("nice try");
	}
	
	public Pixel getPixel(int x, int y) {
		
		return xform.transform(source.getPixel(x, y));
	}
	
	public int countRange(double low, double high) {
		int count = 0;
		for(int i =0; i<source.getHeight(); i++) {
			for(int j=0; j<source.getWidth(); j++) {
				if(source.getPixel(i,j).getIntensity()<=high && source.getPixel(i,j).getIntensity()>=low) {
					count ++;
				}
			}
		}
		return count;
	}
	@Override
	public void print() {
		for (int x =0; x<this.getWidth();x++) {
			for(int j = 0; j<this.getHeight(); j++) {
				System.out.print(this.getPixel(x, j));
			}
			System.out.println();
		}
		
	}
	@Override
	public SubPicture extract(int xOffset, int yOffset, int width, int height) {
		// TODO Auto-generated method stub
		return new SubPictureImpl(this, xOffset, yOffset, width, height);
	}

	@Override
	public TransformedPicture transform(PixelTransformation xform) {
		// TODO Auto-generated method stub
		return new TransformedPicture(this,xform);
	}

	
}

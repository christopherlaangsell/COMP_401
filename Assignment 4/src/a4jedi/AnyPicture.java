package a4jedi;

public abstract class AnyPicture implements Picture {
	public abstract SubPicture extract(int xOffset, int yOffset, int width, int height);
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract void setPixel(int x, int y, Pixel p);
	public abstract Pixel getPixel(int x, int y);
	public abstract int countRange(double low, double high);
	public abstract void print();
	public TransformedPicture transform(PixelTransformation xform) {
		return new TransformedPicture(this,xform);
	}
	
	
}

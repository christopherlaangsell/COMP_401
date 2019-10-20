package a4novice;

public abstract class AnyPicture {
	public abstract SubPicture extract(int xOffset, int yOffset, int width, int height);
	public abstract int getWidth();
	public abstract int getHeight();
	public abstract void setPixel(int x, int y, Pixel p);
	public abstract Pixel getPixel(int x, int y);
	public abstract int countRange(double low, double high);
	public abstract void print();
	
	
}

package a6jedi;

import java.util.Iterator;

public abstract class AnyPicture implements Picture {
	private int width;
	private int height;
	
	public AnyPicture(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public  SubPicture extract(int xOffset, int yOffset, int width, int height) {
		return new SubPictureImpl(this , xOffset, yOffset, width, height);
	}
	
	public void setPixel(Coordinate c, Pixel p) {
		this.setPixel(c.getX(), c.getY(), p);
	}
	
	
	public Pixel getPixel(Coordinate c) {
		return this.getPixel(c.getX(), c.getY());
	}
	
	
	public SubPicture extract(Coordinate corner_a, Coordinate corner_b) {
		int w,h,x,y;
		
		//Indicating which corner is top left and bottom right. 
		//This then allows us to assign an appropriate width.
		
		if(corner_a.getX()>corner_b.getX()) {
			w=corner_a.getX()-corner_b.getX();
			x=corner_b.getX();
		} else {
			w=corner_b.getX()-corner_a.getX();
			x=corner_a.getX();
		}
		
		if(corner_a.getY()>corner_b.getY()) {
			h=corner_a.getY()-corner_b.getY();
			y=corner_b.getY();
		} else {
			h=corner_b.getY()-corner_a.getY();
			y=corner_a.getY();
		}
		
			
			
		return new SubPictureImpl(this, x, y, w, h);
	}
	
	public Iterator<Pixel> iterator() {
		return new RowMajorPixelIterator(this);
	}
	
	public Iterator<Pixel> sample(int init_x, int init_y, int dx, int dy) {
		return new SamplePixelIterator(this, init_x, init_y, dx, dy);
	}
	
	public Iterator<SubPicture> window(int window_width, int window_height) {
		return new WindowSubPictureIterator(this, window_width, window_height);
	}
	
	public Iterator<SubPicture> tile(int tile_width, int tile_height) {
		return new TileSubPictureIterator(this, tile_width, tile_height);
	}
	
	public Iterator<Pixel> zigzag() {
		return new ZigZagPixelIterator(this);
	}
	
	public abstract void setPixel(int x, int y, Pixel p);
	public abstract Pixel getPixel(int x, int y);
	public abstract int countRange(double low, double high);
	public abstract void print();
	
}

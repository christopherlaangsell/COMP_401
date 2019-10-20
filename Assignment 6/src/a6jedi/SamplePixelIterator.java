package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SamplePixelIterator implements Iterator<Pixel> {

	private AnyPicture source;
	private int init_x, init_y, dx, dy;
	private int x_idx,y_idx;
	
	public SamplePixelIterator (AnyPicture source, int init_x, int init_y, int dx, int dy) {
		
		//making sure parameters are appropriate for picture source
		if(init_x>source.getWidth() || init_y>source.getHeight() || init_x < 0 || init_y < 0) {
			throw new IllegalArgumentException("Out of Pic");
		}
		
		if(dx<0 || dy < 0) {
			throw new IllegalArgumentException("Negative d-something");
		}
		
		this.source = source;
		this.init_x=init_x;
		this.init_y=init_y;
		this.dx=dx;
		this.dy=dy;
		x_idx = init_x;
		y_idx = init_y;
	}
	
	public boolean hasNext() {
		//Simple. Saying has next as long as it doesn't exceed height.
		return ((y_idx)<source.getHeight());
			
	}
	
	public Pixel next() {
		Pixel p = source.getPixel(x_idx,y_idx);
		
		if (!hasNext()) {
			throw new NoSuchElementException("no mo");
		}
		
		x_idx += dx;
		if(x_idx >= source.getWidth()) {
			x_idx = init_x;
			y_idx += dy;
		} 
			
			
		
		return p;
	}
	
}

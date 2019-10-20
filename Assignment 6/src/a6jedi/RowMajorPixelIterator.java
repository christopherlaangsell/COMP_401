package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowMajorPixelIterator implements Iterator<Pixel> {

	private AnyPicture source;
	private int x_idx, y_idx;
	
	public RowMajorPixelIterator(AnyPicture source) {
		this.source = source;
		x_idx = 0;
		y_idx = 0;
	}
	
	public boolean hasNext() {
		//Creating bounds framework
		return (x_idx<source.getWidth() && y_idx<source.getHeight());
			
	}
	
	public Pixel next() {
		Pixel p = source.getPixel(x_idx,y_idx);
		
		//Checking to make sure not at end.
		if (x_idx == source.getWidth()-1 && y_idx == source.getHeight()-1) {
			throw new NoSuchElementException("no mo");
		}
		
		//If hitting horizontal bounds, augment y and reset x.
		if(x_idx < source.getWidth()-1) {
			x_idx ++;
		} else {
			x_idx = 0;
			y_idx ++;
		}
		return p;
	}

}

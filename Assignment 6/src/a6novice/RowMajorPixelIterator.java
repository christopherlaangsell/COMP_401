package a6novice;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RowMajorPixelIterator implements Iterator<Pixel> {

	private AnyPicture source;
	int x_idx, y_idx;
	
	public RowMajorPixelIterator(AnyPicture source) {
		this.source = source;
		x_idx = 0;
		y_idx = 0;
	}
	
	public boolean hasNext() {
		return (y_idx<source.getHeight());
			
	}
	
	public Pixel next() {
		Pixel p = source.getPixel(x_idx,y_idx);
		
		if (!hasNext()) {
			throw new NoSuchElementException("no mo");
		}
		
		if(x_idx < source.getWidth()-1) {
			x_idx ++;
		} else {
			x_idx = 0;
			y_idx ++;
		}
		return p;
	}

}

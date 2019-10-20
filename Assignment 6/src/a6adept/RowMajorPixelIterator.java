package a6adept;

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
		//Think this might be wrong. Should be <= right?
		return (x_idx<source.getWidth() && y_idx<source.getHeight());
			
	}
	
	public Pixel next() {
		Pixel p = source.getPixel(x_idx,y_idx);
		
		if (x_idx == source.getWidth()-1 && y_idx == source.getHeight()-1) {
			throw new NoSuchElementException("no mo");
		}
		
		//thinking it should probably not be -1
		if(x_idx < source.getWidth()-1) {
			x_idx ++;
		} else {
			x_idx = 0;
			y_idx ++;
		}
		return p;
	}

}

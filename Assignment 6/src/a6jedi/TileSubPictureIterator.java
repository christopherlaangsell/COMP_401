package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TileSubPictureIterator implements Iterator<SubPicture> {

	
	private AnyPicture source;
	private int w,h,x_idx,y_idx;
	
	public TileSubPictureIterator (AnyPicture source, int width, int height) {
		//Checking to make sure Tile in bounds
		if(width>source.getWidth() || width < 0 || height>source.getHeight() || height < 0) {
			throw new IllegalArgumentException("Tile not appropriate");
		}
		
		
		this.source = source;
		this.w = width;
		this.h = height;
		this.x_idx = 0;
		this.y_idx = 0;
	}

	
	public boolean hasNext() {
		//returning true as long as in bounds.
		return ((x_idx+w)<=source.getWidth() && (y_idx+h)<=source.getHeight());
	}


	public SubPicture next() {
		SubPicture p = new SubPictureImpl(source, x_idx, y_idx, w, h);

		
		//Checking if there's another
		if (!hasNext()) {
			throw new NoSuchElementException("no mo");
		}
		
		if(x_idx+w < source.getWidth()) {
			x_idx += w;
		} else {
			x_idx = 0;
			y_idx += h;
		}
		return p;
	}
	
	
}

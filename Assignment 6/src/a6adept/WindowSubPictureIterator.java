package a6adept;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class WindowSubPictureIterator implements Iterator<SubPicture> {

	private AnyPicture source;
	private int w,h,x_idx,y_idx;
	
	public WindowSubPictureIterator(AnyPicture source, int width, int height) {
		if(width<0 || width>source.getWidth() || height<0 || height > source.getHeight()) {
			throw new IllegalArgumentException("window not ok");
		}
		
		this.source = source;
		this.w = width;
		this.h = height;
		x_idx = 0;
		y_idx = 0;
	}

	public boolean hasNext() {

		return ((x_idx+w)<=source.getWidth() && (y_idx+h)<=source.getHeight());

	}

	public SubPicture next() {
		SubPicture p = new SubPictureImpl(source, x_idx, y_idx, w, h);

		if (x_idx+w > source.getWidth() && y_idx+h > source.getHeight()) {
			throw new NoSuchElementException("no mo");
		}

		if(x_idx+w < source.getWidth()) {
			x_idx ++;
		} else {
			x_idx = 0;
			y_idx ++;
		}
		return p;
	}

	
	
	
}

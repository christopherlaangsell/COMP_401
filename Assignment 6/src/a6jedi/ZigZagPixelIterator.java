package a6jedi;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ZigZagPixelIterator implements Iterator<Pixel> {

	private AnyPicture source;
	private int w, h, x_idx, y_idx;

	public ZigZagPixelIterator(AnyPicture source) {
		this.source = source;
		this.h = source.getHeight();
		this.w = source.getWidth();
		this.x_idx = 0;
		this.y_idx = 0;
	}

	public boolean hasNext() {
		
		//Checking if either of the indexes are out of bounds. 
		if (y_idx == source.getHeight() || x_idx == source.getWidth()) {
			return false;
		}
		return true;

	}

	public Pixel next() {
		

		if (!hasNext()) {
			throw new NoSuchElementException("no mo");
		}
		Pixel p = source.getPixel(x_idx,y_idx);


		//Checking if even diagonal
		if((x_idx+y_idx)%2 == 0) {


			//Checking if hitting right side wall
			if(x_idx==(w-1)) {
				y_idx++;
			}
			
			//Checking if hitting top boundary
			else if(y_idx==0) {
				x_idx++;
			} 

			

			//Normal operation
			else {
				x_idx ++;
				y_idx --;
			}
		}  

		//Odd diagonal
		else {

			//Checking bottom wall
			if (y_idx==(h-1)) {
				x_idx++;
			}
			
			//Checking left wall
			else if(x_idx==0) {
				y_idx++;
			} 


			//Normal Operation
			else {
				x_idx--;
				y_idx++;
			}
		}





		return p;
	}

}

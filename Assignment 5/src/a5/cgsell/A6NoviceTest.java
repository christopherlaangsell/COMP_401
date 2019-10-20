package a5.cgsell;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import a6novice.*;
import junit.framework.Assert;
import java.util.Iterator;
import org.junit.Test;
import a6novice.*;


public class A6NoviceTest {
		
	static public String[] getTestNames() {
		String[] test_names = new String[5];
		
		test_names[0] = "getXTest";
		test_names[1] = "setCoordinateTest";
		test_names[2] = "getCoordinateTest";
		test_names[3] = "IteratorTest";
		test_names[4] = "IteratorBoundsTest";
		
		return test_names;
	}
	
	@Test
	//Checks that coordinate attributes were set in correctly
	public void getXTest() {
		Coordinate c = new Coordinate(1,1);
		assertEquals(c.getX(),1);
	}
		
	@Test
	//Checks that program sets coordinates effectively.
	public void setCoordinateTest() {
	Pixel p = new GrayPixel(0.75);
	Picture pic = new PictureImpl(7,2);
	Coordinate c = new Coordinate(1,1);
	
	
	try {
		pic.setPixel(c,p);
	} catch (IllegalArgumentException e) {
		fail("");
	} catch (Exception e) {
		fail("");
	}
	}
	
	@Test
	//Checks that coordinates are accessible.
	public void getCoordinateTest() {
		Pixel p = new GrayPixel(.5);
		Picture pic = new PictureImpl(4,4);
		Coordinate c = new Coordinate(3,2);
		pic.setPixel(c, p);
		
	
	assertEquals(pic.getPixel(c), p);
	
	}
	
	@Test
	//Checks iterator off arbitrary pixels
	public void IteratorTest() {
		Picture picc = new PictureImpl(4,4);
		double[] nums = {.3,.7,.5,.1,.1};
		
		for (int i = 0; i<picc.getHeight(); i++) {
			for (int j = 0; j<picc.getWidth(); j++) {
				

				picc.setPixel(j,i, new GrayPixel(nums[j]*nums[i]));
			}  
		}
		
		Iterator<Pixel> it = picc.iterator();
		
		for( int i=0; i<picc.getHeight(); i++) {
			for (int j=0; j<picc.getWidth(); j++) {
				assertEquals(it.next(), picc.getPixel(j,i));
			}
		}
				
				
	}
	
	@Test
	//Checking iterator bounds. Basically has next method.
	public void IteratorBoundsTest() {
		Picture picc = new PictureImpl(2,2);
		double[] nums = {.3,.7,.5,.1,.1};
		
		for (int i = 0; i<picc.getHeight(); i++) {
			for (int j = 0; j<picc.getWidth(); j++) {


				picc.setPixel(j,i, new GrayPixel(nums[j]*nums[i]));
			}  
		}
		
		Iterator<Pixel> it = picc.iterator();
		
		it.next();
		it.next();
		it.next();
		it.next();
		
		assertFalse(it.hasNext());
		
	}
	
	
	
		
	}
	
	


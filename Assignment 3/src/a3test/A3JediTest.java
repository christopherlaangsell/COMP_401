package a3test;

import static org.junit.Assert.*;

import org.junit.Test;

import a3jedi.GrayPixel;
import a3jedi.Picture;
import a3jedi.PictureImpl;

public class A3JediTest {

	@Test
	public void sizeMismatchTest() {
		Picture p1 = new PictureImpl(3,4);
		Picture p2 = new PictureImpl(4,3);
		
		try {
			double psnr = p1.calculatePSNR(p2);
			fail("Expected runtime exception");
		} catch (RuntimeException e) {
		}
	}
	
	@Test
	public void psnrTest() {
		Picture p1 = new PictureImpl(1,1);
		Picture p2 = new PictureImpl(1,1);
		
		p1.setPixel(0, 0, new GrayPixel(0.0));
		p2.setPixel(0, 0, new GrayPixel(1.0));

		assertEquals(0.0, p1.calculatePSNR(p2), 0.001);
		
		p2.setPixel(0, 0, new GrayPixel(0.5));
		assertEquals(-10.0*Math.log10(0.25), p1.calculatePSNR(p2), 0.001);
	}

}

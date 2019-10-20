package a3test;

import static org.junit.Assert.*;

import org.junit.Test;

import a3adept.ColorPixel;
import a3adept.GrayPixel;
import a3adept.Picture;
import a3adept.PictureImpl;
import a3adept.Pixel;

public class A3AdeptTest {

	@Test
	public void blendTest() {
		Pixel p1 = new ColorPixel(0.5, 0.3, 0.8);
		Pixel p2 = new ColorPixel(0.4, 0.4, 0.2);
		
		Pixel blend = p1.blend(p2, 1.0);
		assertEquals(0.5, blend.getRed(), 0.001);
		assertEquals(0.3,  blend.getGreen(), 0.001);
		assertEquals(0.8,  blend.getBlue(), 0.001);
		
		blend = p1.blend(p2, 0.0);
		assertEquals(0.4, blend.getRed(), 0.001);
		assertEquals(0.4, blend.getGreen(), 0.001);
		assertEquals(0.2, blend.getBlue(), 0.001);

		blend = p1.blend(p2,  0.5);
		assertEquals(0.45, blend.getRed(), 0.001);
		assertEquals(0.35, blend.getGreen(), 0.001);
		assertEquals(0.50, blend.getBlue(), 0.001);		
		
		assertFalse(blend == p1);
		assertFalse(blend == p2);
	}

	@Test
	public void lightenTest() {
		Pixel p = new ColorPixel(0.5, 0.3, 0.8);
		
		Pixel lighter = p.lighten(0.0);
		assertEquals(0.5, lighter.getRed(), 0.001);
		assertEquals(0.3,  lighter.getGreen(), 0.001);
		assertEquals(0.8,  lighter.getBlue(), 0.001);
		
		lighter = p.lighten(1.0);
		assertEquals(1.0, lighter.getRed(), 0.001);
		assertEquals(1.0, lighter.getGreen(), 0.001);
		assertEquals(1.0, lighter.getBlue(), 0.001);

		lighter = p.lighten(0.5);
		assertEquals(0.75, lighter.getRed(), 0.001);
		assertEquals(0.65, lighter.getGreen(), 0.001);
		assertEquals(0.9, lighter.getBlue(), 0.001);		
		
		assertFalse(lighter == p);
	}

	@Test
	public void darkenTest() {
		Pixel p = new ColorPixel(0.5, 0.3, 0.8);
		
		Pixel darker = p.darken(0.0);
		assertEquals(0.5, darker.getRed(), 0.001);
		assertEquals(0.3,  darker.getGreen(), 0.001);
		assertEquals(0.8,  darker.getBlue(), 0.001);
		
		darker = p.darken(1.0);
		assertEquals(0.0, darker.getRed(), 0.001);
		assertEquals(0.0, darker.getGreen(), 0.001);
		assertEquals(0.0, darker.getBlue(), 0.001);

		darker = p.darken(0.5);
		assertEquals(0.25, darker.getRed(), 0.001);
		assertEquals(0.15, darker.getGreen(), 0.001);
		assertEquals(0.4, darker.getBlue(), 0.001);		
		
		assertFalse(darker == p);
	}

	@Test
	public void pictureConstructorTest() {
		Picture p = new PictureImpl(5,7);
		assertEquals(5, p.getWidth());
		assertEquals(7, p.getHeight());
		Pixel gray = new GrayPixel(0.5);
		for (int x=0; x<p.getWidth(); x++) {
			for (int y=0; y<p.getHeight(); y++) {
				compare_pixels(p.getPixel(x,y), gray);
			}
		}
	}

	@Test
	public void pictureGetAndSetPixelTest() {
		Picture p = new PictureImpl(5,7);
		Pixel a = new ColorPixel(0.1, 0.4, 0.8);
		p.setPixel(0, 0, a);
		compare_pixels(a, p.getPixel(0, 0));
	}
	
	@Test
	public void pictureCountRangeTest() {
		Picture p = new PictureImpl(10,10);
		for (int x=0; x<10; x++) {
			for (int y=0; y<10; y++) {
				p.setPixel(x, y, new GrayPixel(((y*10)+x)/100.0));
			}
		}
		assertEquals(100, p.countRange(0.0, 1.0));
		assertEquals(50, p.countRange(0.245, 0.745));
	}
	
	private static boolean compare_pixels(Pixel a, Pixel b) {
		assertEquals(a.getRed(), b.getRed(), 0.001);
		assertEquals(a.getGreen(), b.getGreen(), 0.001);
		assertEquals(a.getBlue(), b.getBlue(), 0.001);
		return true;
	}
}

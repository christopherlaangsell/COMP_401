package a2novice;

import java.util.Scanner;
import java.util.Arrays;

public class A2Novice {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		process(s);
		 
	}

	public static void process(Scanner s) {
		int pic_width = s.nextInt();
		int pic_height = s.nextInt();
		int current_pixel_number = 0;
		int pic_total = pic_width*pic_height;
		
		int pixels[] = new int[pic_total];
		String adjusted_pixels[] = new String[pic_total];
		
		for (int i = 0; i<pixels.length; i++) {
			current_pixel_number = s.nextInt();
			pixels[i] = current_pixel_number;
			adjusted_pixels[i] = read_intensity( current_pixel_number);
		}
		
		
		print(arrange(adjusted_pixels, pic_height, pic_width ), pic_height, pic_width);
		int min_value = 100;
		int max_value = 0;
		int sum=0;
		double average_value = 0;
		for (int i = 0; i<pixels.length; i++) {
			if (min_value > pixels[i]) {
				min_value = pixels[i];
			}
			
			if (max_value < pixels[i]) {
				max_value = pixels[i];
			}
			
			sum += pixels[i];
			average_value = ((double) sum)/((double )(i+1));
		}
		System.out.println("Min value = " + min_value);
		System.out.println("Max value = " + max_value);
		System.out.println("Average value = " + average_value);
		
		
		
	}
	
	
	
	public static String read_intensity(int pixel_value) {
		if (pixel_value<10) {
			return "#";
		} else if (pixel_value<20) {
			return "M";
		} else if (pixel_value<30) {
			return "X";
		} else if (pixel_value<40) {
			return "D";
		} else if (pixel_value<50) {
			return "<";
		} else if (pixel_value<60) {
			return ">";
		} else if (pixel_value<70) {
			return "s";
		} else if (pixel_value<80) {
			return ":";
		} else if (pixel_value<90) {
			return "-";
		} else {
			return " ";
		} 
	}
	
	public static String[][] arrange(String[] adjusted_pixels, int height, int width) {
		int pic_height = height;
		int pic_width = width;
		String arranged[][] = new String[pic_width][pic_height];
		for(int i=0; i<pic_width; i++) {
			for(int j=0; j<pic_height; j++) {
				//System.out.println("i:"+i+",j:"+j + ", "+ (j+i*pic_height));
				arranged[i][j] = adjusted_pixels[j+i*pic_height];
				
			}
			
			//System.out.println(arranged[][]);
		}
		return arranged;
	}
	
	public static void print(String[][] arranged, int height, int width) {
		int pic_height = height;
		int pic_width = width;
		for(int i=0; i<pic_height; i++) {
			for(int j=0; j<pic_width; j++) {
				System.out.print(arranged[j][i]);
			}
			System.out.println("");
			//System.out.println(Arrays.toString(arranged));
		}
		
		
		
	}
}

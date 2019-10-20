package a2adept;

import java.util.Scanner;

public class A2Adept {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		process(s);

	}

	public static void process(Scanner s) {
		int bin_width = s.nextInt();
		int pic_width = s.nextInt();
		int pic_height = s.nextInt();
		int pic_total = pic_width * pic_height;
		int pixel_intensities[] = new int[pic_total];
		int num_bins = 0;
		if(100%bin_width == 0) {
			num_bins = 100/bin_width;
		} else {
			num_bins = (100/bin_width)+1;
		}
		
		int sort_intensities[] = new int[num_bins];
		for (int i = 0; i < pic_total; i++) {
			pixel_intensities[i] = s.nextInt();
		}

		
			for (int i = 0; i < pixel_intensities.length; i++) {
				//System.out.println(i);
				sort_intensities[pixel_intensities[i]/bin_width] += 1;
			
					//System.out.println(j);
				}
			
		
		
		
		for(int i=0;i<sort_intensities.length;i++) {
			
			String final_stars = star_display(sort_intensities[i], pic_total);
			int starting_value = (bin_width)*i;
			if(starting_value<10) {
				System.out.println(" "+starting_value + ":"+final_stars);
			} else {
			System.out.println(starting_value+":"+final_stars);
			}
			
		}
			
			
		}
		
	
	
	public static String star_display(int count, int pic_total) {
		double percentage = 0.0;
		int num_stars=0;
		percentage = 100*((double)count)/((double)pic_total);
		num_stars = (int)(percentage + 0.5);
		String stars ="";
		//String stars[] = new String[num_stars];
		for(int j=0; j<num_stars; j++) {
			stars += "*";
		}
		return stars;
		
	}

}

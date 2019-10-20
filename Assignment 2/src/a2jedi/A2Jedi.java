package a2jedi;

import java.util.Arrays;
import java.util.Scanner;

public class A2Jedi {

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
			
		
		
		
		int final_stars[] = new int [num_bins];
		//int longest;
		int most_stars = 0;
		//System.out.println("before loop");
		for(int i=0;i<num_bins;i++) {
			final_stars[i] = star_display(sort_intensities[i], pic_total);
			/*
			System.out.println(final_stars[i]);
			System.out.println(final_stars[i].length());
			System.out.println(most_stars);
			*/
			if (final_stars[i]>most_stars) {
				most_stars = final_stars[i];
				//longest = i;
				//System.out.println("look here");
			}
			
		}
		//System.out.println(final_stars.length);
		//System.out.println(Arrays.toString(final_stars));
		
		for(int i=most_stars; i>0;i--) {
			for(int j=0; j<num_bins; j++) {
				if(final_stars[j]>=i) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println();
			
		}
		for(int i=0; i<num_bins; i++) {
		 System.out.print("-");
		}
		System.out.println();
		
		for(int i=0; i<num_bins; i++) {
			int starting_value = (bin_width)*i;
			if(starting_value<10) {
				System.out.print(" ");
			} else {
				System.out.print(starting_value/10);
			}
		}	
		System.out.println();
		for(int i=0; i<num_bins; i++) {
			int starting_value = (bin_width)*i;
			System.out.print(starting_value%10);
		}
		}
			
			//System.out.println("in loop");
			
		
			
		/*
		
		String monster_array[][] = new String [num_bins][most_stars];
		String difference_string = "";
		for(int i=0;i<num_bins; i++) {
			for(int j=0; j<most_stars;j++) {
				int difference = most_stars - final_stars[j].length();
				
				for(int k=0; k<difference; k++) {
					difference_string +=" ";
				}
				monster_array[i][j] = difference_string+final_stars[j]+"-";
				int starting_value = (bin_width)*i;
				if(starting_value<10) {
					monster_array[i][j] += "0"+starting_value;
			} else {
				monster_array[i][j] += starting_value;
			}
			
			}
		}
		
		for(int i=0; i<most_stars;i++) {
			String printline = "";
			for(int j=0; j<num_bins;j++) {
				printline += monster_array[j][i];
			}
			System.out.println(printline);
		}
		
		String line[] = new String[num_bins];	
		for(int i=0; i<num_bins; i++) {
			line[i] = "-";
			
		}
		System.out.println(line);
		
			
			
		}
	*/	
	
	
	public static int star_display(int count, int pic_total) {
		double percentage = 0.0;
		int num_stars=0;
		percentage = 100*((double)count)/((double)pic_total);
		num_stars = (int)(percentage + 0.5);
		int stars =0;
		//String stars[] = new String[num_stars];
		for(int j=0; j<num_stars; j++) {
			stars += 1;
		}
		return stars;
		
	}

}

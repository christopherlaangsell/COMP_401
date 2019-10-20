package a1adept;

import java.util.Scanner;

public class A1Adept {
	
	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		process(s);
		
	}
	
	public static void process(Scanner s) {
		// Put your code here.
		
		double AG_wt = .4;
		double PG_wt = .15;
		double M_wt = .2;
		double F_wt = .25;
		
		
		int num_assignments = s.nextInt();
		int[] a_value = new int[num_assignments];
		for( int i=0; i<num_assignments; i++) {
			a_value[i]= s.nextInt();	
		}
		
		int pot_part_pnts = s.nextInt();
		int class_size = s.nextInt();
		
		
		for(int i=0; i<class_size; i++) {
		String First_Name = s.next();
		String Last_Name = s.next();
		int Participation_Points = s.nextInt();
		
		double[] a_points = new double[num_assignments];
		for( int j=0; j<num_assignments; j++) {
			a_points[j] = s.nextDouble();
		}
		
		double Midterm = s.nextDouble();
		double Final_Exam = s.nextDouble();
		
		
		double part_perc = 100*(Participation_Points/(pot_part_pnts*.8));
		if(part_perc > 100.0 ) {
			part_perc = 100.0;
		}
		
		double a_perc;
		double a_total=0;
		for( int j=0; j<num_assignments; j++) {
			a_total += a_value[j];
		}
		double a_points_rec = 0;
		for( int j=0; j<num_assignments; j++) {
			a_points_rec += a_points[j];
		}
		
		a_perc = 100*(a_points_rec/a_total);
		
		double WA = a_perc*AG_wt+
				part_perc*PG_wt + Midterm*M_wt + 
				Final_Exam*F_wt;
		
		char first_initial = First_Name.charAt(0);
		String Letter_Grade;
		if (WA >= 94.0) {
			Letter_Grade = "A";
		} else if (90.0 <= WA && WA < 94.0) {
			Letter_Grade = "A-";
		} else if (86.0 <= WA && WA < 90.0 ) {
			Letter_Grade = "B+";
		} else if (83.0 <= WA && WA < 86.0 ) {
			Letter_Grade = "B";
		} else if (80.0 <= WA && WA < 83.0 ) {
			Letter_Grade = "B-";
		} else if (76.0 <= WA && WA < 80.0) {
			Letter_Grade = "C+";
		} else if (73.0 <= WA && WA < 76.0 ) {
			Letter_Grade = "C";
		} else if (70.0 <= WA && WA < 73.0 ) {
			Letter_Grade = "C-";
		} else if (65.0 <= WA && WA < 70.0 ) {
			Letter_Grade = "D+";
		} else if (60.0 <= WA && WA < 65.0 ) {
			Letter_Grade = "D";
		} else {
			Letter_Grade = "F";
		}
		
		System.out.println(first_initial + ". " + Last_Name + " " + Letter_Grade); 
		
		
		}
	} 

}

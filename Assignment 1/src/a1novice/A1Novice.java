package a1novice;

import java.util.Scanner;

public class A1Novice {

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
		int class_size = s.nextInt();

		System.out.println("\n");
		for (int i=0; i<class_size; i++ )
		{ 
		String First_Name = s.next();
		String Last_Name = s.next();
		double Assignment_Grade = s.nextDouble();
		double Participation_Grade = s.nextDouble();
		double Midterm = s.nextDouble();
		double Final_Exam = s.nextDouble();
		double WA = Assignment_Grade*AG_wt+
				Participation_Grade*PG_wt + Midterm*M_wt + 
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

package a1jedi;

import java.util.Scanner;

public class A1Jedi {

	// Do not change the main method.	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);

		process(s);

	}

	public static void process(Scanner s) {
		// Put your code here.
		
		//A1 adept inputs - midterm and final
		
		
		
		double AG_wt = .4;
		double PG_wt = .15;
		double M_wt = .2;
		double F_wt = .25;
		double total_midterm=0;
		double total_final=0;
		int Midterm=0;
		int Final_Exam=0;
		int a_sum=0;
		int A=0,Am=0,Bp=0,B=0,Bm=0,Cp=0,C=0,Cm=0,Dp=0,D=0,F=0;
		
		int num_assignments = s.nextInt();
		int[] a_value = new int[num_assignments];
		for( int i=0; i<num_assignments; i++) {
			int d = s.nextInt();
			a_sum += d;
			a_value[i] = d;	
		}
		
		int pot_part_pnts = s.nextInt();
		int class_size = s.nextInt();
		
		int[] midterm_scores = new int[class_size];
		int[] final_scores = new int[class_size];
		int[] part_points = new int[class_size];
		double[] a_points = new double[class_size];
		double[] adjusted_midterm_scores = new double[class_size];
		double[] adjusted_final_scores = new double[class_size];
		
		
		for(int i=0; i<class_size; i++) {
		String First_Name = s.next();
		String Last_Name = s.next();
		String[] name = new String[class_size];
		name[i] = First_Name + Last_Name;
			
		int Participation_Points = s.nextInt();
		part_points[i] = Participation_Points;
		
		double sum=0;
		for( int j=0; j<num_assignments; j++) {
			 sum += s.nextDouble();
		}
		
		a_points[i] = sum;
		
		
		
		Midterm = s.nextInt();
		midterm_scores[i] = Midterm;
		total_midterm += Midterm;
		
		
		Final_Exam = s.nextInt();
		final_scores[i] = Final_Exam;
		total_final += Final_Exam;
		}
		
		
		double avg_midterm = total_midterm/class_size;
		double avg_final = total_final/class_size;
		//System.out.println(avg_final);
		
		
		
		
		
		
		//midterm deviation
		double sum = 0.0;
		double midterm_deviation = 0;
		for(int i=0; i<class_size; i++) {
			
			sum += (midterm_scores[i]-avg_midterm)*(midterm_scores[i]-avg_midterm);
			double second_mean = sum/class_size;
			midterm_deviation = Math.sqrt(second_mean);
			//System.out.println(avg_midterm);
			//System.out.println(sum);
		}
		
		//System.out.println(midterm_deviation);
		//System.out.println(avg_midterm);
		
		//final deviation
		 sum = 0;
		double final_deviation=0;
		for(int i=0; i<class_size; i++) {
			
			sum += Math.pow(final_scores[i]-avg_final,2);
			double second_mean = sum/class_size;
			final_deviation = Math.sqrt(second_mean);
		}
		
		//System.out.println(final_deviation);
		
		//Normalized = (Raw - Average) / StdDev
			for(int i=0; i<class_size; i++) {
			double MidRaw = (double)midterm_scores[i];
			double M_normd = (MidRaw-avg_midterm)/midterm_deviation;
			
			double FinalRaw = (double)final_scores[i];
			double F_normd = (FinalRaw-avg_final)/final_deviation;
			
			double low_normal=0;
			double high_normal=0;
			double low_curved=0;
			double high_curved=0;
			
			//System.out.println(F_normd);
			
			/*if(M_normd >= 2.0) {
				low_normal = 2.0;
				high_normal= 2.0;
				low_curved = 100.0;
				 high_curved =	100.0;
				 
				
			} */ if(M_normd > 1.0 && M_normd <2.0) {
				 low_normal = 1.0;
				 high_normal = 2.0;
				 low_curved = 94.0;
				 high_curved = 100.0;
			} else if(M_normd == 1.0) {
				low_normal = 1.0;
				high_normal= 1.0;
				low_curved = 94.0;
				 high_curved =	94.0;
			} else if(M_normd > 0.0 && M_normd <1.0) {
				 low_normal = 0.0;
				 high_normal = 1.0;
				 low_curved = 85.0;
				 high_curved = 94.0;
			} else if(M_normd == 0.0) {
				low_normal = 0.0;
				high_normal= 0.0;
				low_curved = 85.0;
				 high_curved =	85.0;
			} else if(M_normd > -1.0 && M_normd <0.0) {
				 low_normal = -1.0;
				 high_normal = 0.0;
				 low_curved = 75.0;
				 high_curved = 85.0;
			} else if(M_normd == -1.0) {
				low_normal = -1.0;
				high_normal= -1.0;
				low_curved = 75.0;
				 high_curved =	75.0;
			} else if(M_normd > -1.5 && M_normd <-1.0) {
				 low_normal = -1.5;
				 high_normal = -1.0;
				 low_curved = 65.0;
				 high_curved = 75.0;
			} else if(M_normd == -1.5) {
				low_normal = -1.5;
				high_normal= -1.5;
				low_curved = 65.0;
				 high_curved =	65.0;
			} else if(M_normd > -2.0 && M_normd <-1.5) {
				 low_normal = -2.0;
				 high_normal = -1.5;
				 low_curved = 55.0;
				 high_curved = 65.0;
			} else if(M_normd == -2.0) {
				low_normal = -2.0;
				high_normal= -2.0;
				low_curved = 55.0;
				 high_curved =	55.0;
			} else if(M_normd > -3.0 && M_normd <-2.0) {
				 low_normal = -3.0;
				 high_normal = -2.0;
				 low_curved = 30.0;
				 high_curved = 55.0;
			} else if(M_normd == -3.0) {
				low_normal = -3.0;
				high_normal= -3.0;
				low_curved = 30.0;
				 high_curved =	30.0;
			} else if(M_normd > -4.0 && M_normd < -3.0 ) {
				 low_normal = -4.0;
				 high_normal = -3.0;
				 low_curved = 0.0;
				 high_curved = 30.0;
			} /*else if(M_normd <= -4.0) {
				low_normal = -4.0;
				high_normal = -4.0;
				low_curved = 0.0;
				high_curved = 0.0;
			}*/
			
			double curved_score_M = 0;
			if(M_normd<2.0 && M_normd>-4.0) {
			 curved_score_M = (((M_normd-low_normal)/(high_normal - low_normal))*(high_curved-low_curved)) + low_curved;
			
			} else if(M_normd<=-4.0) {
				 curved_score_M = 0;
			} else if(M_normd>=2.0) {
				  curved_score_M = 100;
			}
			
			/*if(F_normd >= 2.0) {
				 low_normal = 2.0;
				 high_normal= 2.0;
				 low_curved = 100.0;
				 high_curved =	100.0;
			} */ if(F_normd > 1.0 && F_normd <2.0) {
				 low_normal = 1.0;
				 high_normal = 2.0;
				 low_curved = 94.0;
				 high_curved = 100.0;
			} else if(F_normd == 1.0) {
				 low_normal = 1.0;
				 high_normal = 1.0;
				 low_curved = 94.0;
				 high_curved = 94.0;
			} else if(F_normd > 0.0 && F_normd <1.0) {
				 low_normal = 0.0;
				 high_normal = 1.0;
				 low_curved = 85.0;
				 high_curved = 94.0;
			} else if(F_normd == 0.0) {
				 low_normal = 0.0;
				 high_normal = 0.0;
				 low_curved = 85.0;
				 high_curved = 85.0;
			} else if(F_normd > -1.0 && F_normd <0.0) {
				 low_normal = -1.0;
				 high_normal = 0.0;
				 low_curved = 75.0;
				 high_curved = 85.0;
			} else if(F_normd == -1.0) {
				 low_normal = -1.0;
				 high_normal = -1.0;
				 low_curved = 75.0;
				 high_curved = 75.0;
			} else if(F_normd > -1.5 && F_normd <-1.0) {
				 low_normal = -1.5;
				 high_normal = -1.0;
				 low_curved = 65.0;
				 high_curved = 75.0;
			} else if(F_normd == -1.5) {
				 low_normal = -1.5;
				 high_normal = -1.5;
				 low_curved = 65.0;
				 high_curved = 65.0;
			} else if(F_normd > -2.0 && F_normd <-1.5) {
				 low_normal = -2.0;
				 high_normal = -1.5;
				 low_curved = 55.0;
				 high_curved = 65.0;
			} else if(F_normd == -2.0) {
				 low_normal = -2.0;
				 high_normal = -2.0;
				 low_curved = 55.0;
				 high_curved = 55.0;
			} else if(F_normd > -3.0 && F_normd <-2.0) {
				 low_normal = -3.0;
				 high_normal = -2.0;
				 low_curved = 30.0;
				 high_curved = 55.0;
			} else if(F_normd == -3.0) {
				 low_normal = -3.0;
				 high_normal = -3.0;
				 low_curved = 30.0;
				 high_curved = 30.0;
			} else if(F_normd > -4.0 && F_normd < -3.0 ) {
				 low_normal = -4.0;
				 high_normal = -3.0;
				 low_curved = 0.0;
				 high_curved = 30.0;
			} /*else if(F_normd <= -4.0) {
				low_normal = -4.0;
				high_normal = -4.0;
				low_curved = 0.0;
				high_curved = 0.0;
			}*/
			
			//System.out.println(F_normd);
			double curved_score_F = 0;
			if(F_normd<2.0 && F_normd>-4.0) {
				 curved_score_F = (((F_normd-low_normal)/(high_normal - low_normal))*(high_curved-low_curved)) + low_curved;
				
				} else if(F_normd<=-4.0) {
					 curved_score_F = 0;
				} else if(F_normd>=2.0) {
					 curved_score_F = 100;
				}
			
			//System.out.println(curved_score_M);
			
			
			
			adjusted_midterm_scores[i] = curved_score_M;
			
			
			adjusted_final_scores[i] = curved_score_F;
			}
			
			
			for(int i=0; i<class_size; i++) {
			double part_perc = 100*(part_points[i]/(pot_part_pnts*.8));
			if(part_perc > 100.0 ) {
					part_perc = 100.0;
			}
			
			
			double assignment_grade = (a_points[i]/a_sum)*100;
			double WA = part_perc*PG_wt+ assignment_grade*AG_wt+M_wt*adjusted_midterm_scores[i]+F_wt*adjusted_final_scores[i];
			
			//System.out.println(WA);
			//System.out.println(adjusted_final_scores[i]);	
			//System.out.println(adjusted_midterm_scores[i]);
			
			String Letter_Grade;
			if (WA >= 94.0) {
				Letter_Grade = "A";
				A ++;
			} else if (90.0 <= WA && WA < 94.0) {
				Letter_Grade = "A-";
				Am ++;
			} else if (86.0 <= WA && WA < 90.0 ) {
				Letter_Grade = "B+";
				Bp ++;
			} else if (83.0 <= WA && WA < 86.0 ) {
				Letter_Grade = "B";
				B ++;
			} else if (80.0 <= WA && WA < 83.0 ) {
				Letter_Grade = "B-";
				Bm++;
			} else if (76.0 <= WA && WA < 80.0) {
				Letter_Grade = "C+";
				Cp++;
			} else if (73.0 <= WA && WA < 76.0 ) {
				Letter_Grade = "C";
				C ++;
			} else if (70.0 <= WA && WA < 73.0 ) {
				Letter_Grade = "C-";
				Cm ++;
			} else if (65.0 <= WA && WA < 70.0 ) {
				Letter_Grade = "D+";
				Dp ++;
			} else if (60.0 <= WA && WA < 65.0 ) {
				Letter_Grade = "D";
				D ++;
			} else {
				Letter_Grade = "F";
				F++;
			}
			
			String[] Grade = new String[class_size];
			Grade[i] = Letter_Grade;
			//System.out.println(Letter_Grade);
			
			}
			
		System.out.println("A: "+A);	
		System.out.println("A-: "+Am);
		System.out.println("B+: "+Bp);
		System.out.println("B: "+B);
		System.out.println("B-: "+Bm);
		System.out.println("C+: "+Cp);
		System.out.println("C: "+C);
		System.out.println("C-: "+Cm);
		System.out.println("D+: "+Dp);
		System.out.println("D: "+D);
		System.out.println("F: "+F);
		
	}

}

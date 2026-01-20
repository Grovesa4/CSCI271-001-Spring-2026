/***************************************************
*Assignment 1 for CSCI271-001 Spring 2026

*Author: Adrian Groves
*OS: Windows 11
*Compiler: Java 25.0.1
*Date: 2026-01-20

*Purpose: Calculate final grade in class by weighted scores 
**********************************************************/


/*******************************************************************
* I declare and confirm the following:
* - I have not discussed this program code with anyone other than my
* instructor or the teaching assistants assigned to this course.
* - I have not used programming code obtained from someone else,
* or any unauthorised sources, including the Internet, either
* modified or unmodified.
* - If any source code or documentation used in my program was
* obtained from other sources, like a text book or course notes,
* I have clearly indicated that with a proper citation in the
* comments of my program.
* - I have not designed this program in such a way as to defeat or
* interfere with the normal operation of the supplied grading code.
*
* Adrian Groves
* W30625933
********************************************************************/


/************CalculateGrade*****************************************
 * This program calculates the final grade in a class based on
 * weighted scores from assignments, exams, midterms, and tests.
 * It takes inpututed scores, applies weights, and determines the
 * final grade and letter grade.
 * 
 * Parameters:
 * - assignmentWeight: Weight of assignments in percentage
 * - examWeight: Weight of exams in percentage
 * - midtermWeight: Weight of midterms in percentage
 * - testWeight: Weight of tests in percentage
 * - assignmentScore: Score inputed by user for 7 assignments
 * - examScore: Final exam score inputed by user
 * - midtermScore: Midterm score inputed by user
 * - testScore: Score inputed by user for 7 tests
 * 
 * Pre: Weights must sum to 100%
 * - User must input valid scores for assignments, tests, midterm, and exam
 * 
 * Post: Outputs final score and letter grade
 * 
 *******************************************************************/

import java.util.Scanner;

public class CalculateGrade {
        public static void main(String[] args) {
        double score; // Overall score before final calculation
        double assignmentWeight = 30; // Weight of assignments
        double examWeight = 40; // Weight of exams
        double midtermWeight = 20; // Weight of midterms
        double testWeight = 10; // Weight of tests
        double assignmentScore=0; // Intializes Score in assignments = 0
        double assignmentAverage = 0; // Intializes Average score in assignments to 0
        double examScore=0; // Intializes exam score to 0
        double midtermScore=0; // Intializes midterm score to 0
        double testScore=0; // Intializes test scores to 0
        double testAverage = 0; // Intializes Average score in tests to 0
        double finalScore = 0; // Intializes Final Grade in class to 0
        char grade; // Letter Grade
        
        Scanner scanner = new Scanner(System.in); // Create a Scanner object for user input



        System.out.println("Enter scores for 7 assignments:"); //Request user input from console for assignment scores
        for (int i = 1; i <= 7; i++) {
            while (true) {
                try {// Input validation loop to ensure assignment scores are between 0 and 100
                    assignmentScore = Double.parseDouble(scanner.NextLine("Assignment " + i + " score: "));
                    if (assignmentScore < 0 || assignmentScore > 100) {
                        System.out.println("Please enter a valid score between 0 and 100.");
                        continue;
                    }
                    break; // Exit loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            assignmentAverage += assignmentScore;// Sum assignment scores
        }
        assignmentAverage /= 7;// Calculate average assignment score

        System.out.println("Assignment Average: " + assignmentAverage);// Print average assignment score

        System.out.println("Enter scores for 7 tests:"); //Requests user input from console for test scores.
        for (int i = 1; i <= 7; i++) {
            while (true) { // Input validation loop to ensure test scores are between 0 and 100
                try {
                    testScore = Double.parseDouble(scanner.NextLine("Test " + i + " score: "));
                    if (testScore < 0 || testScore > 100) {
                        System.out.println("Please enter a valid score between 0 and 100.");
                        continue;
                    }
                    break; // Exit loop if input is valid
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric value.");
                }
            }

            testAverage += testScore;
        }
        testAverage /= 7;
        System.out.println("Test Average: " + testAverage);
        
        System.out.print("Enter midterm score: "); //Requests user input from console for midterm score.
        while(true) { // Input validation loop to ensure midterm score is between 0 and 100
            try {
                midtermScore = Double.parseDouble(scanner.NextLine());
                if (midtermScore < 0 || midtermScore > 100) {
                    System.out.println("Please enter a valid score between 0 and 100.");
                    continue;
                }
                break; // Exit loop if input is valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
 
        
        System.out.print("Enter exam score: "); //Requests user input from console for final exam score.
        while(true) { // Input validation loop to ensure exam score is between 0 and 100
            try {
                examScore = Double.parseDouble(scanner.NextLine());
                if (examScore < 0 || examScore > 100) {
                    System.out.println("Please enter a valid score between 0 and 100.");
                    continue;
                }
                break; // Exit loop if input is valid
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }

      
        
        score = (examScore * examWeight + midtermScore * midtermWeight + testAverage * testWeight) / 70;// Calculate overall test scores out of 70%
        
        double w = ((score - 60)/20)* 0.3; // Calculate weight for assignments based on score range

        System.out.println(score); // test print for score value
        System.out.println(w);    // test print for weight value    
        

        // Calculate final score based on score ranges
        if (score >= 80) {       
            finalScore = examScore*(examWeight/100) + midtermScore*(midtermWeight/100) + testAverage*(testWeight/100) + assignmentAverage*(assignmentWeight/100);
        } else if (score < 80 && score >= 60) {
            finalScore = (1-w)*score + (w)*assignmentAverage;
        } else if (score < 60) {
            finalScore = score;
        }

        // Determine letter grade based on final score
        if (finalScore >= 90) {    
            grade = 'A';
        } else if (finalScore >= 80) {
            grade = 'B';
        } else if (finalScore >= 70) {
            grade = 'C';
        } else if (finalScore >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }
// Output final score and letter grade
        System.out.println("With the given scores, the final score is: " + finalScore + " and the letter grade is: " + grade);
    scanner.close();
    }
    
}
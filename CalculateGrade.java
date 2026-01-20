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

import java.util.Scanner;

public class CalculateGrade {
    public static void main(String[] args) {
        double score;
        double assignmentWeight = 30;
        double examWeight = 40;
        double midtermWeight = 20;
        double testWeight = 10;
        double assignmentScore = 0;
        double assignmentAverage = 0;
        double examScore = 0;
        double midtermScore = 0;
        double testScore = 0;
        double testAverage = 0;
        double finalScore = 0;
        char grade;
        String StudentName;
        
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine(); // Skip header row

        String line = scanner.nextLine(); // Read CSV data line
        String[] data = line.split(",");

        StudentName = data[0].trim();

        // Parse 7 assignment scores from columns 1-7
        assignmentAverage = 0;
        for (int i = 1; i <= 7; i++) {
            try {
                assignmentScore = Double.parseDouble(data[i].trim());
                if (assignmentScore < 0 || assignmentScore > 100) {
                    System.out.println("Invalid assignment score: " + assignmentScore);
                }
                assignmentAverage += assignmentScore;
            } catch (NumberFormatException e) {
                System.out.println("Error parsing assignment " + i + ": " + data[i]);
            }
        }
        assignmentAverage /= 7;
        System.out.println("Assignment Average: " + assignmentAverage);

        // Parse 7 test scores from columns 8-14
        testAverage = 0;
        for (int i = 8; i <= 14; i++) {
            try {
                testScore = Double.parseDouble(data[i].trim());
                if (testScore < 0 || testScore > 100) {
                    System.out.println("Invalid test score: " + testScore);
                }
                testAverage += testScore;
            } catch (NumberFormatException e) {
                System.out.println("Error parsing test " + (i - 7) + ": " + data[i]);
            }
        }
        testAverage /= 7;
        System.out.println("Test Average: " + testAverage);
        
        // Parse midterm score from column 15
        try {
            midtermScore = Double.parseDouble(data[15].trim());
            System.out.println("Midterm Score: " + midtermScore);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing midterm: " + data[15]);
        }
 
        // Parse exam score from column 16
        try {
            examScore = Double.parseDouble(data[16].trim());
            System.out.println("Exam Score: " + examScore);
        } catch (NumberFormatException e) {
            System.out.println("Error parsing exam: " + data[16]);
        }

        score = (examScore * examWeight + midtermScore * midtermWeight + testAverage * testWeight) / 70;
        
        double w = ((score - 60) / 20) * 0.3;

        System.out.println("Score: " + score);
        System.out.println("Weight: " + w);

        if (score >= 80) {       
            finalScore = examScore * (examWeight / 100) + midtermScore * (midtermWeight / 100) + testAverage * (testWeight / 100) + assignmentAverage * (assignmentWeight / 100);
        } else if (score < 80 && score >= 60) {
            finalScore = (1 - w) * score + w * assignmentAverage;
        } else if (score < 60) {
            finalScore = score;
        }

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

        System.out.println("With the given scores, the final score for " + StudentName + " is: " + finalScore + " and the letter grade is: " + grade);
        scanner.close();
    }
}
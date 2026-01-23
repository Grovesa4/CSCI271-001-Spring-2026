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
********************************************************************/

/********************************************************************
*Description: This program reads student scores from standard input,
*calculates their final grades based on weighted averages of assignments,
*tests, midterm, and final exam.
*
*It handles invalid scores and outputs the final numeric and letter grades.
*
*Pre: It requires input in CSV format with a header row followed by student data rows.
*Each row should contain: A student's name, assignment scores, test scores, midterm score, and final exam score in that order.
*
*Post: For each student, it outputs their name, final numeric grade (rounded to two decimal places), and letter grade.
*
*Return: Outputs to standard output the final grades for each student or error messages for invalid scores.
 */

import java.util.Scanner;

public class CalculateGrade {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Read header row to figure out assignments and tests
        String header = scanner.hasNextLine() ? scanner.nextLine() : "";
        String[] columns = header.split(",");
        int numAssignments = 0;
        int numTests = 0;

        // Assuming columns: Name, A1..An, T1..Tm, Midterm, Exam
        for (int i = 1; i < columns.length - 2; i++) {
            if (columns[i].toLowerCase().startsWith("a")) numAssignments++;
            else if (columns[i].toLowerCase().startsWith("t")) numTests++;
        }

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] data = line.split(",");
            if (data.length < 2 + numAssignments + numTests) {
                System.out.println("Error: Not enough data for student.");
                continue;
            }

            String studentName = data[0].trim();
            boolean invalid = false;

            // Compute assignment average
            double assignmentAverage = 0;
            for (int i = 1; i <= numAssignments; i++) {
                double score = Double.parseDouble(data[i]);
                if (score < 0 || score > 100) {
                    System.out.println("Error: Assignment score for " + studentName + " out of range.");
                    invalid = true;
                    break;
                }
                assignmentAverage += score;
            }
            if (invalid) continue;
            assignmentAverage /= numAssignments;

            // Compute test average
            double testAverage = 0;
            for (int i = 1 + numAssignments; i <= numAssignments + numTests; i++) {
                double score = Double.parseDouble(data[i]);
                if (score < 0 || score > 100) {
                    System.out.println("Error: Test score for " + studentName + " out of range.");
                    invalid = true;
                    break;
                }
                testAverage += score;
            }
            if (invalid) continue;
            testAverage /= numTests;

            // Midterm and Exam
            double midtermScore = Double.parseDouble(data[data.length - 2]);
            double examScore = Double.parseDouble(data[data.length - 1]);

            if (midtermScore < 0 || midtermScore > 100) {
                System.out.println("Error: Midterm score for " + studentName + " out of range.");
                continue;
            }
            if (examScore < 0 || examScore > 100) {
                System.out.println("Error: Exam score for " + studentName + " out of range.");
                continue;
            }

            // Compute E
            double E = (0.4 * examScore + 0.2 * midtermScore + 0.1 * testAverage) / 0.7;

            // Compute final grade G
            double finalScore;
            if (E < 60) {
                finalScore = E;
            } else if (E < 80) {
                double W = ((E - 60) / 20) * 0.3;
                finalScore = (1 - W) * E + W * assignmentAverage;
            } else {
                finalScore = 0.4 * examScore + 0.2 * midtermScore + 0.1 * testAverage + 0.3 * assignmentAverage;
            }

            // Letter grade
            char grade;
            if (finalScore >= 90) grade = 'A';
            else if (finalScore >= 80) grade = 'B';
            else if (finalScore >= 70) grade = 'C';
            else if (finalScore >= 60) grade = 'D';
            else grade = 'F';

            // Output
            System.out.println(
                "Student: " + studentName + 
                ", Final Grade: " + String.format("%.2f", finalScore) + 
                ", Letter Grade: " + grade
            );
        }

        scanner.close();
    }
}

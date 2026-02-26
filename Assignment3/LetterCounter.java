import java.util.Scanner;

/***************************************************
*Assignment 3 for CSCI271-001 Spring 2026

*Author: Adrian Groves
*OS: Windows 11
*Compiler: Java 25.0.1
*Date: 2026-02-19

*Purpose: Test Runtime on multiple problem sets and compare to theoretical time complexity.
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

/*Question 1
Function to read in a String s from the user and then count the number of letters in the string using a recursive approach 
*/

class LetterCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        int letterCount = countLetters(input);
        System.out.println("Number of letters in the string: " + letterCount);
        scanner.close();
    }

/******************countLetters function*****************************
*Description: This function counts the number of letters in a string recursively.
*
*Input: A string s provided by the user.
*
*Pre: The user must input a valid string when prompted. The function will process the string character by character to determine if each character is a letter.
*
*Post: The function returns the total number of letters in the string.
*
*Return: Returns an integer representing the number of letters in the string.
 *****************************************************************/

    public static int countLetters(String s) {
        // Base case: if the string is empty, return 0
        if (s.isEmpty()) {
            return 0;
        }
        // Recursive case: check the first character and then count the rest of the string
        char firstChar = s.charAt(0);
        int countForFirstChar = Character.isLetter(firstChar) ? 1 : 0;
        return countForFirstChar + countLetters(s.substring(1));
    }
}

/*
The runtime of countLetters is O(n) where n is the length of the string. This is because the function processes each character of the string exactly once. The recursive calls are made for each character in the string, and each call performs constant time operations (checking if first character is a letter and recursively calling on the rest of the string). Therefore, the time complexity is linear with respect to the size of the input string.
*/



/* Question 2
    Reads in a string and counts the number of times a specific character appears in the string using recursion 
*/

class CharacterCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        System.out.print("Enter a character to count: ");
        char characterToCount = scanner.next().charAt(0);
        int count = countCharacter(input, characterToCount);
        System.out.println("Number of times '" + characterToCount + "' appears in the string: " + count);
        scanner.close();
    }
/******************countCharacter function*****************************
*Description: This function counts the number of times a specific character appears in a string recursively.
*
*Input: A string s provided by the user and a character to count.
*Pre: The user must input a valid string and a character when prompted. The function will process the string character by character to determine if each character matches the specified character.
*Post: The function returns the total count of the specified character in the string.
*Return: Returns an integer representing the number of times the specified character appears in the string.
 *****************************************************************/

    public static int countCharacter(String s, char characterToCount) {
        // Base case: if the string is empty, return 0
        if (s.isEmpty()) {
            return 0;
        }
        // Recursive case: check the first character and then count the rest of the string
        char firstChar = s.charAt(0);
        int countForFirstChar = (firstChar == characterToCount) ? 1 : 0;
        return countForFirstChar + countCharacter(s.substring(1), characterToCount);
    }
}

/*
The runtime of countCharacter is O(n) where n is the length of the string. This is because the function processes each character of the string exactly once. The recursive calls are made for each character in the string, and each call performs constant time operations (checking if first character matches and recursively calling on the rest of the string). Therefore, the time complexity is linear with respect to the size of the input string.
*/

/* Question 3
    Reads in a series of integers and stores them in an array. Then, it uses recursion to find the maximum interger in the array.
*/

class MaxIntegerFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of integers: ");
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        System.out.println("Enter the integers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        int max = findMax(numbers, 0);
        System.out.println("The maximum integer in the array is: " + max);
        scanner.close();
    }
/******************findMax function*****************************
*Description: This function finds the maximum integer in an array recursively.
*Input: An array of integers provided by the user.
*Pre: The user must input a valid number of integers and the integers themselves when prompted. The function will process the array element by element to determine the maximum value.
*Post: The function returns the maximum integer found in the array. 
*Return: Returns an integer representing the maximum value in the array.
 *****************************************************************/

    public static int findMax(int[] arr, int index) {
        // Base case: if we have reached the end of the array, return the last element
        if (index == arr.length - 1) {
            return arr[index];
        }
        // Recursive case: find the maximum in the rest of the array
        int maxInRest = findMax(arr, index + 1);
        // Return the maximum of the current element and the maximum found in the rest of the array
        return Math.max(arr[index], maxInRest);
    }
}

/* 
The Runtime of the findMax function is O(n) where n is the number of elements in the array. This is because the function processes each element of the array exactly once. The recursive calls are made for each element in the array, and each call performs constant time operations (comparing elements and returning a value). Therefore, the time complexity is linear with respect to the size of the input array.
*/

/* Question 4
Read in a Interger and a digit. Then recursively count how many times the digit appears in the interger
*/

class DigitCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an integer: ");
        long number = scanner.nextLong();
        System.out.print("Enter a digit to count: ");
        long digitToCount = scanner.nextInt();
        long count = countDigit(number, digitToCount);
        System.out.println("Number of times digit '" + digitToCount + "' appears in the integer: " + count);
        scanner.close();
    }
/******************countDigit function*****************************
*Description: This function counts the number of times a specific digit appears in an integer recursively.
*
*Input: An integer number and a digit to count.
*Pre: The user must input a valid integer and a valid single digit when prompted. The function will process the integer digit by digit to determine if each digit matches the specified digit.
*Post: The function returns the total count of the specified digit in the integer.
*Return: Returns an integer representing the number of times the specified digit appears in the integer.
 *****************************************************************/

    public static long countDigit(long number, long digitToCount) {
        // Base case: if the number is 0, return 0
        if (number == 0) {
            return 0;
        }
        // Recursive case: check if the last digit matches and then count for the rest of the number
        long lastDigit = number % 10;
        long countForLastDigit = (lastDigit == digitToCount) ? 1 : 0;
        return countForLastDigit + countDigit(number / 10, digitToCount);
    }
}

/*
The runtime of countDigit is O(d) where d is the number of digits in the integer. This is because the function processes each digit of the integer exactly once. The recursive calls are made for each digit in the integer, and each call performs constant time operations (checking if last digit matches and dividing by 10). Therefore, the time complexity is linear with respect to the number of digits in the input integer.
*/



/* Question 5
Read in a string from the users and display it in reverse order recursively
*/

class StringReverser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String input = scanner.nextLine();
        String reversed = reverseString(input);
        System.out.println("Reversed string: " + reversed);
        scanner.close();
    }
/******************reverseString function*****************************
*Description: This function reverses a string recursively.
*Input: A string provided by the user.
*Pre: The user must input a valid string when prompted. The function will process the string character by character to build the reversed version.
*Post: The function returns the reversed string.    
*Return: Returns a string that is the reverse of the input string.
 *****************************************************************/

    public static String reverseString(String s) {
        // Base case: if the string is empty, return an empty string
        if (s.isEmpty()) {
            return "";
        }
        // Recursive case: take the last character and append it to the reverse of the rest of the string
        char lastChar = s.charAt(s.length() - 1);
        return lastChar + reverseString(s.substring(0, s.length() - 1));
    }
}

/*
The runtime of reverseString is O(n) where n is the length of the string. This is because the function processes each character of the string exactly once. The recursive calls are made for each character in the string, and each call performs constant time operations (accessing the last character and concatenating it). Therefore, the time complexity is linear with respect to the size of the input string.
*/



/*Question 6
Read in a list of interger and store in an array A, Then return the sum of all even numbers in the array 
*/

class EvenSumCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of integers: ");
        int n = scanner.nextInt();
        int[] numbers = new int[n];
        System.out.println("Enter the integers:");
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }
        int evenSum = sumEvenNumbers(numbers, 0);
        System.out.println("The sum of all even numbers in the array is: " + evenSum);
        scanner.close();
    }

/******************sumEvenNumbers function*****************************
*Description: This function calculates the sum of all even numbers in an array recursively.
*Input: An array of integers provided by the user.
*Pre: The user must input a valid number of integers and the integers themselves when prompted.
*Post: The function returns the sum of all even numbers found in the array.
*Return: Returns an integer representing the sum of all even numbers in the array.
*****************************************************************/
    
        public static int sumEvenNumbers(int[] arr, int index) {
            // Base case: if we have reached the end of the array, return 0
            if (index == arr.length) {
                return 0;
            }
            // Recursive case: check if the current element is even and then sum for the rest of the array
            int currentEvenValue = (arr[index] % 2 == 0) ? arr[index] : 0;
            return currentEvenValue + sumEvenNumbers(arr, index + 1);
        }
    }

    /*The runtime of sumEvenNumbers is O(n) where n is the number of elements in the array.
     * This is because the function processes each element of the array exactly once.
     * The recursive calls are made for each element in the array, and each call performs constant time operations (checking if even and adding to sum).
     * Therefore, the time complexity is linear with respect to the size of the input array.
     */
/***************************************************
*Assignment 2 for CSCI271-001 Spring 2026

*Author: Adrian Groves
*OS: Windows 11
*Compiler: Java 25.0.1
*Date: 2026-01-27

*Purpose: Calculate and simplify fractions based on user input. 
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
*Description: This program takes fractions (numerator/denominator),
* simplifies them, and performs arithmetic and power operations.
*
*Pre:
* - Program runs under a Java 25.0.1 runtime on Windows.
* - Inputs are integer numerators and denominators (long range).
* - A denominator of 0 is allowed and represents Infinity/-Infinity or NaN (0/0).
* - Large exponents or repeated multiplications may overflow long values.
*
*Post:
* - Simplified fractions and results of arithmetic, negation, and power
*   operations are printed to standard output.
* - Special cases are represented as "Infinity", "-Infinity", or "NaN".
* - Program exits normally (non-exceptional) unless a runtime error occurs.
**********************************************************************/

public class Fraction{
    private long numerator;
    private long  denominator;


public Fraction(long num){
    this(num, 1);   
}
public Fraction(long num, long denom) {
    //initialize state
    this.numerator = num;
    this.denominator = denom;

    if (denom < 0) {
        this.numerator = -num;
        this.denominator = -denom;
    }
}

public long getNumerator() {
        return numerator;
}
public long getDenominator() {
        return denominator;
    }

//Override toString method to display fraction
@Override
public String toString() {
    if (getDenominator() == 0) {
        if (getNumerator() > 0) return "Infinity";
        if (getNumerator() < 0) return "-Infinity";
        return "NaN";
    }
    if (getDenominator() == 1) {
        return String.valueOf(getNumerator());
    }
    return getNumerator() + "/" + getDenominator();
}

// Calculate the greatest common divisor    
private long GCD(long num, long denom) {
    if (num < 0) { num = -num; }
    while(denom != 0) {
        long Remainder = num % denom;
        num = denom;
        denom = Remainder;  
    }
    if (num == 0) {
        num = 1;
    }
    return num;
}

// Simplify the fraction
public Fraction simplify() {
        long gcd = GCD(getNumerator(), getDenominator());
        return new Fraction(getNumerator() / gcd, getDenominator() / gcd);

    }

// Add another fraction to this fraction
public Fraction add(Fraction other) {
        long newNumerator = this.getNumerator() * other.getDenominator() + other.getNumerator() * this.getDenominator();
        long newDenominator = this.getDenominator() * other.getDenominator();
        return new Fraction(newNumerator, newDenominator).simplify();
    }

// Subtract another fraction from this fraction
public Fraction subtract(Fraction other) {
        long newNumerator = this.getNumerator() * other.getDenominator() - other.getNumerator() * this.getDenominator();
        long newDenominator = this.getDenominator() * other.getDenominator();
        return new Fraction(newNumerator, newDenominator).simplify();
    }

// Multiply the fraction by another fraction
public Fraction multiply(Fraction other) {
        long newNumerator = this.getNumerator() * other.getNumerator();
        long newDenominator = this.getDenominator() * other.getDenominator();
        return new Fraction(newNumerator, newDenominator).simplify();
    }

// Divide the fraction by another fraction

public Fraction divide(Fraction other) {
        long newNumerator = this.getNumerator() * other.getDenominator();
        long newDenominator = this.getDenominator() * other.getNumerator();
        return new Fraction(newNumerator, newDenominator).simplify();
    }

// Negate the fraction
public Fraction negateFraction() {
        return new Fraction(-this.getNumerator(), this.getDenominator()).simplify();
    }

// Raise fraction to an integer power
public Fraction powFraction(int exponent) {
        long newNumerator = this.getNumerator();
        long newDenominator = this.getDenominator();
        for (int i = 1; i < Math.abs(exponent); i++) {
            newNumerator *= this.getNumerator();
            newDenominator *= this.getDenominator();
        }
        if (exponent < 0) {
            long temp = newNumerator;
            newNumerator = newDenominator;
            newDenominator = temp;
        }
        else if (exponent == 0) {
            newNumerator = 1;
            newDenominator = 1;
        }
        return new Fraction(newNumerator, newDenominator).simplify();
    }  

public static void main(String[] args) {
    
// Test fractions including edge cases
    Fraction f1 = new Fraction(1, 2); // 1/2
    Fraction f2 = new Fraction(3, 4); // 3/4
    Fraction f3 = new Fraction(0, 1); // 0
    Fraction f4 = new Fraction(-2, -4); // 1/2 after simplification
    Fraction f5 = new Fraction(5); // 5/1
    Fraction inf = new Fraction(1, 0);  // Infinity
    Fraction ninf = new Fraction(-1, 0); // -Infinity
    Fraction nan = new Fraction(0, 0);  // NaN
   

// Display test fractions
    System.out.println("Test Fractions:");
    System.out.println("f1: " + f1);
    System.out.println("f2: " + f2);
    System.out.println("f3 (zero): " + f3);
    System.out.println("f4 (-2/-4 simplified): " + f4.simplify());
    System.out.println("f5 (5 as fraction): " + f5);
    System.out.println("inf (1/0): " + inf);
    System.out.println("ninf (-1/0): " + ninf);
    System.out.println("nan (0/0): " + nan);
    
// Perform and display results of various operations
    System.out.println("\nArithmetic Operations:");
    System.out.println("fraction1 + fraction2 = " + f1.add(f2));  
    System.out.println("fraction1 - fraction2 = " + f1.subtract(f2));
    System.out.println("fraction1 + fraction3 (zero) = " + f1.add(f3));
    System.out.println("fraction1 - fraction3 (zero) = " + f1.subtract(f3));
    System.out.println("fraction1 * fraction3 (zero) = " + f1.multiply(f3));
    System.out.println("fraction1 / fraction3 (zero) = " + f1.divide(f3));
    System.out.println("fraction1 * fraction2 = " + f1.multiply(f2));     
    System.out.println("fraction1 / fraction2 = " + f1.divide(f2));
    System.out.println("Negation of fraction1 = " + f1.negateFraction());
    System.out.println("Negation of fraction2 = " + f2.negateFraction());
    System.out.println("Negation of fraction4 = " + f4.negateFraction());
    
// Power operations
    System.out.println("\nPower Operations:");    
    System.out.println("fraction 1 raised to the power of 2 = " + f1.powFraction(2));
    System.out.println("fraction 2 raised to the power of -2 = " + f2.powFraction(-2));
    System.out.println("fraction 4 raised to the power of 0 = " + f4.powFraction(0));    
    System.out.println("fraction 2 raised to the power of 40 (expected value of 12157665459056928801/1208925819614629174706176 ) = " + f2.powFraction(40));
    System.out.println("fraction 2 raised to the power of 40 give a wrong value as it exceeds the max value of a long");

// Operations involving special values
    System.out.println("\nOperations involving special values:");
    System.out.println("fraction1 + inf = " + f1.add(inf));
    System.out.println("fraction1 - ninf = " + f1.subtract(ninf)); 
    System.out.println("fraction1 * nan = " + f1.multiply(nan));
    System.out.println("inf + ninf = " + inf.add(ninf));
    
// Edge case operations    
    System.out.println("\nOperations producing special values:");
    System.out.println("1/2 ÷ 0/1  -> expected Infinity: " + f1.divide(f3));
    System.out.println("Negation of 1/0 -> expected -Infinity: " + inf.negateFraction());
    System.out.println("0/1 ÷ 0/1 -> expected NaN: " + f3.divide(f3));
    }
}

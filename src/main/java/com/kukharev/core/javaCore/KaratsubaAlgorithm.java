/*
Written by Alexander Kukharev
https://github.com/ImMedved
 */

/*EN
1. — karatsuba(String num1, String num2) — takes two strings representing large numbers as input,
splits them into smaller parts recursively, multiplies these parts using the Karatsuba algorithm, and returns the product as a string.

2. — addStrings(String num1, String num2) — takes two numeric strings as input, adds them digit by digit, and returns the sum as a string.

3. — subtractStrings(String num1, String num2) — takes two numeric strings as input, subtracts the second number from the first, and returns the difference as a string.

4. — shiftLeft(String num, int positions) — takes a numeric string and an integer as input, shifts the string left by appending zeros, and returns the shifted string.
*/

/*RU
1. — karatsuba(String num1, String num2) — принимает на вход две строки, представляющие большие числа,
рекурсивно разбивает их на более мелкие части, умножает эти части с помощью алгоритма Карацубы и возвращает результат в виде строки.

2. — addStrings(String num1, String num2) — принимает на вход две числовые строки, складывает их по цифрам и возвращает сумму в виде строки.

3. — subtractStrings(String num1, String num2) — принимает на вход две числовые строки, вычитает второе число из первого и возвращает разницу в виде строки.

4. —shiftLeft(String num, intpositions) — принимает в качестве входных данных числовую строку и целое число,
сдвигает строку влево путем добавления нулей и возвращает сдвинутую строку.
 */

package com.kukharev.core.javaCore;

public class KaratsubaAlgorithm {

    // Method to multiply two large numbers represented as strings
    public static String karatsuba(String num1, String num2) {
        int n = Math.max(num1.length(), num2.length());

        // Base case for recursion
        if (n == 1) return Integer.toString(Integer.parseInt(num1) * Integer.parseInt(num2));

        // Ensuring the numbers are of the same length by padding with zeros
        StringBuilder num1Builder = new StringBuilder(num1);
        while (num1Builder.length() < n) num1Builder.insert(0, "0");
        num1 = num1Builder.toString();
        StringBuilder num2Builder = new StringBuilder(num2);
        while (num2Builder.length() < n) num2Builder.insert(0, "0");
        num2 = num2Builder.toString();

        // Split the numbers into halves
        int mid = n / 2;
        String a = num1.substring(0, mid);
        String b = num1.substring(mid);
        String c = num2.substring(0, mid);
        String d = num2.substring(mid);

        // Recursive steps
        String ac = karatsuba(a, c);
        String bd = karatsuba(b, d);
        String abcd = karatsuba(addStrings(a, b), addStrings(c, d));

        // Compute (ac * 10^n) + ((abcd - ac - bd) * 10^(n/2)) + bd
        String product = addStrings(addStrings(
                shiftLeft(ac, 2 * (n - mid)),
                shiftLeft(subtractStrings(abcd, addStrings(ac, bd)), n - mid)
        ), bd);

        return product.replaceFirst("^0+(?!$)", "");  // Removing leading zeros
    }

    // Method to add two numeric strings
    private static String addStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int carry = 0, p1 = num1.length() - 1, p2 = num2.length() - 1;

        while (p1 >= 0 || p2 >= 0 || carry != 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1--) - '0' : 0;
            int x2 = p2 >= 0 ? num2.charAt(p2--) - '0' : 0;
            int sum = x1 + x2 + carry;
            result.append(sum % 10);
            carry = sum / 10;
        }

        return result.reverse().toString();
    }

    // Method to subtract one numeric string from another
    private static String subtractStrings(String num1, String num2) {
        StringBuilder result = new StringBuilder();
        int borrow = 0, p1 = num1.length() - 1, p2 = num2.length() - 1;

        while (p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1--) - '0' : 0;
            int x2 = p2 >= 0 ? num2.charAt(p2--) - '0' : 0;
            int diff = x1 - x2 - borrow;
            if (diff < 0) {
                diff += 10;
                borrow = 1;
            } else borrow = 0;
            result.append(diff);
        }

        return result.reverse().toString().replaceFirst("^0+(?!$)", "");  // Removing leading zeros
    }

    // Method to shift a numeric string to the left by a given number of positions
    private static String shiftLeft(String num, int positions) {
        return num + "0".repeat(Math.max(0, positions));
    }
    /* for{} —> String.repeat()
    private static String shiftLeft(String num, int positions) {
        StringBuilder result = new StringBuilder(num);
        result.append("0".repeat(Math.max(0, positions)));
        return result.toString();
    }
    */

    public static void main(String[] args) {
        String num1 = "12345678901234567890";
        String num2 = "98765432109876543210";
        System.out.println(karatsuba(num1, num2));
    }
}


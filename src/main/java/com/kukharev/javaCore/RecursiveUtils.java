/*
Written by Alexander Kukharev
https://github.com/ImMedved
 */

/*EN
1. printNumbersDescending(int a, int b) — takes two integers as input, prints numbers from the larger to the smaller one, and does not return anything.

2. isPalindrome(int[] array, int start, int end) — accepts an integer array, starting index, and ending index as parameters,
checks if the content of the array is a palindrome, and returns true if it is, otherwise false.

3. findMinPosition(int[] array, int start, int minPos) — accepts an integer array, starting index, and position of the current minimum value as parameters,
finds the position of the smallest element in the array, and returns its index.

4. fillArrayWithOnes(int[][] array, int row, int col) — takes a 2D integer array, row index, and column index as arguments,
fills the array with size 5x5 with the value 1, and does not return anything.

5. genPalindr(String str, boolean even) — takes a string and a boolean flag as parameters,
generates a palindrome string of the specified length depending on the flag, and returns the generated palindrome.

6. genPalindr(int number, boolean even) — accepts an integer and a boolean flag as arguments,
generates a palindrome number of the specified length depending on the flag, and returns the generated palindrome.

7. countSubstringOccurrences(String str, String sub) — receives two strings as input,
counts how many times the substring appears in the main string, and returns the count.

8. reverseString(String str) — takes a string as input, reverses the characters of the string, and returns the reversed string.

9. reverseNumber(int number) — accepts an integer as input, reverses the digits of the number, and returns the reversed number.

10. gcd(int a, int b) — takes two integers as parameters, calculates the greatest common divisor of the two numbers, and returns the result.

11. factorial(int n) — accepts an integer as input, computes the factorial of the number, and returns the factorial value.
*/

/*RU
1. printNumbersDescending(int a, int b) — принимает на вход два целых числа, печатает числа от большего к меньшему и ничего не возвращает.

2. isPalindrome(int[] array, int start, int end) — принимает целочисленный массив, начальный индекс и конечный индекс в качестве параметров,
проверяет, является ли содержимое массива палиндромом, и возвращает true, если это так, в противном случае — false. .

3. findMinPosition(int[] array, int start, int minPos) — принимает в качестве параметров целочисленный массив,
начальный индекс и позицию текущего минимального значения, находит позицию наименьшего элемента в массиве и возвращает его индекс.

4. fillArrayWithOnes(int[][] array, int row, int col) — принимает в качестве аргументов двумерный целочисленный массив,
индекс строки и индекс столбца, заполняет массив размером 5x5 значением 1 и ничего не возвращает.

5. genPalindr(String str, boolean Even) — принимает в качестве параметров строку и логический флаг,
генерирует строку-палиндром указанной длины в зависимости от флага и возвращает сгенерированный палиндром.

6. genPalindr(int Number, Boolean Even) — принимает в качестве аргументов целое число и логический флаг,
генерирует номер палиндрома указанной длины в зависимости от флага и возвращает сгенерированный палиндром.

7. countSubstringOccurrences(String str, String sub) — получает на вход две строки, подсчитывает,
сколько раз подстрока появляется в основной строке, и возвращает счетчик.

8.verseString(String str) — принимает строку в качестве входных данных, меняет местами символы строки и возвращает перевернутую строку.

9.verseNumber(int number) — принимает целое число в качестве входных данных, меняет местами цифры числа и возвращает перевернутое число.

10. gcd(int a, int b) — принимает в качестве параметров два целых числа, вычисляет наибольший общий делитель двух чисел и возвращает результат.

11. Factorial(int n) — принимает целое число в качестве входных данных, вычисляет факториал числа и возвращает значение факториала.
 */

package com.kukharev.javaCore;

public class RecursiveUtils {

    // This method prints all numbers between two given numbers, from the larger to the smaller one.
    public static void printNumbersDescending(int a, int b) {
        if (a < b) return;
        System.out.println(a);
        printNumbersDescending(a - 1, b);
    }

    // This method checks if the content of an int array with 41 elements is a palindrome.
    public static boolean isPalindrome(int[] array, int start, int end) {
        if (start >= end) return true;
        if (array[start] != array[end]) return false;
        if (isPalindrome(array, start + 1, end - 1)) return true;
        else return false;
    }

    // This method finds the position of the smallest element in an int array.
    public static int findMinPosition(int[] array, int start, int minPos) {
        if (start == array.length) return minPos;
        if (array[start] < array[minPos]) minPos = start;
        return findMinPosition(array, start + 1, minPos);
    }

    // This method fills a 5x5 array with the value 1.
    public static void fillArrayWithOnes(int[][] array, int row, int col) {
        if (row == 5) return;
        if (col < 5) {
            array[row][col] = 1;
            fillArrayWithOnes(array, row, col + 1);
        } else fillArrayWithOnes(array, row + 1, 0);
    }

    // This method generates a palindrome string of specified length.
    public static String genPalindr(String str, boolean even) {
        StringBuilder sb = new StringBuilder(str);
        return even ? sb.toString() + sb.reverse().toString() : sb.toString() + sb.reverse().deleteCharAt(0).toString();
    }

    // This method generates a palindrome number of specified length.
    public static int genPalindr(int number, boolean even) {
        String numStr = Integer.toString(number);
        StringBuilder sb = new StringBuilder(numStr);
        if (even) return Integer.parseInt(sb.toString() + sb.reverse().toString());
        else return Integer.parseInt(sb.toString() + sb.reverse().deleteCharAt(0).toString());
    }

    // This method counts how many times a given substring appears in a string.
    public static int countSubstringOccurrences(String str, String sub) {
        if (str.length() < sub.length()) return 0;
        if (str.substring(0, sub.length()).equals(sub)) return 1 + countSubstringOccurrences(str.substring(1), sub);
        else return countSubstringOccurrences(str.substring(1), sub);
    }

    // This method reverses a given string.
    public static String reverseString(String str) {
        if (str.isEmpty()) return str;
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    // This method reverses the digits of a number.
    public static int reverseNumber(int number) {
        return reverseNumberHelper(number, 0);
    }

    private static int reverseNumberHelper(int number, int result) {
        return number == 0 ? result : reverseNumberHelper(number / 10, result * 10 + number % 10);
    }

    // This method finds the greatest common divisor of two numbers.
    public static int gcd(int a, int b) {
        return switch (b) {
            case 0 -> a;
            default -> gcd(b, a % b);
        };
    }

    // This method calculates the factorial of a number.
    public static int factorial(int n) {
        return switch (n) {
            case 0 -> 1;
            default -> n * factorial(n - 1);
        };
    }

    public static void main(String[] args) {
        // Example usage of the methods.

        // printNumbersDescending method
        printNumbersDescending(10, 5);

        // isPalindrome method
        int[] array = {1, 2, 3, 4, 3, 2, 1};
        System.out.println(isPalindrome(array, 0, array.length - 1));

        // findMinPosition method
        int[] array2 = {4, 2, 3, 1, 5};
        System.out.println(findMinPosition(array2, 0, 0));

        // fillArrayWithOnes method
        int[][] array3 = new int[5][5];
        fillArrayWithOnes(array3, 0, 0);
        for (int[] row : array3) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // genPalindr variations
        System.out.println(genPalindr("abc", true));
        System.out.println(genPalindr("abc", false));
        System.out.println(genPalindr(123, true));
        System.out.println(genPalindr(123, false));

        // countSubstringOccurrences method
        System.out.println(countSubstringOccurrences("ababcabcab", "ab"));

        // reverseString and reverseNumber methods
        System.out.println(reverseString("hello"));
        System.out.println(reverseNumber(123450));

        // The greatest common divisor finder
        System.out.println(gcd(48, 18));

        // Factorial of a number finder
        System.out.println(factorial(5));
    }
}

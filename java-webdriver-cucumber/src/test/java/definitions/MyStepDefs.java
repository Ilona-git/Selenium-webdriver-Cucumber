
package definitions;

import com.google.common.base.CharMatcher;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.yaml.snakeyaml.tokens.BlockEndToken;
import pages.Cat;
import pages.Dog;
import pages.Fish;
import pages.Pet;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang3.CharSetUtils.count;
import static org.junit.Assert.assertEquals;

public class MyStepDefs {
    @And("I write my favorite color")
    public void iWriteMyFavoriteColor() {
        String firstName = "ilona";
        String lastName = "H";
        String favoriteColor = "blue";
        String notFavoriteColor = "orange";
        String greeting = "Hi, my name is " + firstName + " " + lastName;
        String text = "my favorite color is " + favoriteColor;
        System.out.println(greeting + ", " + text);
        System.out.println("length: " + greeting.length() + ", " + text.length());
        System.out.println("class: " + greeting + ", " + text.getClass());
        System.out.println("lower: " + greeting.toLowerCase() + ", " + text.toLowerCase());
        System.out.println("upper: " + greeting.toUpperCase() + ", " + text.toUpperCase());
        System.out.println("trim: " + greeting + ", " + text.trim());
        System.out.println("my sring is empty? " + greeting + ", " + text.isEmpty());

        System.out.println(notFavoriteColor == favoriteColor);

    }

    @And("I  perform action with {string} and {string}")
    public void iPerformActionWithAnd(String myvar, String myVAR) {
        System.out.println("1: " + myvar);
        System.out.println("2: " + myVAR);
        System.out.println("myvar upperCase: " + myvar.toUpperCase());
        System.out.println("myVAR upperCase: " + myVAR.toUpperCase());
        System.out.println("myvar length: " + myvar.length());
        System.out.println("myVAR length: " + myVAR.length());
        System.out.println("myvar equals myVAR? " + myvar.equals(myVAR));
        System.out.println("myvar equals ignore case myVAR? " + myvar.equalsIgnoreCase(myVAR));
        System.out.println("myvar contains myVAR? " + myvar.contains(myVAR));

    }

    @And("I do calculation")
    public void iDoCalculation() {

        int a = 10;
        int b = 5;
        int c = a / b;
        System.out.println("division = " + a / b);
        double k = 5.0;
        double d = a / k;
        System.out.println("double division = " + d);

        int num1 = 8;
        int num2 = 10;
        int sum = num1 + num2;
        System.out.println("sum = " + sum);

        int difference = num2 - num1;
        System.out.println("difference is: " + difference);

        int quotient = num2 / num1;
        System.out.println("Quotient is: " + quotient);

        int product = num1 * num2;
        System.out.println("product is: " + product);

    }


    @And("I compare {string} and {string} strings")
    public void iCompareAndStrings(String str1, String str2) {

        if (str1.equals(str2)) {
            System.out.println("I compare str1 and str2  strings equal");
        } else {
            System.out.println("I compare str1 and str2  strings not equal");
        }
    }


    @And("I print url for {string} page")
    public void iPrintUrlForPage(String site) {

        if (site.equals("google")) ;
        {
            System.out.println("https://www.google.com");
        }

        if (site.equals("sample")) ;
        {
            System.out.println("https://skryabin.com/webdriver/html/sample.html");
        }
    }

    @And("I print if number {string} is positive")
    public void iPrintIfNumberIsPositive(String num) {

        int number = 35;

        if (number == 35) {
            System.out.println("number is positive " + number);
        } else if (number <= 0) {
            System.out.println("number is negative " + number);
        }
    }


    @And("I print {string} th day of week")
    public void iPrintThDayOfWeek(String arg0) {
        int day = 7;
        switch (day) {
            case 1:
                System.out.println("Monday");
                break;
            case 2:
                System.out.println("Tuesday");
                break;
            case 3:
                System.out.println("Wednesday");
                break;
            case 4:
                System.out.println("Thursday");
                break;
            case 5:
                System.out.println("Friday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
            case 7:
                System.out.println("Sunday");
                break;
        }
    }

    @Given("I work with arrays")
    public void iWorkWithArrays() {
        String[] fruits = {"kiwi", "apple", "pie", "mango"};
        int[] nums = {1, 2, 3, 1};

        System.out.println(fruits[1]);
        System.out.println(fruits[0]);
        System.out.println(nums[2]);

        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        for (int num : nums) {
            System.out.println("number: " + num);
        }
// declare array different way
        List<String> myFruits = Arrays.asList("kiwi", "apple", "pie", "mango");
        {
            for (String fruits1 : myFruits) {
                System.out.println(fruits1);
            }
        }

        List<Integer> myNums = Arrays.asList(3, 9, 14, 8, 3, 10);
        for (int num : myNums) {
            System.out.println(num);
        }
    }


    @Given("I work with maps")
    public void iWorkWithMaps() {
        Map<String, String> user = Map.of(
                "username", "jdoe",
                "password", "welcome",
                "email", "ilonagorodzetskaya@gmail.com"
        );
        Map<String, String> admin = Map.of(
                "username", "tom",
                "password", "welcome2",
                "email", "admin@gmail.com"
        );
        System.out.println(user);
        System.out.println(admin);
        System.out.println(user.get("username"));
        System.out.println(admin.get("email"));
    }

    @And("I solve coding challenges")
    public void iSolveCodingChallenges() {


//        String[] strArr = {"apple", "orange", "plum"};
        //       Integer[] intArr = {7, 7, 7, 7};
//       int[] arr = {5, 2, 9, 7, 3};
        //      printNumbers(5);
        //       swap(5, 2);

        //       printArr(arr);
        //     printEvenArr(arr);

//              int[] nullArr = null;
        //       System.out.println(isArrEmpty(nullArr));
        //       System.out.println(isArrEmpty(arr));
//
        //       System.out.println(isContain(strArr, "pear"));
        //       System.out.println(isContain(intArr, 7));

//        String str = "Webdriver"; // it shoud be transfer => "revirDbeW
//        printReversed(str);
//       printReversed("WebDriver");
//       System.out.println(reverse("Webdriver"));
//      System.out.println(reverseWords("Hello there!"));

 //       fizzBuzz(20);


  //       System.out.println(maxNum(arr));

//        find2MaxNum(arr);

//        "hello" => "olleh"
//        "aha" <=> "aha"
//        "madam" <=> "madam"
        //       System.out.println(isPalindrome("hello"));
        //       System.out.println(isPalindrome("madam"));


//      System.out.println(isPrime(17));

        //       System.out.println(reversed3rd("WebDriver"));
        //       System.out.println(reversed3rd("Result of reverse"));

        // W e b D r i v e r
        // 1 2 3 4 5 6 7 8 9
        // 9 8 7 6 5 4 3 2 1


//        System.out.println(reverse("WebDriver"));

//        System.out.println(reverseNoVar("WebDriver"));

//        System.out.println(isPalindromeNoVar("WebDriver"));
//        System.out.println(isPalindromeNoVar("civic"));
//        System.out.println(isPalindromeNoVar("noon"));
//          System.out.println(factorial(10));


//      int[] myArray = {90, 8, 55, 1};
//      System.out.println(Arrays.toString(sort(myArray)));


    int[] arr = {5, 3, 2, 5};
    int num = 9;
    int[] arr2 = {4, 8, 4, 1, 11};
    int sum = 5;
    System.out.println(isResultSum(arr2, sum));

        // 1 1 2 3 5 8 13 21 34 55 89 144
        System.out.println(fibFor(1));
        System.out.println(fib(5));
        System.out.println(fib(10));

//        String str = "abcdefa";
//        char ch = 'c';
//        System.out.println("Character " + ch + " occurs " + countChar(str, ch) + " times.");
    }


//Each number in the sequence is the sum of the two numbers that precede it.
// Fibonacci recursive
 long fib(long num) {
     if (num == 0 || num == 1) {
         return num;
     }
     return fib(num - 1) + fib(num - 2);
 }

//Write a function that finds Fibonacci number sequence
    long fibFor(int seq) {
        long prevFib = 0;
        long nextFib = 1;

        for (int i = 1; i < seq; i++) {
            long temp = nextFib;
            nextFib = prevFib + nextFib;
            prevFib = temp;
        }
        return nextFib;
    }

///   Write a function that finds if sum of any of 2 elements in an array
//   result in number
    boolean isResultSum(int[] arr, int sum) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    return true;
                }
            }
        }
        return false;
    }




//Interview coding task (15)
//Write a function that sorts an array
int[] sort(int[] arr) {
    for (int i = 0; i < arr.length; i++) {
        for (int j = i + 1; j < arr.length; j++) {
            if (arr[i] > arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
    return arr;
}




    // Count occurrence of specific character of a string
//   countChar("abcdefa", 'r')
    private static int countChar(String str, char ch) {
        int counter = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ch) {
                counter++;
            }
        }

        return counter;
    }



//Interview coding task
//Write a function that find factorial of a number
//the product of (= the result of multiplying) a whole number and all the whole numbers below it:
long factorial(long num) {
    if (num == 0) {//because  factorial 1 is always 1
        return 1;
    }
    return num * factorial(num - 1);
}






    // Write a function that checks if string is palindrome
    boolean isPalindrome(String word) {
        String reversed = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reversed += word.charAt(i);
        }
        return word.equals(reversed); //it means it is palindrome
    }

    //  Write a function that reverses words in sentence (поменять местами слова)
    String reverseWords(String sentence) {
        String[] arr = sentence.split(" ");
        String reversedSentence = "";
        //i will start from the end of the array
        for (int i = arr.length - 1; i >= 0; i--) {
            reversedSentence += " " + arr[i];
        }
        return reversedSentence;
    }


    // Reverse  word
    String reverse(String str) {
        String reversed = ""; //starts with empty string
        for (int i = str.length() - 1; i >= 0; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }


    void printReversed(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            System.out.print(str.charAt(i)); //print each character
        }
        System.out.println();
    }


    void swap(int a, int b) {
        System.out.println("swap f-n");
        System.out.println("a:" + a);   //print a and b
        System.out.println("b:" + b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a:" + a);
        System.out.println("b:" + b);
    }


    //Write a function that prints all integer array
    void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }


    //Write a function that prints all numbers from 0 up to n
    void printNumbers(int n) {
        for (int i = 0; i <= n; i++) {   //i++ (i =i+1)
            System.out.println(i);
        }
    }

    //Write a function that prints all even numbers from integer
    void printEvenArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                System.out.println(arr[i]);
            }
        }
    }


    //Write a function that checks if array is empty
    boolean isArrEmpty(int[] arr) {
        if (arr == null || arr.length == 0) {
            return true;
        } else {
            return false;

        }
    }

    //Write a function that checks if array contains another element
    boolean isContain(Object[] arr, Object other) {
//        for (int element : arr){

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(other)) {
                return true;
            }
        }
        return false;
    }

    // Write a function that swaps two array elements – 3rd and 5th
    @Given("Array")
    public void array() {
        List<Integer> list = Arrays.asList(5, 2, 9, 7, 3);
        System.out.println(list);
        Collections.swap(list, 2, 4);
        System.out.println(list);
    }


    //  Swap values of firstName and middleName in a Map
    @And("Map")
    public void map() {
        Map<String, String> info = new LinkedHashMap<>();
        info.put("firstName", "John");
        info.put("middleName", "George");

        System.out.println(info);

        String additional = info.get("firstName");
        info.put("firstName", info.get("middleName"));
        info.put("middleName", additional);
        System.out.println(info);

    }

    /*Write a function that accepts integer number and prints
    "divisible by 2" if number is divisible by 2
    "divisible by 5" if element is divisible by 5*/
    @And("Divisible")
    public void divisible() {
        isDivisibleBy2and5(5);
        isDivisibleBy2and5(4);
        isDivisibleBy2and5(8);
    }

    void isDivisibleBy2and5(int num) {
        System.out.println("Is divisible func. Num: " + num);
        if (num % 2 == 0 && num % 5 == 0) {
            System.out.println("Div by 2 and 5");
        } else if (num % 2 == 0) {
            System.out.println("Div by 2");
        } else if (num % 5 == 0) {
            System.out.println("Div by 5");
        } else {
            System.out.println("Not Div by 2 and 5");
        }

    }

/*
Write a function, accepts integer argument
It should print all the numbers up to the argument
BUT:
 if number is multiple of 3, it should print Fizz instead of number
 if number is multiple of 5, it should print Buzz instead of number
 If it is multiple of both 3 and 5, it should print FizzBuzz instead of number
Result for 20:
1 2 Fizz 4 Buzz Fizz 7 8 Fizz Buzz 11 Fizz 13 14 FizzBuzz 16 17 Fizz 19 Buzz
*/

    void fizzBuzz(int max) {
        for (int i = 1; i <= max; i++) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print("FizzBuzz ");
            } else if (i % 3 == 0) {
                System.out.print("Fizz ");
            } else if (i % 5 == 0) {
                System.out.print("Buzz ");
            } else {
                System.out.print(i + " ");  // without ln it will print one line
            }
        }
    }

    //Write a function that finds max number in an array
    int maxNum(int[] arr) {
        if (arr == null) {   //check if array is null
            return 0;
        }

        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (max < arr[i]) {
                max = arr[i];
            }
        }
        return max;
    }


//Write a function that finds two max numbers

    void find2MaxNum(int[] arr) {
        int max1 = Integer.MIN_VALUE; //i start with the min value
        int max2 = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (max1 < arr[i]) {    //if it less then
                max2 = max1;        //  i will assign
                max1 = arr[i];
            } else if (max2 < arr[i]) {
                max2 = arr[i];
            }
        }
        System.out.println("First max: " + max1 + " Second: " + max2);
    }

    //13-14_Q&A
//reversed each 3rd character
    String reversed3rd(String str) {
        String reversed = "";
        int j = 1;      // we add extra variable to count from the end of the string
        for (int i = str.length() - 1; i >= 0; i--) {
            if (j % 3 == 0) {
                reversed += str.charAt(i); //The charAt() method returns the character at the specified index in a string.
            }
            System.out.println(j);
            j++;   // to increment j
        }
        return reversed;
    }

    //How to do check for palindrom without new string?
    boolean isPalindromeNoVar(String word) {
        int j = 0;
        for (int i = word.length() - 1; i >= word.length() / 2; i--) {
            if (word.charAt(i) != word.charAt(j)) {
                return false;
            }
            j++;
        }
        return true;
    }


    //How to revere string without new variable?

    String reverseNoVar(String str) {
        for (int i = str.length() - 1; i >= 0; i--) {
            str += str.charAt(i);
        }
        return str.substring(str.length() / 2);
    }


    //   Write a function that verifies if number is prime
    //   Prime is a number that can only be divided on itself or 1

    boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        if (num % 2 == 0 && num != 2) {
            return false;
        }
        double sqrt = Math.sqrt(num);
        for (int i = 3; i < sqrt; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }





    //cat
    @Given("I run classes")
    public void iRunClasses() {
        // Encapsulation
        // Inheritance
        // Abstraction
        // Polymorphism - static and dynamic

        Pet tom = new Cat("Tom");
        System.out.println("Cat's name: " + tom.getName());
        tom.walk();
        tom.eat("fish");
        // will go to Cat's implementation of speak() - dynamic POLYMORPHISM
        tom.speak();

        Pet charlie = new Dog();
        charlie.setName("Charlie");
        System.out.println("Dog's name: " + charlie.getName());
        charlie.walk();
        charlie.eat("bone");
        // will go to Dog's implementation of speak() - dynamic POLYMORPHISM
        charlie.speak();


        Pet Gold = new Fish();
        Gold.setName("Gold");
        System.out.println("Fishes name: " + Gold.getName());
        Gold.eat("worm");
        Gold.setName("Flinn");
        System.out.println("Fishes name: " + Gold.getName());
    }


}










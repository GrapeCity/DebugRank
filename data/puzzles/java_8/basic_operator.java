import java.util.*;

// 
// Welcome to DebugRank.
// 
// The purpose of the Basic Operator class is to return the sum
// of two integers.
// 
// Review the code below and find where the bug exists.
// 
// When you've found and fix the bug simply press the button
// in the bottom right hand corner to compile and test your code.
// 
// View test case results and compile error messages
// in the tab in the upper right hand corner.
//
//
public class BasicOperator
{
	 //
	 // Returns the sum of two integers.
	 //
	 // @param a the first integer.
	 // @param b the second integer.
	 // @return the sum of a and b.
	 //
	static int addIntegers(int a, int b)
	{
		//hint: this is the bug.
		return a - b;
	}

	//
	// Don't edit any code inside this method.
	// Necessary for HackerRank cloud compiling.
	// 
	// @param args
	//
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		String[] numbers = in.nextLine().split(",");

		int a = Integer.parseInt(numbers[0]);
		int b = Integer.parseInt(numbers[1]);

		int sum;

		sum = addIntegers(a, b);

		System.out.println(sum);
	}
}

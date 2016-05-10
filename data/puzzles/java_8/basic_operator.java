import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

//
// Return the sum of two integers.
//
public class Solution
{
	//
	// Bugs can be found in this method only.
	//
	static int solveMeFirst(int a, int b)
	{
		return a - b;
	}

	//
	// Don't edit any code of this method, necessary for HackerRank API
	//
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		String[] numbers = in.nextLine().split(",");

		int a = Integer.parseInt(numbers[0]);
		int b = Integer.parseInt(numbers[1]);

		int sum;

		sum = solveMeFirst(a, b);

		System.out.println(sum);
	}
}

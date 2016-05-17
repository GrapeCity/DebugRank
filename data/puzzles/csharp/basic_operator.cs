using System;
using System.Collections.Generic;
using System.IO;

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
class BasicOperator
{
	//
	// Returns the sum of two integers.
	//
	static int AddIntegers(int a, int b)
	{
		return a - b;
	}

	//
	// Don't edit any code inside this method.
	// Necessary for HackerRank cloud compiling.
	// 
	static void Main(String[] args)
	{
		String[] numbers = Console.ReadLine().Split(',');
		
		int val1 = Convert.ToInt32(numbers [0]);
		int val2 = Convert.ToInt32(numbers [1]);

		int sum = AddIntegers(val1,val2);

		Console.WriteLine(sum);
	}
}

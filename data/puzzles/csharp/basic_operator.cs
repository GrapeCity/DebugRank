using System;
using System.Collections.Generic;
using System.IO;

class Solution
{
	static int solveMeFirst(int a, int b)
	{
		return a - b;
	}

	static void Main(String[] args)
	{
		String[] numbers = Console.ReadLine().Split(',');
		
		int val1 = Convert.ToInt32(numbers [0]);
		int val2 = Convert.ToInt32(numbers [1]);

		int sum = solveMeFirst(val1,val2);

		Console.WriteLine(sum);
		}
}

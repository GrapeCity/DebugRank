using System;

//
// The Substring class determines if a substring
// exists between two strings.
//
// But unfortunately the class does not compile ):
// 
// Press the compile button in the bottom right corner
// to get the compile errors in order to fix the code.
//
class Substring
{
	//
	// Determine if a substring exists in two strings.
	// 
	static bool DoesSubstringExist(string stringA, string stringB)
	{
		Char[] stringACharacters = stringA.ToCharArray ();

		foreach (var character in stringACharacters)
		{
			if (stringB.Contains (character))
			{
				return true;
			}
		}

		return false;
	}

	//
	// Don't edit any code inside this method.
	// Necessary for HackerRank cloud compiling.
	// 
	static void Main(String[] args)
	{
		String[] strings = Console.ReadLine().Split(',');

		string stringA = strings [0];
		string stringB = strings [1];

		if (DoesSubstringExist(stringA, stringB))
		{
			Console.WriteLine("substring exists");
		} 
		else
		{
			Console.WriteLine("no substring exists");
		}
	}
}

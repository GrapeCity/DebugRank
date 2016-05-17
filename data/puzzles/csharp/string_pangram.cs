using System;
using System.Collections.Generic;

//
// The StringPangram class determines if a string is a Pangram.
// If the input is a Pangram we will print "pangram".
// If the input is not a Pangram we will print "not pangram".
//
// A Pangram is a sentence that uses every letter
// of the alphabet at least once.
//
// Example, the sentence "The quick brown fox jumps over the lazy dog" is a Pangram.
// The sentence "I am a fox" is not a Pangram.
//
class StringPangram
{
	//
	// Determine if the given input string is a Pangram.
	//
	static bool IsStringPangram(string input)
	{
		HashSet<Char> characterSet = new HashSet<char> ();

		Char[] charArray = input.ToCharArray ();

		foreach (var character in charArray)
		{
			if (character != null)
			{
				if (!characterSet.Contains (Char.ToUpper(character)))
				{
					characterSet.Add (Char.ToUpper(character));
				}
			}
		}

		return characterSet.Count == 25;
	}

	//
	// Don't edit any code inside this method.
	// Necessary for HackerRank cloud compiling.
	// 
	static void Main(String[] args)
	{
		String input = Console.ReadLine();

		if (IsStringPangram (input))
		{
			Console.WriteLine("pangram");
		} 
		else
		{
			Console.WriteLine("not pangram");
		}
	}
}

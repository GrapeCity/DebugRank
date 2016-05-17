import java.util.*;
import java.io.*;

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
public class StringPangram
{
	//
	// Determine if the given input string is a Pangram.
	// 
	// @param input The sentence to check.
	// @return True if the input string is a Pangram.
	//
	public static boolean isStringPangram(String input)
	{
		Set<Character> characterSet = new HashSet<Character>();

		char[] charArray = input.toCharArray();

		for (Character character : charArray)
		{
			if (character != null)
			{
				if (!characterSet.contains(Character.toUpperCase(character)))
				{
					characterSet.add(Character.toUpperCase(character));
				}
			}
		}
		
		return characterSet.size() == 25;
	}
	
	//
	// Don't edit any code inside this method.
	// Necessary for HackerRank cloud compiling.
	// 
	// @param args
	//
	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String input = br.readLine();
		
		if(isStringPangram(input))
		{
			System.out.println("pangram");	
		}
		else
		{
			System.out.println("not pangram");	
		}
	}
}

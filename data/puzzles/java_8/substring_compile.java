import java.util.*;

//
// The Substring class determines if a substring
// exists between two strings.
//
// But unfortunately the class does not compile ):
// 
// Press the compile button in the bottom right corner
// to get the compile errors in order to fix the code.
//
public class Substring
{
	//
	// Determine if a substring exists in two strings.
	// 
	// @param stringA the first string.
	// @param stringB the second string.
	// @return True if a substring exists in both strings.
	//
	public static boolean doesSubstringExist(String stringA, String stringB)
	{
		char[] stringACharacters = stringA.toCharArray();
		
		for(char character : stringACharacters)
 		{
			if(stringB.contains(character))
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
	// @param args
	//
	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);

		String[] strings = in.nextLine().split(",");

		String stringA = strings[0];
		String stringB = strings[1];
		
		if(doesSubstringExist(stringA, stringB))
		{
			System.out.println("substring exists");
		}
		else
		{
			System.out.println("no substring exists");
		}
	}
}

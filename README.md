# DebugRank
DebugRank is an android sample that uses MVP for the architecture to ease unit test coverage.  The app is the gamification 
of debugging code to find X bugs.  

See the published app in [Google Play](https://play.google.com/store/apps/details?id=debugrank.grapecity.com.debugrank&hl=en)

Cloud code compiling / execution is possible by utilizing the HackerRank API found at hackerrank.com/api.

The code editor uses GrapeCity's Xuni FlexGrid for bi directional scrolling and inline editing.

Some technologies used in this sample include:
* Realm
* Dagger2
* Retrofit2
* Picasso
* Gson
* RxJava / RxAndroid
* Butterknife
* Dart
* Mockito
* Robolectric
* Espresso
* SVG Assets

## How to contribute
You can contribute in two ways, the first being updates to the app itself and the second being updates to the languages and puzzles found in the **data** folder of this repository.

### Updates to the app
To get the app up and running all you'll need to do is add your own hacker rank API key in java-lib/com.grapecity.debugrank.javalib.constants.Api class, update the variable `HACKERRANK_API_KEY`.

For your own testing purposes you can fork this repo and change the app to point to your fork by updating `GITHUB_API_OWNER` variable in the Api class as well.

The business logic of the app lives in a java library under module **java-lib**, unit tests for the services, interactors, and presenters also live in this module.

The app module holds android specific logic as well as the view logic.  Both unit tests and espresso tests go here.

### Updates to the languages & puzzles
The languages and puzzles inside the app are pulled directly from the **data** folder of this repository.

**Follow these steps to add a new language**

1. update data/languages.json
2. add a new thumbnail image under data/images/{new language key}.png
3. add a new puzzles json file for that language under data/puzzles/{new language key}.json
4. add a language-puzzle folder under data/puzzles/{new language key}
5. add a basic-operator puzzle under the new folder and to the new json file 

**Step 1: languages.json**

To add support for a new Programming Language you'll need to add the language to the languages.json file found in data/languages.json.  Get the hackerrank code (the code field below) from their api [http://api.hackerrank.com/checker/languages.json](http://api.hackerrank.com/checker/languages.json)

```
[
	{
		"key": "java_8",
		"name": "Java 8",
		"file": ".java",
		"code": 43
	},
	{
		"key": "mynewlanguagekey",
		"name": "My Awesome Language",
		"file": ".cs",
		"code": 9 // this code comes from the hackerrank api http://api.hackerrank.com/checker/languages.json
	}
]
```

**Step 2: data/images**

Now you'll need to add a thumbnail image for that language, add a 128px x 128px png under the **data/images** folder with the same name as the new language key (i.e. mynewlanguagekey.png).  You can find a bunch of awesome language thumbnails in the [konpa/devicon](https://github.com/konpa/devicon) repo.

**Step 3: new language-puzzle json file**

Once a user selects a language they will be presented with a list of puzzles to solve.  These puzzles are pulled in from **data/puzzles/{new language key}.json**.  Create this file for your new language and add a basic-operator puzzle.

```
[
	{
		"key": "basic_operator",
		"name": "Basic Operator",
		"points": 5,
		"time": 180,
		"bugs": 1,
		"testcases":["1,2","2,1"],
		"answers":["3","3"]
	}
]
```



**Step 4: new language-puzzle folder**
The actual code files for the puzzles should be placed under the data/puzzles/{new language key}.  Simply create a new folder to hold puzzles for that language.

**Step 5: add a basic-operator puzzle**
Create a new .cs file (if doing c#) and place it in the data/puzzles/{new language key}.  **Note that the file name needs to match the puzzle key in {new language key}.json.**  Example, data/puzzles/csharp/basic_operator.cs.  Refer to the add new puzzle section below to add the basic operator puzzle for your new language.

**Follow these steps to add a new puzzle**

1. add puzzle metadata
2. add raw code file
3. add description to raw code file
4. add function / method comments to raw code file
5. test code

**Step 1: add puzzle metadata**
update data/puzzles/{language key}.json to include puzzle metadata, Example:

```
[
	{
		"key": "basic_operator",
		"name": "Basic Operator",
		"points": 5,
		"time": 180,
		"bugs": 1,
		"testcases":["1,2","2,1"],
		"answers":["3","3"]
	}
]
```

Puzzle metadata fields:

* **key** - The key field acts as a PK and as the raw code file name.
* **name** - The actual puzzle name displayed to the end user.
* **points** - The number of points the end user is rewarded upon solving.
* **time** - The time limit to solve the puzzle in seconds.  Note round to 60 seconds with a minimum of 60 seconds.
* **bugs** - The total number of bugs in the code the user must fix.
* **testcases** - Array of strings that HackerRank will pass to your main function and your code will process.  Size must be equal to answers size.
* **answers** - Array of strings that will be compared to your program's output in order to determine if test case passes.  Size must be equal to testcases size.

**Step 2: add raw code file**
Add the raw code file under data/puzzles/{language key}/{puzzle key}.{languge extension}. Example, data/puzzles/csharp/basic_operator.cs.

```
using System;
using System.Collections.Generic;
using System.IO;

class BasicOperator
{
	static int AddIntegers(int a, int b)
	{
		//hint: here is the bug
		return a - b;
	}

	static void Main(String[] args)
	{
		String[] numbers = Console.ReadLine().Split(',');
		
		int val1 = Convert.ToInt32(numbers [0]);
		int val2 = Convert.ToInt32(numbers [1]);

		int sum = AddIntegers(val1,val2);

		Console.WriteLine(sum);
	}
}
```

**Step 3: add description to raw code file**
Help the end user better understand the point of the puzzle by supplying a top level description.  Use `\\` style comments as the syntax highlighter is not perfect.  If adding the basic operator puzzle please copy paste the existing top level description used in other languages.

**Step 4: add function / method comments to raw code file**
Help the end user understand even further by suppling additional comments for each method.  Copy paste the following comments for the main function that the HackerRank API uses:

```
//
// Don't edit any code inside this method.
// Necessary for HackerRank cloud compiling.
// 
static void Main(String[] args)
{
}
```

If adding the basic operator puzzle give the user a hint to where the bug is to help them learn the app:

```
static int AddIntegers(int a, int b)
{
	//hint: here is the bug
	return a - b;
}
```

**Step 5: test code**
You code should expect a single `String` as the input.  The input will be whatever is entered from the puzzle json metadata file field **testcases**.  If multiple inputs are required you'll need to split them by delimiter, ideally a comma will suffice.  Example:

```
//json file has -> "testcases":["1,2","2,1"]
String[] numbers = Console.ReadLine().Split(',');
		
int val1 = Convert.ToInt32(numbers [0]);
int val2 = Convert.ToInt32(numbers [1]);
```

Make sure to output to the console / system the answer to be checked against the puzzle json metadata file field **answers**.

```
//json file has -> "answers":["3","3"]
int sum = AddIntegers(val1,val2);

Console.WriteLine(sum);
```

Now you should be able to test your code in your favorite IDE by simulating input manually through the console window.  To test fully you should have previously forked this repo.

Update the variable `HACKERRANK_API_KEY` in class `java-lib/com.grapecity.debugrank.javalib.constants.Api` to your HackerRank API key.  Aquire a new HackerRank API key at [https://www.hackerrank.com/api](https://www.hackerrank.com/api).

Update the variable `GITHUB_API_OWNER` in class `java-lib/com.grapecity.debugrank.javalib.constants.Api` to your Github name.

You should now be able to launch the app to test against your forked repo.  **Note** that Retrofit will automatically be caching responses.  You'll need to clear the DebugRank local app cache in order to retrive updated code files during testing iterations.

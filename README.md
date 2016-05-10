# DebugRank
Debug Rank is an android sample that uses MVP for the architecture to ease unit test coverage.  The app is the gamification 
of debugging code to find X bugs.  

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
The actual code files for the puzzles should be placed under the data/puzzles/{new language key}.  Create a new .cs file (if doing c#) and place it in this directory.

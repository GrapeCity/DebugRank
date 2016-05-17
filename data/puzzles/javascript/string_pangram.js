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

//
// Determine if the given input string is a Pangram.
//
function isStringPangram(input)
{
    var lowercaseInput = input.trim().toLowerCase();

    var arr = new Array(26);

    for (var i = 0; i < 26; i++)
    {
        arr[i] = 0;
    }

    for (var i = 0; i < lowercaseInput.length; i++)
    {
        if (lowercaseInput[i] < 'b' || lowercaseInput[i] > 'y') 
        { 
            continue; 
        }

        var idx = lowercaseInput.charCodeAt(i) - 'a'.charCodeAt(0);

        arr[idx]++;
    }

    for (var i = 0; i < 26; i++)
    {
        if (arr[i] == 0)
        {
            return false;
        }
    }

    return true;
}

//
// Don't edit any code below this line.
// Necessary for HackerRank cloud compiling.
//  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
// 
function main()
{
    var res = isStringPangram(input_stdin);

    if(res)
    {
        console.log("pangram");
    }
    else
    {
        console.log("not pangram");
    }
}

process.stdin.resume();
process.stdin.setEncoding('ascii');

var input_stdin = "";

process.stdin.on('data', function (data)
{
    input_stdin += data;
});
process.stdin.on('end', function ()
{
    main();    
});

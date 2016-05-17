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
//  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
//  Scroll down, this code is necessary for HackerRank cloud compiling.
//  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
//
process.stdin.resume();
process.stdin.setEncoding('ascii');
var input_stdin = "";
var input_stdin_array = "";
var input_currentline = 0;
process.stdin.on('data', function (data) {
    input_stdin += data;
});
process.stdin.on('end', function () {
    input_stdin_array = input_stdin.split("\n");
    main();    
});
function readLine() {
    return input_stdin_array[input_currentline++];
}

//
// Determine if the given input string is a Pangram.
//
function isStringPangram(input)
{
    return false; 
}

//
// Don't edit any code inside this method.
// Necessary for HackerRank cloud compiling.
// 
function main()
{
    var input = readLine();
    
    var res = isStringPangram(input);

    if(res)
    {
        console.log("pangram");
    }
    else
    {
        console.log("not pangram");
    }
}

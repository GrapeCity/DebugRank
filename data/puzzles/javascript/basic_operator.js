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

//
// Returns the sum of two integers.
//
function addIntegers(a, b)
{
    //hint: here is the bug
    return a - b; 
}

//
// Don't edit any code below this line.
// Necessary for HackerRank cloud compiling.
//  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
// 
function main()
{
    var numbers = input_stdin.split(',');
    
    var a = parseInt(numbers[0]);
    var b = parseInt(numbers[1]);

    var res = addIntegers(a, b);

    console.log(res);
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
//
// The Substring class determines if a substring
// exists between two strings.
//
// But unfortunately the class does not compile ):
// 
// Press the compile button in the bottom right corner
// to get the compile errors in order to fix the code.
//

//
    // Determine if a substring exists in two strings.
    // 
function doesSubstringExist(stringA, stringB)
{
    var map = {};

    for (int i = 0; i < stringA.length; i++)
    {
        map[stringA[i]] = true;
    }

    for (var i = 0; i < stringB.length; i++)
    {
        if (map[stringB[i]])
        {
            return true;
        }
    }
    return false;
}

//
// Don't edit any code below this line.
// Necessary for HackerRank cloud compiling.
//  - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
// 
function main()
{
    var strings = input_stdin.split(',');
    
    var stringA = strings[0];
    var stringB = strings[1];

    var res = doesSubstringExist(stringA, stringB);

    if(res)
    {
        console.log("substring exists");
    }
    else
    {
        console.log("no substring exists");
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

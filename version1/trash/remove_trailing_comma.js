#!/usr/bin/env node
 
// Reads JSON from stdin and writes equivalent
// nicely-formatted JSON to stdout.
 
var stdin = process.stdin,
    stdout = process.stdout,
    inputChunks = [];
 
stdin.resume();
stdin.setEncoding('utf8');
 
stdin.on('data', function (chunk) {
    inputChunks.push(chunk);
});
 
stdin.on('end', function () {
    var inputJSON = inputChunks.join();
    if (endsWith(inputJSON, "},\n")) {
    	inputJSON = inputJSON.slice(0, -3);
    }
    stdout.write(inputJSON);
    stdout.write('\n');
});

function endsWith(str, suffix) {
    return str.indexOf(suffix, str.length - suffix.length) !== -1;
}
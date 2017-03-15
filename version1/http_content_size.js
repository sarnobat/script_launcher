#!/usr/bin/env node
require('epipebomb')();
var parser = require('http-string-parser');
var readline = require('readline');
var devnull = require('dev-null');

var rl = readline.createInterface({
  input: process.stdin,
  output: devnull()
});

var contentLengthConsumed = 1;

rl.on('line', function(line){
	//console.log('http_content_size.js : open()');
	var contentLengthDelta = getByteLength(line);
	contentLengthConsumed += contentLengthDelta;
	process.stdout.write('\n');
	
}).on('close', function() {
	// This won't get called. The client terminates the connection with an "error"
	process.stdout.write(contentLengthConsumed + '\n');
//	console.log('http_content_size.js : close()');
	contentLengthConsumed = 1;
});

function getByteLength(normal_val) {
    // Force string type
    normal_val = String(normal_val);

    var byteLen = 0;
    for (var i = 0; i < normal_val.length; i++) {
        var c = normal_val.charCodeAt(i);
        byteLen += c < (1 <<  7) ? 1 :
                   c < (1 << 11) ? 2 :
                   c < (1 << 16) ? 3 :
                   c < (1 << 21) ? 4 :
                   c < (1 << 26) ? 5 :
                   c < (1 << 31) ? 6 : Number.NaN;
    }
    return byteLen;
}
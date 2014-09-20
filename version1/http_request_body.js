#!/usr/bin/env node
var parser = require('http-string-parser');
var readline = require('readline');
var devnull = require('dev-null');

var rl = readline.createInterface({
  input: process.stdin,
  output: devnull()
});

var bodyBegan = false;
var contentLengthConsumed = 1;
var contentLength;
rl.on('line', function(line){

	var resourceLine = line.match(/.*\/\?param1=(.*)HTTP\/1.1/);
	if (resourceLine != null && resourceLine.length > 0) {
		var paramValue = resourceLine[1];
		//console.log("Param: " + paramValue);
		//process.stdout.write(paramValue + '\n');
	} else {
		//console.log("Not a resource line: " + line +"\n");
	}

//	console.warn('http_request_body.js [DEBUG] ' + line);
	contentLengthLine = line.match(/Content-Length: (.*)/);
	//console.warn("content length in header: " + contentLength);
	//console.warn("content length consumed: " + contentLengthConsumed);
	

	if (contentLengthLine != null && contentLengthLine.length > 0) {
		//console.warn(contentLengthLine);
		contentLength = contentLengthLine[1];
		//console.warn(contentLength);
	}
	if (bodyBegan) {
		//	console.log('4');
		process.stdout.write(line + '\n');
		var contentLengthDelta = getByteLength(line);
		contentLengthConsumed += contentLengthDelta;
		//console.log(line);
//		console.warn('consumed ' + contentLengthConsumed);
		if (contentLengthConsumed >= contentLength) {
			//console.log('11');
//			console.warn('exiting');
			bodyBegan = false;
			contentLengthConsumed = 0;
			contentLength = 0;
			// If we exit, the process doesn't start again for followup requests
			// if we use netcat's --keep-alive setting. We have to use a loop
			process.exit(0);
		}
	}
	//console.log('10');
	bodyBegan = (bodyBegan || line == null || line == '');
	
}).on('close', function() {
	console.warn('closing ');
  //console.log('Have a great day!');
  //bodyBegan = false;
  //process.exit(0);
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

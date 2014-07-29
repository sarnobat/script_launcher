#!/usr/bin/env node
var parser = require('http-string-parser');
var readline = require('readline');
var devnull = require('dev-null');

var rl = readline.createInterface({
  input: process.stdin,
  output: devnull()
});

var bodyBegan = false;
rl.on('line', function(line){
	if (bodyBegan) {
		process.stdout.write(line + '\n');
	}
	bodyBegan = (bodyBegan || line == null || line == '');
});

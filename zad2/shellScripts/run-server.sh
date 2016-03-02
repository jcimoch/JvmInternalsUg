#!/bin/bash

echo "You need node.js installed at least in latest stable version";
echo "Attempting to start the server";

cd staticFilesServer
npm install
node main.js

exit
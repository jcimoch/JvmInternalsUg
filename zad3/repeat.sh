#!/usr/bin/env bash

for (( i=1; i<=$1; i++ ))
do
    java -cp target/classes pl.jcimoch.ug.Main >> outputTxt.txt
done
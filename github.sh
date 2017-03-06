#!/bin/bash
#@author Maxime GUILLAUME

git pull 
git add .
read -p "define the message of the commit: " stringCom

git commit -m $stringCom
git push

#!/bin/bash
#@author Maxime GUILLAUME

git pull
git add --all

echo 'Enter the commit message:'
read commitMessage

git commit -m "$commitMessage"


git push


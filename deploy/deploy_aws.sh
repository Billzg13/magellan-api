#!/bin/bash

echo "in deploy_aws"
ssh ubuntu@18.157.184.133
echo "succesfull ssh"

eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_rsa

rm -rf magellan-api

git clone git@github.com:Billzg13/magellan-api.git
cd magellan-api
git checkout master
git pull

echo " end of deploy_aws"
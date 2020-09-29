#!/bin/bash

echo "in deploy_aws"
#ssh magellan@18.157.184.133
sshpass -p 'secret12345' ssh magellan@18.157.184.133 <<EOF
eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_rsa
rm -rf magellan-api
git clone git@github.com:Billzg13/magellan-api.git
EOF
echo "succesfull ssh"

eval "$(ssh-agent -s)"
ssh-add ~/.ssh/id_rsa

rm -rf magellan-api

git clone git@github.com:Billzg13/magellan-api.git
cd magellan-api
git checkout master
git pull

echo " end of deploy_aws"
#!/bin/bash

echo "in deploy_aws"
eval "$(ssh-agent -s)"
ls

ssh-add ./ptuxiakiKey.pem

ssh ubuntu@ec2-18-195-215-56.eu-central-1.compute.amazonaws.com
echo "after successfull ssh ?"
ls

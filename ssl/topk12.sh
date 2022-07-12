#!/bin/bash

LBSHOME=~/projects/lychee/data/certbot/conf/live/littlebagshop.com

openssl pkcs12 -export -in $LBSHOME/fullchain.pem -inkey $LBSHOME/privkey.pem -out springboot_letsencrypt.p12 -name bootalias -CAfile chain.pem -caname root

cp springboot_letsencrypt.p12 ../src/main/resources

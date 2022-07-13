#!/bin/bash

LBSHOME=~/projects/lychee/data/certbot/conf/live/littlebagshop.com

if [[ -z "${OSSLPWD}" ]]; then
   echo "environment variable OSSLPWD is not set" 
else
   openssl pkcs12 -export -in $LBSHOME/fullchain.pem -inkey $LBSHOME/privkey.pem -out springboot_letsencrypt.p12 -name bootalias -CAfile chain.pem -caname root -passout env:OSSLPWD
cp springboot_letsencrypt.p12 ~/projects/sorbet/src/main/resources
rm springboot_letsencrypt.p12
fi


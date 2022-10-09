#!/bin/bash

echo -n | openssl s_client -connect littlebagshop.com:443 \
    | openssl x509 > littlebagshop.com.crt
cp littlebagshop.com.crt ../../src/main/resources

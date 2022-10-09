#!/bin/bash

keytool -import -alias hkpost -keystore /usr/local/Cellar/openjdk@8/1.8.0+322/libexec/openjdk.jdk/Contents/Home/jre/lib/security/cacerts -file "*.hongkongpost.hk.cer" 


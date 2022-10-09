#!/bin/bash
openssl x509 -outform der -in ~/Library/Application\ Support/protonmail/bridge/cert.pem -out \
 ~/Library/Application\ Support/protonmail/bridge/cert.der

keytool -import -alias your-alias -keystore /usr/local/Cellar/openjdk@8/1.8.0+322/libexec/openjdk.jdk/Contents/Home/jre/lib/security/cacerts -file ~/Library/Application\ Support/protonmail/bridge/cert.der

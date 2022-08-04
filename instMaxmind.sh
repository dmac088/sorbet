#!/bin/bash

export GEOIP_EDITION='GeoLite2-City'
#export GEOIP_EDITION='GeoLite2-Country'
export GEOIP_LICENCE_KEY=${MAXMIND_KEY}
export GEOIP_TMP_FILE=geoip.tar.gz

echo 'don't forget to export a maxmind api key to $MAXMIND_KEY'
echo 'using key: '$GEOIP_LICENCE_KEY 

curl -sS "https://download.maxmind.com/app/geoip_download?edition_id=${GEOIP_EDITION}&license_key=${GEOIP_LICENCE_KEY}&suffix=tar.gz" > $GEOIP_TMP_FILE && \
tar -xzf $GEOIP_TMP_FILE "${GEOIP_EDITION}*.mmdb"                                                                              && \
mv ${GEOIP_EDITION}*/${GEOIP_EDITION}.mmdb ./src/main/resources/maxmind/"${GEOIP_EDITION}.mmdb"
rm $GEOIP_TMP_FILE

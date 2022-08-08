docker run -d -e STRIPE_SECRET_KEY=testkey \
           -e APP_GLOBALS_BEURL=littlebagshop.com:8090 \
           -e SPRING_PROFILES_ACTIVE=prd \
           --name my-spring-container  \
	   -p 8090:8090 \
           --network my-net \
           -v $(pwd)/images:/images \
           -v $(pwd)/lucene/indexes:/lucene/indexes \
 my-sv2-app

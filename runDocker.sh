docker run -d -e STRIPE_SECRET_KEY=testkey \
           -e SPRING_DATASOURCE_MOCHI_APPLICATION_URL=jdbc:postgresql://my-postgresdb-container:5432/mochidb?currentSchema=mochi \
           -e SPRING_DATASOURCE_MOCHI_OWNER_URL=jdbc:postgresql://my-postgresdb-container:5432/mochidb?currentSchema=mochi \
           -e SPRING_DATASOURCE_SECURITY_APPLICATION_URL=jdbc:postgresql://my-postgresdb-container:5432/mochidb?currentSchema=security \
           -e SPRING_DATASOURCE_SECURITY_OWNER_URL=jdbc:postgresql://my-postgresdb-container:5432/mochidb?currentSchema=security \
           -e APP_GLOBALS_BASEURL=littlebagshop.com:8090 \
           -e SPRING_PROFILES_ACTIVE=prd \
           --name my-spring-container  \
	   -p 8090:8090 \
           --network my-net \
           -v $(pwd)/images:/images \
 my-sv2-app

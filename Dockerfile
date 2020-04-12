From openjdk:11
copy ./target/financial-transaction-redis-0.0.1-SNAPSHOT.war financial-transaction-redis-0.0.1-SNAPSHOT.war
CMD ["java","-jar","financial-transaction-redis-0.0.1-SNAPSHOT.war"]

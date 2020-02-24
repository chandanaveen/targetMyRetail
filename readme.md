URL to swagger : http://localhost:8080/swagger-ui.html

URL to working ID: http://localhost:8080/v1/product/13860428

Cassandra and run the following commands

CREATE KEYSPACE IF NOT EXISTS "myretail"
WITH replication = {'class':'SimpleStrategy', 'replication_factor' : 3};

create table product_details (id int , product_price decimal,currency_code text, PRIMARY KEY (id)   );

insert into product_details (id,product_price, currency_code) values (13860428,  13.49, 'USD');

insert into product_details (id,product_price, currency_code) values (13860429,  12.49, 'USD');

insert into product_details (id,product_price, currency_code) values (13860430,  15.49, 'USD');

Other Installations - GIT, Maven, Java 8 

Project :

1. clone the project https://github.com/chandanaveen/targetMyRetail.git
2. navigate to the root folder using git bash or windows command line
3. run the command maven clean package
4. Navigate <root path to cloned project >\myretail\target\
5. Run the command java -jar targetMyRetail-0.0.1-SNAPSHOT.jar

scu_svk
=======

SVK Project Github


To run the project -
1. Run mvn clean package
2. Copy the war file from target folder to webapps folder inside tomcat.
3. In tomcat/conf/server.xml add this line in "Host" section of the xml
```
<Context docBase="/tmp/images" path="/images" />
```
If you are using unix make sure /tmp/images exists.
If you are using windows create "C:/temp/images" and change "/tmp/images" to "C:/temp/images".

4. Start tomcat server by going to tomcat/bin and doing ./startup.sh

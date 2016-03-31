#!/bin/bash

JAVA_HOME=/opt/java/jdk1.8.0_73 ; mvn clean install jetty:run -Djetty.port=9097 -Dspring.profiles.active=development



#JAVA_HOME=/opt/java/jdk1.8.0_20 ; mvn clean install tomcat7:run-war -Dspring.profiles.active=development


#-Drebel.notification.url=http://localhost:46896 
#-Xdebug -Xrunjdwp:transport=dt_socket,address=5007,suspend=n,server=y -Drebel.properties=/home/blu/.jrebel/jrebel.properties -Dnoverify
#http://flurdy.com/docs/intellij/
#jetty:run -Djetty.port=*9097* -Dspring.profiles.active=development -Djavaagent:/home/blu/.IntelliJIdea13/config/plugins/jr-ide-idea/lib/jrebel/jrebel.jar
#jetty:run -Djetty.port=9097 -Dspring.profiles.active=development -Dnoverify -Djavaagent:/home/blu/.IntelliJIdea15/config/plugins/jr-ide-idea/lib/jrebel6/jrebel.jar
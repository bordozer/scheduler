#!/bin/bash

flyway -user=sa -password= -url='jdbc:h2:~/dev/db/h2-db/scheduler;MODE=Oracle;AUTO_SERVER=TRUE' -locations='filesystem:/home/blu/dev/src/scheduler/scheduler/src/main/resources/migration' migrate

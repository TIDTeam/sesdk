#!/bin/sh

#ps -ef | grep FileServer | awk '{print $2}' | xargs kill -9
#kill $(netstat -nptl | awk '/:8080/{gsub("/.*", ""); print $7}')
kill $(netstat -anop | awk '/:8080/{gsub("/.*", ""); print $7}')
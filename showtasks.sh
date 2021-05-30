#!/usr/bin/env bash

export CATALINA_HOME=/Users/geoprojekt3d/Desktop/Development/Projects/tasks

start_crud() {
  $CATALINA_HOME/runcrud.sh start
}

fail() {
   echo "Something went wrong"
}

end() {
   echo "Browser was open correctly"
}

open_safari() {
  open -a safari 'http://localhost:8080/crud/v1/task/getTasks'
}

if start_crud; then
  if [ $? -eq 0 ]
 then
   open_safari
   end
 else
   fail
 fi
fi



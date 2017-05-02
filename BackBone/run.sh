#!/bin/bash
case "$OSTYPE" in
  solaris*) java -jar -Djava.library.path=libs/native/solaris Game.jar ;;
  darwin*)  java -jar -Djava.library.path=libs/native/macosx Game.jar ;; 
  linux*)   java -jar -Djava.library.path=libs/native/linux Game.jar ;;
  bsd*)     java -jar -Djava.library.path=libs/native/freebsd Game.jar ;;
  *)        echo "unknown: $OSTYPE" ;;
esac

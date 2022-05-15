#!/usr/bin/env bash

if [ "$1" == 'ls' ]; then
  if [ -z "$2" ]; then
    eval ./gradlew listFiles
  else
    args="$2"
    eval ./gradlew listFiles --args "$args"
  fi
fi

if [ "$1" == 'upload' ]; then
  args="$2"
  if [ -z "$2" ] || [ -z "$3"  ] || [ -z "$4"  ] || [ -z "$5"  ] || [ -z "$6"  ]; then
    echo "usage: ./dso-cli.sh upload <username> <password> <ip_address> <port> <filepath>"
  else
    args="$2 $3 $4 $5 $6"
    eval ./gradlew uploadFile --args "$args"
  fi
fi

if [ "$1" == 'download' ]; then
  args="$2"
  if [ -z "$2" ] || [ -z "$3"  ] || [ -z "$4"  ] || [ -z "$5"  ] || [ -z "$6"  ]; then
    echo "usage: ./dso-cli.sh download <username> <password> <ip_address> <port> <filename>"
  else
    args="$2 $3 $4 $5 $6"
    eval ./gradlew downloadFile --args "$args"
  fi
fi

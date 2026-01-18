#!/bin/bash

BASEDIR=$(dirname "$0")
java -jar $BASEDIR/gradle/wrapper/gradle-wrapper.jar "$@"

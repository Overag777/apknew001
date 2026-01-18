#!/bin/sh

# ----------------------------------------------------------------------------
# Gradle startup script for Unix
# ----------------------------------------------------------------------------

# Find the directory where this script is located
APP_HOME=$(cd "$(dirname "$0")" && pwd)

# Use Java from JAVA_HOME if set, otherwise use java from PATH
if [ -n "$JAVA_HOME" ] ; then
    JAVA="$JAVA_HOME/bin/java"
else
    JAVA=java
fi

# Run Gradle
CLASSPATH="$APP_HOME/gradle/wrapper/gradle-wrapper.jar"
exec "$JAVA" \
    -classpath "$CLASSPATH" \
    org.gradle.wrapper.GradleWrapperMain \
    "$@"

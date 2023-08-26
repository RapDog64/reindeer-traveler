#!/bin/bash

echo '### Running the tests ###'
if [ "$1" = "docker" ]; then
  bash ./gradlew :rangiffler-e-2-e-tests:clean test -Denv=docker
else
  bash ./gradlew :rangiffler-e-2-e-tests:clean test
fi
echo '### Preparing the report ###'
bash ./gradlew :rangiffler-e-2-e-tests:allureServe
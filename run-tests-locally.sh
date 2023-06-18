#!/bin/bash

echo '### Running the tests ###'
bash ./gradlew :rangiffler-e-2-e-tests:clean test -Denv=docker
echo '### Preparing the report ###'
bash ./gradlew :rangiffler-e-2-e-tests:allureServe

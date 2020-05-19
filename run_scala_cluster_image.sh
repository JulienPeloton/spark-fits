#!/bin/bash
# Copyright 2018 Julien Peloton
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

## SBT Version
SCALA_VERSION=2.11.8
SCALA_VERSION_SPARK=2.11

## Package version
VERSION=0.8.4

# Package it
sbt ++${SCALA_VERSION} package

# Parameters (put your file)
fitsfn="hdfs://134.158.75.222:8020//lsst/tests/tst0009.fits"
fitsfn="hdfs://134.158.75.222:8020//lsst/images/a.fits"


# Run it!
cmd="spark-submit --master spark://134.158.75.222:7077 --driver-memory 4g --executor-memory 18g --class com.astrolabsoftware.sparkfits.ReadImage target/scala-${SCALA_VERSION_SPARK}/spark-fits_${SCALA_VERSION_SPARK}-${VERSION}.jar $fitsfn"

$cmd

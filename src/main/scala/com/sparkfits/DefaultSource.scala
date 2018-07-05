/*
 * Copyright 2018 Julien Peloton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.astrolabsoftware.sparkfits

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.sources._
import org.apache.spark.sql.types.StructType

/**
  * FITS source implementation for Spark SQL.
  *
  */
class DefaultSource extends RelationProvider with SchemaRelationProvider with DataSourceRegister {

  /**
    * Short alias for spark-fits data source.
    */
  override def shortName(): String = "fits"

  /**
    * Create a new FitsRelation instance using parameters from Spark SQL DDL.
    * Resolves the schema using the FITS header.
    */
  override def createRelation(
      sqlContext: SQLContext,
      parameters: Map[String, String]): BaseRelation = {
    new FitsRelation(parameters, None)(sqlContext)
  }

  /**
    * Create a new FitsRelation instance using parameters from Spark SQL DDL,
    * and using user-provided schema.
    */
  override def createRelation(
      sqlContext: SQLContext,
      parameters: Map[String, String],
      schema: StructType): BaseRelation = {
    new FitsRelation(parameters, Some(schema))(sqlContext)
  }
}

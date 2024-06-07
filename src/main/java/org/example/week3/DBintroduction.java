package org.example.week3;

/**
 *  RDBMS: Oracle, MySQL, PostgreSQL, SQL Server, DB2, MariaDB, Sqlite, TimeScalaDB
 *  NoSQL: MongoDB, Cassandra
 *  Object storage: S3 -> S3 Glacier
 *  File system: EFS
 *  Document DB: Elastic Search / OpenSearch
 *
 *
 *  Query : oracle sql query
 *  Transaction Level : MySQL (read uncommitted, read committed, repeatable read, serializable)
 *                      Oracle (read committed, serializable, read only)
 *  Index : B+ index
 *          BitMap index
 *          Cluster Index
 *  Execution Plan : join strategy
 *                   hint
 *  Table Design : normalization
 *                 1 - m , 1 - 1 , m - m
 *                 entity attribute value
 *                 inheritance
 *                 column type: json, bit mask, compress, url..
 *  PL/SQL : stored procedure, trigger, function, package
 *
 *  Oracle Live SQL
 *      -> session
 *
 *  Friday deadline
 *  DO NOT USE HIBERNATE DDL-AUTO-UPDATE
 *  1. spring boot rest api CRUD
 *       book many to many author
 *  2. meet production standard
 *       controller
 *       service
 *       repository
 *       exception
 *       domain: entity / dto
 *       application.properties
 *  3. one unit test: junit + mockito  => service
 *  4. spring data jpa , use both a and b to connect to db
 *       a. jpa repository interface
 *       b. interface + impl(entity manager)
 *  5. swagger api document
 *  6. post man test
 *  7. read me
 *  8. upload to github
 *
 *  query practices (leetcode easy to medium)
 */
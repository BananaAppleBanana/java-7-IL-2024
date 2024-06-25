package org.example.week6;


/**
 *  code -> package war / jar / docker image
 *
 *  centralized command / manage    [ py -> ]
 *                                  [... -> ]
 *
 *
 *
 *  build -> test -> report  ->  package  -> upload to ECR(tag) ->  deploy -> ECS(EC2 / Fargate)
 *                      |
 *                  code coverage
 *                  security
 *                     |
 *                  Sonarqube(server)
 *
 *  master    ---------                                -> prod env
 *                  \
 *                  hotfix branch
 *                  /
 *  release ---------------------------------o--       -> release env
 *                                          /
 *  dev     ---o--------------------o-----------       -> dev env
 *              \                  /
 *            feature branch ------
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  product owner
 *  manager
 *  BA (optional)
 *  scrum master (optional)
 *  leader
 *  frontend / backend / full stack
 *  shared QA team
 *  QA person (optional)
 *  DBA (shared)
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Tomorrow
 *      Agile Scrum
 *      Daily work
 *      interview questions
 *
 * Self intro
 * Project intro
 * Did you used java for your last project
 * What are rest api method you used
 * What are other methods you used
 * Put vs post
 * Is post idempotent
 * Is put idempotent
 * High level architecture of kafka
 * how does consumer get messages
 * how does consumer know what messages to get
 * how does kafka know message has been processed
 * Coding: leetcode 1268
 *
 *
 Jeffery Li -  02/27/2024  - Goldman Sachs - 4th round
 all following questions are coding questions
 write java class and design producer and consumer, producer can send msg, and consumer can consume message
 what queue should we use
 class ProducerConsumer {
 private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>();
 }
 create consumer class to consume msg
 any exception should we throw in consumer class , how to write it
 create producer class to send 5 data into it
 create a new consumer class, i want to return a value
 create a method, and executor service, handle this new class and get result
 write spring controller for CRUD (get, put, post, delete), including URL design and status code
 what does request param look like in url
 diff 200 and 201
 create a new ArrayList
 sql select all account
 sql select account group by city


 Jeffery Li -  02/23/2024  - Goldman Sachs - 3rd round
 questions related to your resume
 have you used cdk aws
 tell me serverless services
 any cloudformation experience
 what is step function
 lambda maximum java package size
 what languages can we use in lambda
 how to run java in lambda
 lambda limitation
 how to package your java program and upload to lambda
 how about the dependencies in your java application
 what is sqs
 diff queue and broker
 what is dead letter queue
 how do you send notification to developer if some errors in queue
 how do you use cloudwatch monitor sqs(metrics)
 what is event bridger
 diff load balancers in aws
 what are 2 most frequently used method in s3
 how did you use aws in the work
 how to create thread
 disadvantage of runnable
 how to get result from thread
 how to reuse thread
 what types of threadpool in java
 what is the interface name of thread pool
 if we create child thread in main thread, how to wait child thread in main thread
 volatile
 how to write jdbc
 rest api method
 how to configure version of api
 filter
 interceptor
 what is aop
 joinpoint
 point cut
 spring batch experience
 log level


 Jeffery Li -  02/20/2024  - Goldman Sachs - 2nd round
 questions related to your resume
 why microservice
 and how to create one service in microservice
 how do you use spring boot
 why spring boot
 how do you secure your rest api
 if you using 3rd party package or 3rd party api, how do you prevent vulnerability
 how to ensure code quality
 if we have producer a , and consumer b, they are connected with non-secured protocol. how to we transfer data
 are you good at data structure
 hashmap vs hashtable
 can we put null into hashmap or hashtable
 if we put(null, 10) into hashmap, then put(null, 100) what will happen


 Jeffery Li -  02/16/2024  - goldman sachs
 questions related to your resume
 how does gc work
 what can you do if you get out of memory error
 diff abstract class and interface
 how does hashmap work
 diff equals and hashcode
 what is hash collision
 if we need to use customer class as key , what should we do
 tell me some hashmap methods you know
 what is immutable in java
 how to create immutable class in java
 why immutable
 diff abstract class and interface
 diff hashmap vs concurrenthashmap
 how does concurrenthashmap work
 what aws parts have you used in daily life

 */
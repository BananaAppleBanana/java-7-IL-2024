package org.example.week6;

/**
 *      Agile Scrum
 *      sprint = 1 ~ 4 weeks
 *      1. daily stand up
 *      2. sprint planning meeting
 *          product backlog(todo list with priority)
 *          story / ticket
 *          point
 *      3. retrospective meeting / review meeting
 *
 *
 *
 *      Daily work
 *      1. agile scrum (meetings)
 *      2. After you getting tickets / stories
 *          requirements
 *          design
 *          check out new branch
 *          ood interface / abstract class / class
 *          write unit test
 *          impl todo logic
 *          run test
 *          pull request code review
 *          merge
 *          trigger jenkins pipeline
 *
 *
 *
 *
 *  interview questions
 *
 *  Did you used java for your last project
 *      yes
 *      java 11 / java 17
 *      spring , spring boot, spring jpa ,microservice
 *      java -> impl rest api
 *      java -> connect database
 *      ..
 *  What are rest api method you used
 *      get
 *      post
 *      delete
 *      put
 *      patch
 *  What are other methods you used
 *  Put vs post
 *  Is post idempotent
 *  Is put idempotent
 *  High level architecture of kafka
 *      broker
 *      partition
 *      consumer group
 *      consumer pull partitions
 *      ..
 *  how does consumer get messages
 *      pull messages from partitions in broker
 *  how does consumer know what messages to get
 *      index
 *  how does kafka know message has been processed
 *  Coding: leetcode 1268
 *
 *  class Node {
 *      Node[] children = new Node[26];
 *      List<String> productsList;
 *      TreeSet<String> set;
 *      PriorityQueue<String> heap;
 *      String[] size = 4;
 *  }
 *
 *  if(curNode.children[ch - 'a'] == null) {
 *      curNode.children[ch - 'a'] = new Node();
 *  }
 *  curNode = curNode.children[ch - 'a'];
 *
 *  eg. ["ab", "ba"] -> .....
 *      [..] -> ..
 *
 *
 *
 *  Jeffery Li -  02/16/2024  - goldman sachs 1st
 *  questions related to your resume
 *  how does gc work
 *      1. heap + generations
 *          young gen [eden][s0][s1]
 *          old       []
 *          perm gen -> meta space (native heap)
 *      2. young gc (minor gc) -> mirror copying (STW)
 *         old   gc (major gc) -> mark and sweep + compress / cms
 *         full gc
 *         G1
 *  what can you do if you get out of memory error
 *      1. restart
 *      2. heap dump
 *      3. check memory leak
 *      4. soft reference / weak reference
 *      5. change jvm heap generation size
 *  diff abstract class and interface
 *      1. class extend + impl
 *         interface extends
 *      2. access modifiers
 *      3. public static final variables in interface
 *  how does hashmap work
 *      1. get / put
 *      2. hash(key.hashCode()) + length of array => index of bucket
 *      3. use equals in linked list / red black tree
 *  diff equals and hashcode
 *  what is hash collision
 *  if we need to use customer class as key , what should we do
 *      1. override equals and hashcode at same time
 *  tell me some hashmap methods you know
 *      1. put, get, putIfAbsent, computeIfAbsent, merge, size, ...
 *  what is immutable in java
 *      final class ImmutableClass {
 *          private final List<XX> list = new ArrayList<>();
 *          public ImmutableClass(List<XX> list) {
 *              for(XX x: list) {
 *                  list.add(x.clone());
 *              }
 *          }
 *
 *          public List<XX> getList() {
 *              //create new list
 *              //copy all elements into new list
 *              //return new list
 *          }
 *      }
 *
 *      List<XX> list = new ArrayList<>();
 *      ImmutableClass instance = new ImmutableClass(list);
 *      List<XX> myList = instance.getList();
 *      myList.add();
 *
 *  how to create immutable class in java
 *  why immutable
 *  diff hashtable  vs concurrenthashmap
 *      hashtable
 *          1. synchronized on every method
 *      concurrent hashmap
 *          1. synchronized on first node of the bucket
 *          2. no lock when you get elements
 *          3. volatile
 *          4. fail safe
 *  how does concurrenthashmap work
 *  what aws parts have you used in daily life
 *
 *
 *  Jeffery Li -  02/20/2024  - Goldman Sachs - 2nd round
 *  questions related to your resume
 *  why microservice / why not monolithic / why microservice over monolithic
 *      1. scalability
 *      2. Decentralized / isolation / distribution
 *      3. fault tolerance / fail tolerance
 *      4. flexibility (diff framework / languages / implementations)
 *  and how to create one service in microservice
 *      1. create project / service
 *      2. dependencies
 *      3. register service in discovery service
 *      4. configure properties in configuration service
 *      5. dockerfile -> docker image -> ECS task definition
 *      6. configure security (jwt verification)
 *      7. connect diff services with rest api / message queue
 *      8. configure gateway routing configuration / ALB routing strategy ..
 *      9. integration / functional test between services
 *      ...
 *  how do you use spring boot / why spring boot
 *      1. application.properties
 *      2. spring boot auto configuration
 *      3. spring boot package to jar(embedded tomcat)
 *      4. spring boot actuator
 *      5. IOC / DI (Spring)
 *  how do you secure your rest api
 *      1. authentication
 *      2. authorization jwt
 *      3. HTTPS
 *      4. CORS / CSRF / DDOS / Rate Limiter / encryption
 *      5. SQL Injection
 *      OWASP Top Ten
 *      ..
 *
 *  if you using 3rd party package or 3rd party api, how do you prevent vulnerability
 *      1. 3rd party vulnerability ?
 *          response data insecure issues?  validation / documentation
 *          no encryption between service?  HTTPS
 *          fake api ?  certificate / HTTPS
 *          security scan (?)
 *          ...
 *     2. 3rd party package
 *          security scan (CI CD report)
 *          ..
 *
 *  how to ensure code quality
 *      1. Test + code coverage
 *         OOD + SOLID
 *         time complexity , space complexity
 *         reuse data / implementation / utilities
 *         ..
 *      2. code review
 *      3. CI/CD -> security check / vulnerability check
 *      4. QA Test
 *  if we have producer a , and consumer b, they are connected with non-secured protocol. how to we transfer data
 *      1. http -> https
 *      2. encrypt your request body
 *      ...
 *  are you good at data structure
 *  hashmap vs hashtable
 *  can we put null into hashmap or hashtable
 *  if we put(null, 10) into hashmap, then put(null, 100) what will happen
 *
 *
 *  Jeffery Li -  02/23/2024  - Goldman Sachs - 3rd round
 *  questions related to your resume
 *  have you used cdk aws
 *  tell me serverless services
 *      lambda
 *      fargate
 *      s3 / dynamodb
 *      sqs / sns
 *      cloud map
 *
 *  any cloudformation experience
 *  what is step function
 *  lambda maximum java package size
 *  what languages can we use in lambda
 *  how to run java in lambda
 *  lambda limitation
 *  how to package your java program and upload to lambda
 *  how about the dependencies in your java application
 *  what is sqs
 *  diff queue and broker
 *  what is dead letter queue
 *  how do you send notification to developer if some errors in queue
 *  how do you use cloudwatch monitor sqs(metrics)
 *  what is event bridger
 *  diff load balancers in aws
 *  what are 2 most frequently used method in s3
 *  how did you use aws in the work
 *  how to create thread
 *  disadvantage of runnable
 *  how to get result from thread
 *  how to reuse thread
 *  what types of threadpool in java
 *  what is the interface name of thread pool
 *  if we create child thread in main thread, how to wait child thread in main thread
 *      Thread.join()
 *  volatile
 *      1. visibility (always read from main memory)
 *      2. happen before rule..
 *  how to write jdbc
 *  how to configure version of api
 *      1. /v1/...
 *
 *      2. header
 *      3. request param
 *  filter vs interceptor
 *  what is aop
 *  joinpoint
 *  point cut
 *  spring batch experience
 *  log level
 *
 *
 *  Jeffery Li -  02/27/2024  - Goldman Sachs - 4th round
 *  all following questions are coding questions
 *  write java class and design producer and consumer, producer can send msg, and consumer can consume message
 *  what queue should we use
 *  class ProducerConsumer {
 *  private final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>();
 *  }
 *  create consumer class to consume msg
 *  any exception should we throw in consumer class , how to write it
 *  create producer class to send 5 data into it
 *  create a new consumer class, i want to return a value
 *  create a method, and executor service, handle this new class and get result
 *  write spring controller for CRUD (get, put, post, delete), including URL design and status code
 *  what does request param look like in url
 *  diff 200 and 201
 *  create a new ArrayList
 *  sql select all account
 *  sql select account group by city
 *
 *
 */
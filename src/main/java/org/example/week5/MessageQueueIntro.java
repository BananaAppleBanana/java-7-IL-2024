package org.example.week5;


/**
 *  server  -  message queue cluster -  server
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  rabbit mq
 *      producer ->  queue[msg][msg] ->  consumer
 *                                   ->  consumer
 *  vs
 *
 *
 *      producer  ->   broker(topic - partition) -> consumer group
 *                                                  consumer pull msg from partition
 *
 *
 *                          broker                     consumer group1
 *                          Topic A
 *                          partition 1                 consumerA (pull topic A partition 1, 3)
 *    producer              partition 2                 consumerB (pull topic A partition 2)
 *                          partition 3
 *
 *                          broker                     consumer group2
 *                          TopicA                      consumerC (pull topic A partition 2)
 *                          partition 1                 consumerD (pull topic A partition 3)
 *                          partition 2                 consumerE (pull topic A partition 1)
 *                          partition 3
 *
 *
 *
 *      consumer group  1 -  m  consumer 1 - m partition
 *
 *  vs
 *  SQS (visibility timeout)
 *       queue[msg][msg][msg][msg]      ->  consumer
 *                                      ->  consumer
 *
 *  SNS -> SQS
 *      -> SQS
 *      ..
 *
 *  Dead Letter Queue : failed message
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  Change data capture + outbox pattern
 *
 *       |
 *      server  -  message queue
 *       |
 *      DB
 *
 *      1. insert data to db
 *      2. send msg to message queue
 *
 *       |
 *     server
 *       |
 *      DB  -  CDC service -> message queue
 *      1. begin tx
 *         insert data into db table
 *         insert message into outbox table
 *         commit tx
 *
 *      2. CDC Service monitoring outbox table
 *         read outbox table
 *         send message to message queue
 *         remove data / mark status
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  SAGA pattern
 *
 *      user
 *      |
 *      server - message queue  -   server  -  message queue - server
 *       |                          |                            |
 *      DB1                         DB2                         DB3
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
    duplicate message
 *      1. idempotent service
 *      2. use cache to check duplicate msg
 *          message queue -  service
 *                              |
 *                           cache
 *      3. SNS deduplicate
 */
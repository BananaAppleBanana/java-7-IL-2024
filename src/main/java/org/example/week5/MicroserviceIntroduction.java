package org.example.week5;

/**
 *  why not using Monolithic
 *  1. class / interface / package / configuration structure
 *  2. fail tolerance
 *  3. deploy
 *  4. scalability
 *  5. message queue
 *
 *  why not Microservice
 *  1. service boundary
 *  2. complex
 *  3. global transaction
 *
 *  what do you need to consider when you design Microservice architecture
 *  1. Api Gateway
 *      co-relation id(global unique id)
 *          DB
 *          UUID
 *          Snowflake -> timestamp + machine id + thread id + serial id
 *      rate limiter
 *          token bucket
 *                     drop 1 token per sec
 *                     |
 *                 \    /
 *                  \_/
 *                   |
 *                   --->  take token ->   user
 *          queue
 *              [t1][t2][t3][t4]  t5
 *          sliding window
 *              [2][][][3][][][][][][]
 *                      l    r
 *  2. Centralized Security Service
 *  3. Discovery Service / Service Registration
 *         service A  -->  service B
 *              |
 *         discovery server
 *         ribbon client side load balancer
 *  4. Message queue
 *          user
 *           |
 *          backend -> message queue  -> downstream
 *  5. Centralized Config Service
 *  6. Circuit Breaker(hystrix)
 *          service A  -->  service B
 *                  send 5 requests,  3 requests failed / timeout
 *                          |
 *                        state (open -> close)
 *                         |
 *                        background thread -> try access service B -> success 3 times in 5 requests -> (close -> open)
 *  7. Cache Cluster
 *      Redis(fix number of hash slots/buckets)
 *           -> task ->  event loop  ->  single thread
 *           AOF -> log every operation
 *           snapshot -> take snapshot every certain time
 *
 *              Leader node(0,5000)         Leader node(5000~..)
 *               /          \               /       \
 *         follower node
 *
 *
 *      cache aside
 *          backend service   - cache
 *              |
 *           database
 *         1. get
 *              read from cache -> return value if exist
 *              if no data in cache -> read from db, save to cache (TTL)
 *         2. update
 *              delete data from cache
 *              write into database
 *       read through and write through
 *          backend service
 *              |
 *            cache
 *             |
 *           database
 *
 *          backend service
 *            |
 *          cache
 *           |  (bulk updates)
 *          database
 *  8. Database Cluster
 *      random access
 *      sequential access
 *  9. Deployment -> docker (Kubernetes / ECS)
 *  10. Documentation
 *  11. Monitor
 *          log monitor(Splunk)
 *          api performance monitor
 *          jvm monitor
 *
 *
 *   Raft
 *      Leader  Candidate  Follower
 *   Scheduled task
 *      1. message queue(delayed task)
 *      2. time wheel
 *      3. priority queue
 *      4. sort db table
 *      ..
 *   Performance bad
 *      1. global cache / result cache / get http / CDN
 *      2. db tuning
 *      3. message queue
 *      4. tuning java code / logic
 *      ..
 *   Asynchronous process
 *      1. message queue
 *   Count frequency
 *      1. cache
 *      2. min count sketch
 *      3. hyper log log
 *      4. ...
 *      ..
 *
 *
 */
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
 *  2. Centralized Security Service
 *  3. Discovery Service / Service Registration
 *  4. Message queue
 *  5. Centralized Config Service
 *  6. Circuit Breaker
 *  7. Cache Cluster
 *  8. Database Cluster
 *  9. Deployment -> docker (Kubernetes / ECS)
 *  10. Documentation
 *  11. Monitor
 *          log monitor
 *          api performance monitor
 *          jvm monitor
 *
 */
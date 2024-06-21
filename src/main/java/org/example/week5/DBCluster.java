package org.example.week5;

/**
 * Single Leader
 *      Leader
 *          |    \
 *      Standby   follower 1, 2, 3, 4, 5
 *      1. write -> leader
 *      2. read -> leader  / follower
 * Multi Leader
 *      Leader          Leader          Leader
 *      |   \           /   \            /  \
 *
 *     vector clock
 * LeaderLess
 *             Leader1(0)
 *   Leader4            Leader2(5000)
 *            Leader3(10000)
 *    *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   MongoDB Cluster (CP)
 *
 *              mongos    -   config server
 *           /      \       \
 *      partition1  2       3
 *      primary node
 *      secondary node
 *      secondary node
 *    *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  LSM tree
 *
 *  Cassandra node
 *
 *      ->   mem table (cache) -> SSTable(sorted string table)
 *          |
 *        commit log
 *
 *
 *      read -> blooming filter ->
 *
 * Cassandra cluster (AP)
 *
 *               node1
 *
 *     node4               node2
 *
 *              node3
 *
 *      replica factor = 3
 *      send request to node 4 => send replica to node1, node2 and node3
 *
 *      write consistency level 1 ~ replica number
 *      read consistency level 1 ~ replica number
 *
 *      read repair
 *
 *      rc + wc > rf
 *
 *    *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *    global secondary index
 *              Tom: partition 1, 5, 7
 *    *    *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 */
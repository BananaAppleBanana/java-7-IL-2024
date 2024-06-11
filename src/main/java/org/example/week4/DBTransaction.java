package org.example.week4;

/**
 * Transaction
 * ACID
 *  Atomicity
 *  Consistency
 *  Isolation Level
 *      Read Uncommitted
 *      Read Committed
 *      Repeatable Read (MySQL Default level)
 *      Serializable
 *  Durability
 *
 *
 *  user1       insert 10 rows               insert 10 rows       rollback
 *  -----------------------------------------------------------------------> timeline
 *  user2   select                  select
 *
 *
 *  Read Uncommitted (problems: dirty read / non-repeatable read / phantom read)
 *  user1           begin tx  insert 10 rows               insert 10 rows       commit
 *  -----------------------------------------------------------------------------------> timeline
 *  user2   select *              !=             select *
 *
 *  Read Committed (problems: non-repeatable read / phantom read)
 *  user1           begin tx  insert 10 rows               insert 10 rows       commit
 *  -----------------------------------------------------------------------------------> timeline
 *  user2   select *              ==             select *
 *
 *  user1           begin tx  insert 10 rows           insert 10 rows       commit
 *  -----------------------------------------------------------------------------------> timeline
 *  user2   select *              !=                                                    select *
 *
 *
 *
 *  Repeatable Read (problems:  phantom read)
 *  user1           begin tx  update 10 rows           insert 10 rows       commit
 *  -----------------------------------------------------------------------------------> timeline
 *  user2   select *              ==                                                    select *
 *
 *  user1           begin tx  insert 10 rows           insert 10 rows       commit
 *  -----------------------------------------------------------------------------------> timeline
 *  user2   select id=x return empty             !=                                    select id=x
 *
 *
 *
 *  Serializable
 *  user1           begin tx  update/insert/delete 10 rows         commit
 *  -----------------------------------------------------------------------------------> timeline
 *  user2   select *              ==                                                    select *
 *
 *
 *  MVCC
 *      3 hidden column : row_id, tx_id, rollback_pointer
 *
 *      id,  name,  row_id,  tx_id,  rollback_pointer
 *       1,   Tom ,  x     ,     1,  null
 *
 *       update .. set name = 'Jerry'
 *      id,  name,  row_id,  tx_id,  rollback_pointer
 *       1,  Jerry ,  xx  ,    2,     |
 *                                    |
 *                                  1,   Tom ,  x     ,     1,  null
 *
 *      read view (committed transaction id)
 *          Read committed : every select -> read view
 *          Repeatable : 1st select -> read view
 *
 *
 *  Read Lock / Share Lock : select ... share
 *  Write Lock / Exclusive Lock : select ... for update
 *
 *  Record Lock -> row
 *  select .where id > 1 and id < 5 for update
 *  id
 *  2   -> ex
 *  4   -> ex
 *
 *  Next Key Lock -> Record Lock + Gap Lock
 *  select .where id > 1  for update
 *  id
 *  2   -> ex
 *  (3) -> gap lock
 *  4   -> ex
 *  (5, infinite) -> gap lock
 *
 *  Optimistic Lock(prevent lost update)
 *  userA                       userB
 *  read x = 2                  read x = 2
 *  write x = 3                 write x = 3
 *          \                   /
 *                 server
 *                   |
 *                  DB
 *
 *
 *  userA                       userB
 *  read x = 2                  read x = 2
 *   version = 1                version = 1
 *  write x = 3
 *  where version = 1
 *                              write x = 3
 *                              where version = 1
 *    success
 *                              error : version is not 1
 *
 *                              read x = 3
 *                              version = 2
 *                              write x = 4, version 3
 *                              success
 *          \                   /
 *                 server
 *                   |
 *                  DB
 *               x      version
 *               4       3
 * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *
 *       |
 *    Coordinator (2 phase commit)
 *   /      \
 * DB1      DB2
 *
 *
 * user1
 *      begin db1 tx
 *      begin db2 tx
 *      insert to db1
 *      insert to db2
 *      commit db1 tx
 *      ~~~ crash ~~~
 *      commit db2 tx
 *
 * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *  * *
 *
 */
package org.example.week4;

/**
 *  B tree
 *
 *  insert 10
 *  [10]
 *
 *  insert 5
 *  [5, 10]
 *
 *  insert 15
 *  [5, 10, 15]
 *
 *  insert 20
 *  [5, 10, 15, 20]
 *          [15]  root node
 *         /    \
 *  [5, 10]    [15, 20]
 *
 *  insert 25, 30
 *
 *          [15,  25]  root node
 *         /     \      \
 *  [5, 10]    [15, 20]   [25, 30]
 *
 *  insert 35, 40
 *
 *          [15,    25,     35]  root node
 *         /     \      \           \
 *  [5, 10]    [15, 20]   [25, 30]   [35,  40]
 *
 *
 *  insert 45, 50
 *
 *                      [35]
 *                 /          \
 *          [15,    25]           [35,    45]
 *         /     |      \           \           \
 *  [5, 10]  - [15, 20] - [25, 30] - [35,  40] - [45,  50]
 *  rowid       rowid       rowid       rowid
 *      |
 *      |
 *      |
 *      ------------------------->      table
 *                                  row_id(hidden column), id, xx, xx
 *
 *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *  BitMap (bitmask)
 *  table
 *    id,   sex,    row_id      row_id,  Male,   Female
 *    1,    Male,   xx1         xx1,      1,        0
 *    2,    Male,   xx2         xx2,      1,        0
 *    3,    Female, yy          yy,       0,        1
 *
 *    Male: 110
 *    Female: 001
 *
 *    male | female => 110 | 001
 *
 *
 *
 *    1h -> 4 x 15min
 *    24h -> 24 x 4 x 15min -> 96 x 15min
 *
 *    0000...1110
 *
 *    0000...
 *
 *    *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *    non-clustered index
 *    clustered index
 *
 *                      [35]
 *                 /          \
 *          [15,    25]           [35,    45]
 *         /     |      \           \           \
 *  [5, 10]  - [15, 20] - [25, 30] - [35,  40] - [45,  50]
 *  rowid       rowid       rowid       rowid
 *  name
 *  sex
 *  phone
 *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   table partition by date range / value range
 *   *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   full table scan
 *   index scan
 *      index unique scan : root -> leaf node
 *      index range scan  : root -> leaf node -> left / right linkedlist
 *      index full scan / index fast full scan : scan all index leaf nodes
 *
 *   hint:
 *      use_nl / use_hash / use_merge
 *      parallel
 *      leading
 *      index
 *      full
 *      ..
 *
 *   *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   explain plan vs execution plan
 *   oracle architecture
 *   *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   table design
 *   1 - 1 / 1 - m:
 *      tableA 1 - m tableB
 *       id(pk)       id(pk),  a_id(fk)
 *   m - m
 *      tableA m - m tableB
 *       id(pk)        id(pk)
 *      tableA 1 - m junction table m - 1 table B
 *        id            id                  id
 *                      a_id
 *                      b_id
 *
 *
 *  Super key(candidate key(primary key))
 *
 *  Book,  Author
 *  b_id,  a_id,  book_name,  author_name
 *   1  ,   1  ,    'Java'          Tom
 *   2  ,   1  ,    'C#'            Tom
 *   1  ,   2  ,    'Java'          Alex
 *
 *   book_name not depends on a_id
 *   book_name depends on b_id
 *   author_name not depends on b_id
 *   author_name depends on a_id
 *
 *
 *   normalization
 *   1st
 *        name
 *      Alex,Adam
 *   2nd
 *
 *   3rd
 *
 *   id, name, address_id, address_name
 *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   user_id  name
 *   1        Tom
 *   2        Alex
 *   Entity attribute value
 *
 *   id, column_name, column_value, column_type
 *   1,   "user_id" ,       1     ,  number / varchar
 *   2,   "name"    ,    'Tom'    ,  varchar
 *   3,   "user_id" ,      2      ,  number / varchar
 *   ...
 *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *   solution1
 *   id, column1, column2, column3 ... column20
 *   solution2
 *     parent table
 *     /    \   \
 *   child1  2   3
 *   solution
 *   id, name, phone, json
 *
 *   *   *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *  *
 *   cookie vs session
 *
 *
 *   browser -> load balancer(sticky session)
 *           -> tomcat
 *           <- session_id (saved into cookie)
 *           -> send request with cookie(header)
 *
 *
 */
//
//EXPLAIN PLAN
//        SET statement_id = 'ex_plan1' FOR
//        select *
//        from  hr.employees
//        where employee_id >= 101 and employee_id <= 102;
//        SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY(statement_id => 'ex_plan1'));
//
//
//        -- EXPLAIN PLAN
//        --   SET statement_id = 'ex_plan1' FOR
//        --   	select /*+ Leading(e) full(d) */ *
//        --   	from  hr.employees e join hr.departments d on e.department_id = d.department_id;
//        -- SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY(statement_id => 'ex_plan1'));
//
//
//
//        -- EXPLAIN PLAN
//        --   SET statement_id = 'ex_plan1' FOR
//        --     select /*+ index(e) */ *
//        --   	from  hr.employees e, hr.departments d
//        --     where e.department_id = d.department_id;
//        -- SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY(statement_id => 'ex_plan1'));
//
//
//        -- EXPLAIN PLAN
//        --   SET statement_id = 'ex_plan1' FOR
//        --   	select /*+ index(e) */ *
//        --   	from  hr.employees e join hr.departments d on e.department_id = d.department_id;
//        -- SELECT PLAN_TABLE_OUTPUT FROM TABLE(DBMS_XPLAN.DISPLAY(statement_id => 'ex_plan1'));
//
//
//        --parallel(5)
//        -- EXPLAIN PLAN
//        --   SET statement_id = 'ex_plan1' FOR
//
//        --   SELECT /*+ index(e) */ * FROM  hr.employees e;
//
//        -- SELECT PLAN_TABLE_OUTPUT
//        --   FROM TABLE(DBMS_XPLAN.DISPLAY(statement_id => 'ex_plan1'));

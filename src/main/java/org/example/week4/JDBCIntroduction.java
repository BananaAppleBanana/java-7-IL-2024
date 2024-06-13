package org.example.week4;


import jakarta.persistence.EntityManager;

/**
 * import java.sql.Connection;
 * import java.sql.DriverManager;
 * import java.sql.PreparedStatement;
 * import java.sql.SQLException;
 *
 * public class JDBCTransactionExample {
 *
 *     public static void main(String[] args) {
 *         Connection connection = null;
 *         PreparedStatement preparedStatement = null;
 *
 *         try (D...){
 *             // Load the JDBC driver
 *             Class.forName("com.mysql.cj.jdbc.Driver");
 *
 *             // Establish a connection
 *             String url = "jdbc:mysql://localhost:3306/mydatabase";
 *             String user = "username";
 *             String password = "password";
 *             connection = DriverManager.getConnection(url, user, password);
 *
 *             // Disable auto-commit mode
 *             connection.setAutoCommit(false);
 *
 *             // Execute SQL statements
 *             String sql1 = "INSERT INTO employees (name, department) VALUES (?, ?)";
 *             preparedStatement = connection.prepareStatement(sql1);
 *             preparedStatement.setString(1, "John Doe");
 *             preparedStatement.setString(2, "HR");
 *             preparedStatement.executeUpdate();
 *
 *             String sql2 = "UPDATE account SET balance = balance - ? WHERE id = ?";
 *             preparedStatement = connection.prepareStatement(sql2);
 *             preparedStatement.setInt(1, 200);
 *             preparedStatement.setInt(2, 1);
 *             preparedStatement.executeUpdate();
 *
 *             // Commit the transaction
 *             connection.commit();
 *             System.out.println("Transaction committed successfully.");
 *
 *         } catch (ClassNotFoundException e) {
 *             System.out.println("JDBC Driver not found.");
 *             e.printStackTrace();
 *         } catch (SQLException e) {
 *             System.out.println("SQL Exception occurred.");
 *             e.printStackTrace();
 *
 *             // Rollback the transaction in case of an error
 *             if (connection != null) {
 *                 try {
 *                     connection.rollback();
 *                     System.out.println("Transaction rolled back.");
 *                 } catch (SQLException ex) {
 *                     System.out.println("Error rolling back transaction.");
 *                     ex.printStackTrace();
 *                 }
 *             }
 *         } finally {
 *             // Close resources
 *             try {
 *                 if (preparedStatement != null) preparedStatement.close();
 *                 if (connection != null) connection.close();
 *             } catch (SQLException e) {
 *                 e.printStackTrace();
 *             }
 *         }
 *     }
 * }
 *
 *
 *
 * SQL Injection
 *      username: "abc"
 *      password: "abc; drop table;"
 *
 *
 *      "select * from .. where username = " + inputUsername + " and password = " + inputPassword
 *      "select * from .. where username = abc and password = abc; drop table;"
 * what do we need to impl on top of JDBC
 *  1. connection pool
 *  2. result set <-> object mapping
 *  3. centralized query language : jpql , hql
 *  4. cache
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 *  Student (id, name)
 *  Enrollment (id, s_id, c_id)
 *  Course (id, name)
 *
 *  option1
 *  class Student {
 *      private String id;
 *      private String name;
 *      private List<Course> courses;
 *  }
 *
 *  class Course {
 *      private String id;
 *      private String name;
 *      private List<Student> courses;
 *  }
 *
 *  option2
 *  class Student {
 *      private String id;
 *      private String name;
 *      @OneToMany(mappedBy = "stu123")
 *      private List<Enrollment> enrollmentList;
 *  }
 *  class Enrollment {
 *      private String id;
 *      @ManyToOne
 *      @JoinColumn(...)
 *      private Student stu123;
 *      private Course course;
 *  }
 *  class Course {
 *      private String id;
 *      private String name;
 *      private List<Enrollment> enrollmentList;
 *  }
 *
 *
 *   ORM
 *   Query query = entityManager.createQuery("select s from Student s where id = 'xx'");
 *   Student stu = (Student)query.getSingleResult();
 *   List<Enrollment> enrollmentList = stu.getEnrollmentList();
 *   enrollmentList.size() / ..   => send query to database
 *
 *   1. Lazy Loading vs Eager Loading
 *   2. Lazy Loading query + fetch = eager loading
 *   3. N + 1 problem
 *      List<Student> stuList;
 *      for(Student stu: stuList) {
 *          List<Enrollment> enrollmentList = stu.getEnrollmentList();
 *          enrollmentList.size() / ..   => send query to database
 *      }
 *   4. Entity class
 *   5. Repository Layer : interface + impl
 *          hibernate : Session
 *          JPA       : EntityManager
 *   6. @Transactional(propagation level)
 *
 *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *    *
 *   How to use hibernate in project?
 *      1. add dependency in pom.xml
 *      2. add data source application.properties
 *      3. create entity class
 *      4. create repository interface / implementation
 *      5. inject repo into service layer
 *      6. @Transactional
 *   What if we have multiple database
 *      database 1 - 1 data source 1 - 1 SessionFactory / EntityManagerFactory 1 - m session / entity manager
 *   What is LazyInitializationException?
 *      persistent context      --     new Java instance
 *      persist / proxy / attached          transient
 *              |
 *         detached / un proxy
 *   diff jpa and hibernate
 *   Merge() vs Persist()
 *      merge :
 *          if(id exist in db)
 *              update
 *          else
 *              persist()
 *     persist :insert data (cannot provide id in persist())
 *   Dynamic Query / Criteria Query
 *      search by : age, sex, race, region..
 *      Builder
 *      select ... from ... join where ... and ,... or
 *   Spring Data JPA
 *
 *   @Repository
 *   public interface XXXRepository extends JpaRepository<Student, String> {
 *
 *      @Query
 *      ....
 *   }
 *
 *
 *  Tomorrow: Security
 *
 *
 */

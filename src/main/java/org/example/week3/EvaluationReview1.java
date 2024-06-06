package org.example.week3;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 1.please write down design document for your most recent project.
 * design document includes
 * 	1. features / functionalities  / system purpos + overview / clients, why
 * 	2. database schema (tables)
 * 	3. high level design (microservice architecture) and provide module details
 * 	4. rest api design (design 2 - 4 rest apis)
 * 	5.Data flow, prepare 2 - 3 data flow diagram (example: when user client some buttons to upload some files, what happens next, how does request go through your services)
 * 	6. message queue story
 * 	7. biggest challenge(technical challenge)
 * 	8. aws
 * 	9. how did you monitor your application(log monitor / jvm monitor / api monitor)
 * 	10. a story about production support
 * 	11. a story about performance tuning
 * 	12. a story about most recent api you developed
 * 2.Prepare stories based on your resume: example,  where did you use multi-threading in your last project? Where did you use builder design patterns in your last project?
 * 3. Come up team size
 * 4. Design a ci/cd pipeline flow to (AWS / local) depends on your resume project(if you want to keep AWS)
 * 5. daily user / TPS / QPS
 * 6. frontend story
 *
 *
 *
 *
 * static ->
 *      new Instance
 *      static function
 *      Class.forName("..")
 * gc
 *      1. Heap
 *          young gen [eden][s0][s1]
 *          old gen   [            ]
 *      2. Reference Type
 *          SoftReference
 *          WeakReference
 *          PhantomReference
 *      3. Out of Memory
 *      4. Memory Leak
 *          static collection / connection
 *          Heap dump -> java mission control / memory analyzer / Jprofiler
 *          real time -> jvm real time monitor
 *
 * Reactive programming / Rxjs Rxjava
 * stream api
 *      1. functional programming
 *      2. parallel stream (fork join pool)
 *      3. lambda expression + functional interface
 *      4. intermediate operations => map(function), filter(predicate), foreach(consumer), collect(supplier, accumulator, combiner)
 *         Function :  1 input 1 output
 *         Predicate : 1 input boolean output
 *         Consumer : 1 input no output
 *         Supplier : no input 1 output
 *         terminal operations
 *
 *    list.stream().map(x -> x).filter(x -> x > 3).collect(Collectors.toList())
 *        RP1  <->   RP2     <->  RP3             <->  RP4
 *        Sink1     ->  Sink2     ->  Sink3            ->  Sink4
 *
 */
class StreamAPITest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Set<Integer> res = list.stream().collect(
                    HashSet::new,
                    (set, ele) -> set.add(ele),
                    (s1, s2) -> s1.addAll(s2)
                );
        System.out.println(res);

        for(int v: list) {
            list.add(4);
        }
    }
}

/**
 * fail safe vs fail fast
 * concurrent modification exception ?
 */

/**
 * CAS / Volatile => AbstractQueuedSynchronizer -> ReentrantLock / CountDownLatch / CyclicBarrier / Semaphore
 * Synchronized
 *
 * concurrent hashmap
 * ReentrantLock
 *      obj(T4)   ->   T2 -> T3
 *
 *      Condition ; waiting list
 *
 * thread states
 * Synchronized + wait()
 * notify notifyAll()
 * sleep()
 *
 * Synchronized
 *      -> fair lock
 *      -> for same obj -> single waiting list
 *      -> ..
 *
 *
 *
 */

/**
 * blocking queue
 */
class MyArrayBlockingQueue<E> {
    private final Object[] queue;
    private final ReentrantLock lock;
    private final Condition full;
    private final Condition empty;
    private int size;
    private int start, end;

    public MyArrayBlockingQueue(int size) {
        this.size = size;
        queue = new Object[size];
        lock = new ReentrantLock();
        full = lock.newCondition();
        empty = lock.newCondition();
    }

    public E poll() {
        lock.lock();
        E ele = null;
        try {
            while(size == 0) {
                empty.await();
            }
            ele = (E)queue[start++];
            start %= queue.length;
            size--;
            full.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return ele;
    }

    public void offer(E e) {
        lock.lock();
        try {
            while (size == queue.length) {
                full.await();
            }
            queue[end++] = e;
            end %= queue.length;
            size++;
            empty.signal();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
/**
 * ForkJoinPool vs ThreadPoolExecutor
 * callable vs runnable	 where do we use callable
 * Thread pool : executor vs executors vs executorService
 * completable future vs Future get() vs join()
 * CompletableFuture f1, f2, f3
 * CompletableFuture<Void> cf = CompletableFuture.allOf(array of CompletableFuture)
 * return cf.thenApply(Void -> {
 *     f1.join()
 *     f2.join()
 *     f3.join()
 *     return res;
 * }).join();
 */
class CFTest {
    public static void main(String[] args) {

    }
}
/**
 * TimeWheel
 * [0][1][2]...[59]  min
 *           i
 * [0][1][2].[40].[59]  second
 *  j
 *
 * msg, 30min 28s
 * new Node(msg, timestamp)
 *
 *
 * HashedTimeWheel
 */

/**
 * ioc vs di
 *      1. @Controller, @Component, @Service, @Repository, @Bean
 *      2. @Autowired   -> setter, constructor, field
 *          interface A -> impl1, impl2
 *          private A impl1;
 *      3. @Qualifier -> By Name
 *
 * SpringMVC
 *
 *      -> dispatcherServlet (/*) -> handler mapping -> controller
 *              |
 *            view resolver
 *             |
 *           model and view
 *
 *
 *       -> dispatcherServlet (/*) -> handler mapping -> controller
 *             |
 *          Http Message Converter (Jackson)
 *            |
 *          json
 *
 *  @RestController  =  @ResponseBody + @Controller
 *  @Controller
 *
 *  db sequence generates id
 *
 * "/v1/users" + post  => return created id
 * "/v1/users" + get
 * "/v1/users/{id}" + get
 * "/v1/users/{id}" + put(idempotent)
 * "/v1/users/{id}" + delete
 * what is rest api
 * TDD
 *      1. requirements (features / input / output / error / corner cases)..
 *      2. design flow / structure / code (interface / abstract class / methods(TODO))
 *      3. write test cases (junit test / ..)
 *      4. impl methods / logic
 *      5. run test cases
 *      6. debug..
 *
 * Good Rest api / meets production standard
 *      follow rest api standard / http standard
 *      security ->
 *      versioning / Flexibility / Extensibility => SOLID
 *      error handling + logging -> debug / monitor
 *      performance (tuning)
 *      documentation
 *      CAP -> CP / AP
 *
 *
 * SOLID
 *      Single Responsibility
 *      Open Close
 *      Liskov Substitution
 *      Interface Segregation
 *      Dependency Inversion
 *
 * int[] arr = new int[Integer.MAX_VALUE];
 * class Student {
 *     public boolean equals(Object obj) {
 *         //obj is null ? obj == this
 *         //class type
 *         //convert to xx type
 *         //compare value
 *     }
 *     public int hashCode() {
 *         return Objects.hash(val1, val2);
 *     }
 * }
 * HashMap<Student, Integer> map;
 *
 *
 *
 * 1. RestTemplate -> send request to 3rd party api
 * 2. get result return to user
 *
 * github ->
 */

class Student {
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

/**
 *  tomcat
 *
 *          ->  waiting queue ->  worker thread1
 *                            ->  worker thread2
 *                            -> max worker thread
 *
 *  1. constructor injection
 *  2. return ResponseEntity + HttpStatus
 *  3. @GetMapping(params = "")
 *  4. impl logic in service layer
 *  5. configure resttemplate bean in configuration class
 *  6. inject rest template
 *  7. centralize URL (properties, utilties class)
 *  8. CompletableFuture.join()
 *  9. RestTemplate.getForObject(url, Type[].class)
 *  10. Exception => @ExceptionHandler + @Controller
 *  -----------------------------------------------------------
 *  Design Pattern
 */
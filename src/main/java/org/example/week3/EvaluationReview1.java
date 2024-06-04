package org.example.week3;

import java.util.*;

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
 * blocking queue
 *
 * callable vs runnable	 where do we use callable
 * Thread pool : executor vs executors vs executorService
 * completable future vs Future get() vs join()
 *
 * ioc vs di
 * controller "/all", "/get/all"
 * restcontroller vs controller
 * what is rest api
 *
 * Design Pattern ->
 */

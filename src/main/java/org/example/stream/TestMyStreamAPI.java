package org.example.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TestMyStreamAPI {
    public static void main(String[] args) {
        List<Integer> l = new ArrayList<>();
        l.add(4);
        l.add(3);
        IStream<Integer> stream = IStream.of(l);
        l.add(2);
        l.add(1);
        List<String> ans = stream.map(x -> x * 2).map(x -> String.valueOf(x)).sorted((v1, v2) -> v1.compareTo(v2)).collect(() -> new ArrayList(), (res, val) -> res.add(val));
        System.out.println(ans);
//        int v = stream.map(x -> x * 2).sorted((v1, v2) -> v1.compareTo(v2)).reduce(0, (res, val) -> res + val);
//        System.out.println(v);
    }
}
/**
 *  stream.map(x -> x * 2)
 *         .map(x -> String.valueOf(x))
 *         .sorted((v1, v2) -> v1.compareTo(v2))
 *         .collect(() -> new ArrayList(), (res, val) -> res.add(val));
 *
 *  1. Operation(head , iterator supplier)
 *  2. Operation(x -> x * 2) {
 *          rewrite create new Sink logic
 *          -> new Sink() {
 *              override accept function
 *          }
 *      }
 * 3. ..
 * 4..
 * Sink -> Sink -> Sink -> Sink
 */
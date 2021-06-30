package com.nelsw.euler;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.math3.primes.Primes;

import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.function.LongPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Log4j2
public class Problem60 {

    public Problem60() {

        var then = Instant.now();

        var primes = IntStream
                .rangeClosed(2, (int) Math.pow(10, 5))
                .parallel()
                .boxed()
                .filter(Primes::isPrime)
                .sorted()
                .collect(Collectors.toList());

        var max = primes.get(primes.size() - 1);

        var pairs = new HashMap<Integer, List<Long>>();

        var results = new ArrayList<Integer>();
        var best    = max;

        for (int p : primes) {

            if (p * 5 > best) {
                break;
            }

            if (!pairs.containsKey(p)) {
                putPairs(pairs, primes, p, best - p * 5);
            }

            var thisResult = work(pairs, primes, best, p);
            if (thisResult == max) {
                continue;
            }

            results.add(thisResult);
            best = thisResult;

            var lastResult = results.get(results.size() - 1);
            if (thisResult < lastResult) {
                break;
            }
        }

        Collections.sort(results);

        var actual   = results.get(0);
        var expected = 26_033;
        if (actual == expected) {
            log.info("✅ - {} - {}", Duration.between(then, Instant.now()), actual);
        } else {
            log.warn("❌");
        }
    }

    private static int work(
            Map<Integer, List<Long>> pairs,
            List<Integer> primes,
            int best,
            long p0) {

        for (long p1 : pairs.get((int) p0)) {

            var sum = p0 + (p1 * 4);
            if (sum > best) {
                break;
            }

            if (!pairs.containsKey((int) p1)) {
                putPairs(pairs, primes, p1, best - sum);
            }

            for (long p2 : pairs.getOrDefault((int) p1, Collections.emptyList())) {

                sum = p0 + p1 + (p2 * 3);
                if (sum > best) {
                    break;
                }

                if (!isPrimeCat(p2).test(p0)) {
                    continue;
                }

                if (!pairs.containsKey((int) p2)) {
                    putPairs(pairs, primes, p2, best - sum);
                }

                for (long p3 : pairs.getOrDefault((int) p2, Collections.emptyList())) {

                    sum = p0 + p1 + p2 + (p3 * 2);
                    if (sum > best) {
                        break;
                    }

                    if (!LongStream.of(p0, p1).allMatch(isPrimeCat(p3))) {
                        continue;
                    }

                    if (!pairs.containsKey((int) p3)) {
                        putPairs(pairs, primes, p3, best - sum);
                    }

                    var result = pairs
                            .get((int) p3)
                            .stream()
                            .parallel()
                            .filter(p4 -> LongStream.of(p0, p1, p2, p3, p4).sum() < best)
                            .mapToLong(Long::intValue)
                            .filter(isPrimeCat(p0)
                                    .and(isPrimeCat(p1)
                                            .and(isPrimeCat(p2)
                                                    .and(isPrimeCat(p3)))))
                            .map(p4 -> LongStream.of(p0, p1, p2, p3, p4).sum())
                            .min();

                    if (result.isEmpty()) {
                        continue;
                    }

                    return (int) result.getAsLong();
                }
            }
        }

        return best;
    }

    private static void putPairs(Map<Integer, List<Long>> pairs, List<Integer> primes, long prime, long max) {
        pairs.put((int) prime, primes
                .stream()
                .parallel()
                .mapToLong(Integer::longValue)
                .filter(q -> q > prime)
                .filter(q -> q < max)
                .filter(isPrimeCat(prime))
                .boxed()
                .collect(Collectors.toList()));
    }

    private static LongPredicate isPrimeCat(long thatPrime) {
        return thisPrime ->
                Primes.isPrime((int) Long.parseLong(thatPrime + String.valueOf(thisPrime))) &&
                        Primes.isPrime((int) Long.parseLong(thisPrime + String.valueOf(thatPrime)));

    }

}

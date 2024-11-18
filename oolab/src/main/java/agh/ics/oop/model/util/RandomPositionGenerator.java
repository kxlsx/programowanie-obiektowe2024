package agh.ics.oop.model.util;

import agh.ics.oop.model.Grass;
import agh.ics.oop.model.Vector2d;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    Stream<Vector2d> innerStream;

    public RandomPositionGenerator(int maxX, int maxY, int limit) {
        this(maxX, maxY, limit, new Random());
    }

    public RandomPositionGenerator(int maxX, int maxY, int limit, Random rnd) {
        /* Zostawiam tutaj ten kod tylko dlatego, że mi się podoba:
        Random generator = new Random();
        PrimitiveIterator.OfInt randomYIter = generator
                .ints(0, upperLimit)
                .iterator();
        generator
                .ints(0, upperLimit)
                .mapToObj(x -> new Vector2d(x, randomYIter.next()))
                .distinct()
                .limit(grassClumpCount)
                .forEach(v -> grassClumps.put(v, new Grass(v)));
         */

        List<Vector2d> vects = new LinkedList<>();
        for(int x = 0; x < maxX; x++) {
            for(int y  = 0; y < maxY; y++) {
                vects.add(new Vector2d(x, y));
            }
        }
        Collections.shuffle(vects, rnd);
        innerStream = vects.stream().limit(limit);
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return innerStream.iterator();
    }
}

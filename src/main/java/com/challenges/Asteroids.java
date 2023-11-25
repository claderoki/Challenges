package com.challenges;

import com.challenges.base.CodeChallenge;
import com.challenges.base.InputOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.com/problems/asteroid-collision
 */
public class Asteroids extends CodeChallenge<int[], int[]> {
    enum Direction {
        LEFT,
        RIGHT
    }

    record Asteroid(int index, Direction direction, int size) {
        public boolean checkCollision(Asteroid other) {
            return direction.equals(Direction.LEFT) && other.direction.equals(Direction.RIGHT);
        }
    }

    private Asteroid[] findColliders(List<Asteroid> asteroids) {
        Asteroid previous = null;
        for(var current: asteroids) {
            if (previous != null && current.checkCollision(previous)) {
                return new Asteroid[]{previous, current};
            }
            previous = current;
        }
        return null;
    }

    public int[] test(int[] asteroids) {
        AtomicInteger in = new AtomicInteger();
        List<Asteroid> remaining = new ArrayList<>(Arrays.stream(asteroids)
            .boxed()
            .map(c -> new Asteroid(in.getAndIncrement(), c > 0 ? Direction.RIGHT : Direction.LEFT, Math.abs(c)))
            .toList());

        Asteroid[] colliding;
        do {
            colliding = findColliders(remaining);
            if (colliding != null) {
                Asteroid first = colliding[0];
                Asteroid second = colliding[1];
                if (first.size == second.size) {
                    remaining.remove(first);
                    remaining.remove(second);
                } else if (first.size > second.size) {
                    remaining.remove(second);
                } else {
                    remaining.remove(first);
                }
            }
        } while (colliding != null);

        int[] intArray = new int[remaining.size()];
        for (int i = 0; i < intArray.length; i++) {
            var asteroid = remaining.get(i);
            intArray[i] = asteroid.direction.equals(Direction.LEFT) ? -asteroid.size : asteroid.size;
        }
        return intArray;
    }

    @Override
    public boolean isEqual(int[] output1, int[] output2) {
        return Arrays.equals(output1, output2);
    }

    @Override
    public List<InputOutput<int[], int[]>> getInputAndDesiredOutputs() {
        return List.of(
            new InputOutput<>(array(-2,-1,2,-2), array(-2,-1)),
            new InputOutput<>(array(-2,1,-2,1), array(-2,-2,1)),
            new InputOutput<>(array(5,10,-5) , array(5,10)),
            new InputOutput<>(array(8,-8), array()),
            new InputOutput<>(array(10,2,-5), array(10)),
            new InputOutput<>(array(-2,-1,1,2), array(-2,-1,1,2)),
            new InputOutput<>(array(-2,1,-2,1), array(-2,-2,1))
        );
    }
}

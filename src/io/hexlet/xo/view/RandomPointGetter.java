package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Point;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.util.Random;

public class RandomPointGetter implements IPointGetter {

    private static final Random random = new Random();

    @Override
    public Point getMovePoint(final Field field) {
        Point result = getRandomPoint();
        try {
            while (field.getFigure(result) != null) {
                result = getRandomPoint();
            }
        } catch(final InvalidPointException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Point getRandomPoint() {
        return new Point(
                getRandomInt(),
                getRandomInt()
        );
    }

    private int getRandomInt() {
        return Math.abs(random.nextInt() % 3);
    }

}

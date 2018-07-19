package io.hexlet.xo.view;

import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Point;
import io.hexlet.xo.model.exceptions.InvalidPointException;

public class AIPointGetter<T> implements IPointGetter<T> {

    @Override
    public Point getMovePoint(final Field<T> field) {
        Point p00 = new Point(field.getSize() - field.getSize(),field.getSize() - field.getSize());
        Point pMaxMax = new Point(field.getSize() - 1,field.getSize() - 1);
        Point pMax0 = new Point(field.getSize() -1,field.getSize() - field.getSize());
        Point p0Max = new Point(field.getSize() - field.getSize(),field.getSize() - 1);
        Point pCentre = new Point((field.getSize() - 1) / 2,(field.getSize() - 1) / 2 );

        Point[] startPoint = {p00, pMaxMax, pMax0, p0Max, pCentre};

        Point p1 = new Point(field.getSize() - field.getSize(),(field.getSize() - 1) / 2);
        Point p2 = new Point((field.getSize() - 1) / 2,field.getSize() - field.getSize());
        Point p3 = new Point(field.getSize() -1,(field.getSize() - 1) / 2);
        Point p4 = new Point((field.getSize() - 1) / 2, (field.getSize() - 1));

        Point[] secondPoint = {p1, p2, p3, p4};

        try {
            final Point succesfullPointX = getSuccessfulCoordinate(field, (T) Figure.X);
            if (succesfullPointX != null) {
                return succesfullPointX;
            }

            final Point succesfullPointO = getSuccessfulCoordinate(field, (T) Figure.O);
            if (succesfullPointO != null) {
                return succesfullPointO;
            }

            if (field.getFigure(pCentre) == null) {
                return pCentre;
            }

            for (Point tempPoint : startPoint){
                if (field.getFigure(tempPoint) == null)
                    return tempPoint;
            }

            for (Point tempPoint : secondPoint) {
                if (field.getFigure(tempPoint) == null)
                    return tempPoint;
            }
        } catch (final InvalidPointException e) {
            e.printStackTrace();
        }

        return new RandomPointGetter<T>().getMovePoint(field);

    }

    private Point getSuccessfulCoordinate(final Field<T> field, final T figure) throws InvalidPointException{
        final int row = checkRows(field, figure);
        if (row != 99) {
            for (int i = 0; i < field.getSize(); i++) {
                if (field.getFigure(new Point(row, i)) == null) {
                    return new Point(row, i);
                }
            }
        }

        final int col = checkCols(field, figure);
        if (col != 99) {
            for (int i = 0; i < field.getSize(); i++) {
                if (field.getFigure(new Point(i, col)) == null) {
                    return new Point(i, col);
                }
            }
        }

        final int diag = checkDiags(field, figure);
        if (diag == 10) {
            for (int i = 0; i < field.getSize(); i++) {
                if (field.getFigure(new Point(i, i)) == null) {
                    return new Point(i, i);
                }
            }
        } else if (diag == 20) {
            for (int i = 0; i < field.getSize(); i++) {
                if (field.getFigure(new Point(i, field.getSize() - i - 1)) == null) {
                    return new Point(i, field.getSize() - i - 1);
                }
            }
        }

        return null;
    }

    private int checkRows(final Field<T> field, final T figure) throws InvalidPointException {
        for (int i = 0; i < field.getSize(); i++) {
            int count = 0;
            for (int j = 0; j < field.getSize(); j++) {
                if (field.getFigure(new Point(i, j)) == figure) {
                    count++;
                    if (count == field.getSize() - 1) {
                        return i;
                    }
                }
            }
        }
        return 99;
    }

    private int checkCols(final Field<T> field, final T figure) throws InvalidPointException {
        for (int i = 0; i < field.getSize(); i++) {
            int count = 0;
            for (int j = 0; j < field.getSize(); j++) {
                if (field.getFigure(new Point(j, i)) == figure) {
                    count++;
                    if (count == field.getSize() - 1) {
                        return i;
                    }
                }
            }
        }
        return 99;
    }

    private int checkDiags(final Field<T> field, final T figure) throws InvalidPointException {
        int count = 0;
        for (int i = 0; i < field.getSize(); i++) {
            if (field.getFigure(new Point(i, i)) == figure) {
                count++;
                if (count == field.getSize() - 1) {
                    return 10;
                }
            }
        }

        count = 0;
        for (int i = 0; i < field.getSize(); i++) {
            if (field.getFigure(new Point(i, field.getSize() - i - 1)) == figure) {
                count++;
                if (count == field.getSize() - 1) {
                    return 20;
                }
            }
        }
        return 99;
    }
}

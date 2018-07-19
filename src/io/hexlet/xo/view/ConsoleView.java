package io.hexlet.xo.view;


import io.hexlet.xo.controllers.CurrentMoveController;
import io.hexlet.xo.controllers.MoveController;
import io.hexlet.xo.controllers.WinnerController;
import io.hexlet.xo.model.*;
import io.hexlet.xo.model.exceptions.AlreadyOccupiedException;
import io.hexlet.xo.model.exceptions.InvalidPointException;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ConsoleView<T> {

    private final CurrentMoveController<T> currentMoveController = new CurrentMoveController<>();
    private final WinnerController<T> winnerController = new WinnerController<>();
    private final MoveController<T> moveController = new MoveController<>();
    private final AIPointGetter<T> aiPointGetter = new AIPointGetter<>();

    public void show(final Game<T> game) {
        System.out.format("Game name: %s\n", game.getName());
        final Field<T> field = game.getField();
        for (int x = 0; x < field.getSize(); x++) {
            if (x != 0) {
                printSeparator();
            }
            printLine(field, x);
        }
    }

    public boolean move(final Game<T> game) {
        final Field<T> field = game.getField();
        final T winner = winnerController.getWinner(field);
        if (winner != null) {
            System.out.format("Winner is: %s\n", winner);
            return false;
        }

        final T currentFigure = currentMoveController.currentMove(field);
        if (currentFigure == null) {
            System.out.println("No winner and no moves left!");
            return false;
        }
        System.out.format("Now is moving %s\n", currentFigure);

        Point point = null;
        if (currentFigure == Figure.O) {
            point = aiPointGetter.getMovePoint(field);
        } else {
            point = askPoint();
        }
        try {
            moveController.applyFigure(field, point, currentFigure);
        } catch (final InvalidPointException | AlreadyOccupiedException e) {
            System.out.println("Point is invalid!");
        }
        return true;
    }

    private Point askPoint() {
        return new Point(askCoordinate("X") - 1, askCoordinate("Y") - 1);
    }

    private int askCoordinate(final String coordinateName) {
        System.out.format("Please, input %s:", coordinateName);
        final Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (final InputMismatchException e) {
            System.out.println("!!!!");
            return askCoordinate(coordinateName);
        }
    }

    private void printLine(final Field<T> field, final int x) {
        for (int y = 0; y < field.getSize(); y++) {
            if (y != 0) {
                System.out.print("|");
            }
            System.out.print(" ");
            final T figure;
            try {
                figure = field.getFigure(new Point(x, y));
            } catch (final InvalidPointException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
            System.out.print(figure != null ? figure : " ");
            System.out.print(" ");
        }
        System.out.println();
    }

    private void printSeparator() {
        System.out.println("~~~~~~~~~~~");
    }

}

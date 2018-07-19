package io.hexlet.xo;


import io.hexlet.xo.model.Field;
import io.hexlet.xo.model.Figure;
import io.hexlet.xo.model.Game;
import io.hexlet.xo.model.Player;
import io.hexlet.xo.model.exceptions.InvalidBoardSizeException;
import io.hexlet.xo.view.ConsoleView;

public class XOCLI {

    public static void main(final String[] args) {

        final int fieldSize = 3;
        final String name1 = "Player_1";
        final String name2 = "Player_2";

        final Player<Figure>[] players = new Player[2];
        players[0] = new Player<>(name1, Figure.X);
        players[1] = new Player<>(name2, Figure.O);

        Field<Figure> field = null;
        try {
            field = new Field<>(fieldSize);
        } catch (InvalidBoardSizeException e){
            e.printStackTrace();
        }
        final Game<Figure> gameXO = new Game<>(players, field, "XO");
        final ConsoleView<Figure> consoleView = new ConsoleView<>();

        consoleView.show(gameXO);
        while (consoleView.move(gameXO)) {
            consoleView.show(gameXO);
        }
    }

}


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JOptionPane;
import javax.swing.JButton;

public class TicTacToeEngine implements ActionListener {
    TicTacToe parent;

    TicTacToeEngine(TicTacToe parent) {
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
        //  Получаем источник события
        JButton clickedButton = (JButton) e.getSource();
//        String dispFieldText = parent.displayFormattedField.getText();
        double displayValue = 0;

        Object src = e.getSource();

        if (clickedButton == parent.button_new_game) {
            for (int i = 0; i < 9; i++) {
                parent.buttons[i].setEnabled(true);
                parent.buttons[i].setLabel("");
                parent.buttons[i].setBackground(Color.GRAY);
            }
            parent.emptySquaresLeft = 9;
            parent.button_new_game.setEnabled(false);

            return;
        }

        String winner = "";
        // Это одна из клеток?

        for (int i = 0; i < 9; i++) {
            if (clickedButton == parent.buttons[i]) {
                parent.buttons[i].setLabel("X");
                winner = lookForWinner();

                if (!"".equals(winner)) {
                    endTheGame();
                } else {
                    computerMove();
                    winner = lookForWinner();
                    if (!"".equals(winner)) {
                        endTheGame();
                    }
                }
                break;
            }
        }
    }

    private void endTheGame() {
    }

    /*
    * Этот метод прменяет набор правил, чтобы найти лучший компьютерный ход. Если хороший ход не найден, выбирается
    * случайная клетка.
    * */

    private void computerMove() {
        int selectedSquare;

    // Сначала компьютер пытается найти пустую клетку рядом с двумя клетками, чтобы выиграть.

        selectedSquare = findEmptySquare("O");
        
    // Если он не может найти 2 нолика, то хотя бы не даёт оппоненту сделать ряд из 3-х крестиков, поместив нолик рядом
    // с 2-мя крестиками.

        if (selectedSquare == -1){
            selectedSquare = findEmptySquare("X");
        }

    // Если selectedSquare ысё ещё равен -1, то пытается занять центральную клетку.
        if (selectedSquare == -1 &&  (parent.buttons[4].getLabel().equals("")) ){
            selectedSquare = 4;
        }

    // Не повезло с центральной  просто занимаем любую случайную клетку
        if (selectedSquare == -1){
            selectedSquare = getRandomSquare();
        }
        parent.buttons[selectedSquare].setLabel("0");
    }

//    @TODO дописать метод getRandomSquare
    private int getRandomSquare() {
        return 1;
    }


    private int findEmptySquare(String o) {
        return 1;
    }


    /*
     * Этот метод вызывается после каждого хода, чтобы выявить победителя.
     * @return "X", "O", "T" - ничья, "" - нет победителя
     * */

    private String lookForWinner() {
        String theWinner = "";
        parent.emptySquaresLeft--;

        if (parent.emptySquaresLeft == 0) {
            return "T"; // Это ничья. T от английского слова tie
        }

        // Проверяем ряд 1 - элементы массива 0, 1, 2

        if (parent.buttons[0].getLabel().equals("") &&
                parent.buttons[0].getLabel().equals(parent.buttons[1].getLabel()) &&
                parent.buttons[0].getLabel().equals(parent.buttons[2].getLabel())) {
            theWinner = parent.buttons[0].getLabel();
            highlightWinner(0, 1, 2);

        } else
            // Проверяем ряд 2 - элементы массива 3, 4, 5
            if (parent.buttons[0].getLabel().equals("") &&
                    parent.buttons[0].getLabel().equals(parent.buttons[4].getLabel()) &&
                    parent.buttons[0].getLabel().equals(parent.buttons[5].getLabel())) {
                theWinner = parent.buttons[3].getLabel();
                highlightWinner(3, 4, 5);
            } else
                // Проверяем ряд 3 - элементы массива 6, 7, 8
                if (parent.buttons[0].getLabel().equals("") &&
                        parent.buttons[0].getLabel().equals(parent.buttons[7].getLabel()) &&
                        parent.buttons[0].getLabel().equals(parent.buttons[8].getLabel())) {
                    theWinner = parent.buttons[6].getLabel();
                    highlightWinner(6, 7, 8);
                } else
                    // Проверяем колонку 1 - элементы массива 0, 3, 6
                    if (parent.buttons[0].getLabel().equals("") &&
                            parent.buttons[0].getLabel().equals(parent.buttons[3].getLabel()) &&
                            parent.buttons[0].getLabel().equals(parent.buttons[6].getLabel())) {
                        theWinner = parent.buttons[0].getLabel();
                        highlightWinner(0, 3, 6);
                    } else
                        // Проверяем колонку 2 - элементы массива 1, 4, 7
                        if (parent.buttons[0].getLabel().equals("") &&
                                parent.buttons[0].getLabel().equals(parent.buttons[4].getLabel()) &&
                                parent.buttons[0].getLabel().equals(parent.buttons[7].getLabel())) {
                            theWinner = parent.buttons[1].getLabel();
                            highlightWinner(1, 4, 7);
                        } else
                            // Проверяем колонку 3 - элементы массива 2, 5, 8
                            if (parent.buttons[0].getLabel().equals("") &&
                                    parent.buttons[0].getLabel().equals(parent.buttons[5].getLabel()) &&
                                    parent.buttons[0].getLabel().equals(parent.buttons[8].getLabel())) {
                                theWinner = parent.buttons[2].getLabel();
                                highlightWinner(2, 5, 8);
                            } else
                                // Проверяем диагональ 1 - элементы массива 0, 4, 8
                                if (parent.buttons[0].getLabel().equals("") &&
                                        parent.buttons[0].getLabel().equals(parent.buttons[4].getLabel()) &&
                                        parent.buttons[0].getLabel().equals(parent.buttons[8].getLabel())) {
                                    theWinner = parent.buttons[0].getLabel();
                                    highlightWinner(0, 4, 8);
                                } else
                                    // Проверяем диагональ 2 - элементы массива 3, 4, 6
                                    if (parent.buttons[0].getLabel().equals("") &&
                                            parent.buttons[0].getLabel().equals(parent.buttons[4].getLabel()) &&
                                            parent.buttons[0].getLabel().equals(parent.buttons[6].getLabel())) {
                                        theWinner = parent.buttons[2].getLabel();
                                        highlightWinner(3, 4, 6);
                                    }
        return theWinner;
    }

    private void highlightWinner(int i, int i1, int i2) {
    }
}
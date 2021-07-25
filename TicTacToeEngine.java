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
        if (winner.equals("X")){
            parent.topPanelLabel.setText("You won!");
        } else if (winner.equals("O")){
            parent.topPanelLabel.setText("You lost!");
        } else if (winner.equals("T")){
            parent.topPanelLabel.setText("It's a tie!");
        }

    }

    // делаем недоступными клетки и доступной кнопку "NewGame"
    private void endTheGame() {
        parent.button_new_game.setEnabled(true);
        for (int i = 0; i < 9; i++) {
            parent.buttons[i].setEnabled(false);
        }
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

        if (selectedSquare == -1) {
            selectedSquare = findEmptySquare("X");
        }

        // Если selectedSquare ысё ещё равен -1, то пытается занять центральную клетку.
        if (selectedSquare == -1 && (parent.buttons[4].getLabel().equals(""))) {
            selectedSquare = 4;
        }

        // Не повезло с центральной  просто занимаем любую случайную клетку
        if (selectedSquare == -1) {
            selectedSquare = getRandomSquare();
        }
        parent.buttons[selectedSquare].setLabel("0");
    }

    //  Этот метод выбирает любую пустую клетку
//  @return случайно выборанный номер клетки
    private int getRandomSquare() {
        boolean gotEmptySquare = false;
        int selectedSquare = -1;

        do {
            selectedSquare = (int) (Math.random() * 9);
            if (parent.buttons[selectedSquare].getLabel().equals("")) {
                gotEmptySquare = true; // Чтобы закончить цикл
            }
        } while (!gotEmptySquare);
        return selectedSquare;
    }

    /*
     * Этот метод проверяет каждый ряд, колонку и диагональ, чтобы узнать, есть ли в ней 2 клетки с одинаковыми надписями
     * и пустой клеткой
     * @param передаётся "Х" для пользователя и "О" для компа
     * @return количество свободных клеток, или -1, если не найдено 2 клетки с одинаковыми надписями
     * */

    private int findEmptySquare(String player) {
        int weight[] = new int[9];
        for (int i = 0; i < 9; i++) {
            if (parent.buttons[i].getLabel().equals("O")) {
                weight[i] = -1;
            } else if (parent.buttons[i].getLabel().equals("X")) {
                weight[i] = 1;
            } else {
                weight[i] = 0;
            }
        }
        int twoWeights = player.equals("O") ? -2 : 2;

        // Проверим есть ли в ряду 1 2 однинаковые кллетки и  одна пустая?
        if (weight[0] + weight[1] + weight[2] == twoWeights) {
            if (weight[0] == 0) {
                return 0;
            } else if (weight[1] == 0) {
                return 1;
            } else {
                return 2;
            }
        }
        // Проверим есть ли в ряду 2 2 однинаковые кллетки и  одна пустая?
        if (weight[3] + weight[4] + weight[5] == twoWeights) {
            if (weight[3] == 0) {
                return 3;
            } else if (weight[4] == 0) {
                return 4;
            } else {
                return 5;
            }
        }
        // Проверим есть ли в ряду 3 2 однинаковые кллетки и  одна пустая?
        if (weight[6] + weight[7] + weight[8] == twoWeights) {
            if (weight[6] == 0) {
                return 6;
            } else if (weight[7] == 0) {
                return 7;
            } else {
                return 8;
            }
        }
        // Проверим есть ли в колонке 1 2 однинаковые кллетки и  одна пустая?
        if (weight[0] + weight[3] + weight[6] == twoWeights) {
            if (weight[0] == 0) {
                return 0;
            } else if (weight[3] == 0) {
                return 3;
            } else {
                return 6;
            }
        }
        // Проверим есть ли в колонке 2 2 однинаковые кллетки и  одна пустая?
        if (weight[1] + weight[4] + weight[7] == twoWeights) {
            if (weight[1] == 0) {
                return 1;
            } else if (weight[4] == 0) {
                return 4;
            } else {
                return 7;
            }
        }
        // Проверим есть ли в колонке 3 2 однинаковые кллетки и  одна пустая?
        if (weight[2] + weight[5] + weight[8] == twoWeights) {
            if (weight[2] == 0) {
                return 2;
            } else if (weight[5] == 0) {
                return 5;
            } else {
                return 8;
            }
        }
        // Проверим есть ли в диагонали 1 2 однинаковые кллетки и  одна пустая?
        if (weight[0] + weight[4] + weight[8] == twoWeights) {
            if (weight[0] == 0) {
                return 0;
            } else if (weight[4] == 0) {
                return 4;
            } else {
                return 8;
            }
        }
        // Проверим есть ли в диагонали 2 2 однинаковые кллетки и  одна пустая?
        if (weight[2] + weight[4] + weight[6] == twoWeights) {
            if (weight[2] == 0) {
                return 2;
            } else if (weight[4] == 0) {
                return 4;
            } else {
                return 6;
            }
        }
        // Не найдено двух одинаковых соседних клеток
        return -1;
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

        if (!parent.buttons[0].getLabel().equals("") &&
                parent.buttons[0].getLabel().equals(parent.buttons[1].getLabel()) &&
                parent.buttons[0].getLabel().equals(parent.buttons[2].getLabel())) {
            theWinner = parent.buttons[0].getLabel();
            highlightWinner(0, 1, 2);
        } else
            // Проверяем ряд 2 - элементы массива 3, 4, 5
            if (!parent.buttons[3].getLabel().equals("") &&
                    parent.buttons[3].getLabel().equals(parent.buttons[4].getLabel()) &&
                    parent.buttons[3].getLabel().equals(parent.buttons[5].getLabel())) {
                theWinner = parent.buttons[3].getLabel();
                highlightWinner(3, 4, 5);
            } else
                // Проверяем ряд 3 - элементы массива 6, 7, 8
                if (!parent.buttons[6].getLabel().equals("") &&
                        parent.buttons[6].getLabel().equals(parent.buttons[7].getLabel()) &&
                        parent.buttons[6].getLabel().equals(parent.buttons[8].getLabel())) {
                    theWinner = parent.buttons[6].getLabel();
                    highlightWinner(6, 7, 8);
                } else
                    // Проверяем колонку 1 - элементы массива 0, 3, 6
                    if (!parent.buttons[0].getLabel().equals("") &&
                            parent.buttons[0].getLabel().equals(parent.buttons[3].getLabel()) &&
                            parent.buttons[0].getLabel().equals(parent.buttons[6].getLabel())) {
                        theWinner = parent.buttons[0].getLabel();
                        highlightWinner(0, 3, 6);
                    } else
                        // Проверяем колонку 2 - элементы массива 1, 4, 7
                        if (!parent.buttons[1].getLabel().equals("") &&
                                parent.buttons[1].getLabel().equals(parent.buttons[4].getLabel()) &&
                                parent.buttons[1].getLabel().equals(parent.buttons[7].getLabel())) {
                            theWinner = parent.buttons[1].getLabel();
                            highlightWinner(1, 4, 7);
                        } else
                            // Проверяем колонку 3 - элементы массива 2, 5, 8
                            if (!parent.buttons[2].getLabel().equals("") &&
                                    parent.buttons[2].getLabel().equals(parent.buttons[5].getLabel()) &&
                                    parent.buttons[2].getLabel().equals(parent.buttons[8].getLabel())) {
                                theWinner = parent.buttons[2].getLabel();
                                highlightWinner(2, 5, 8);
                            } else
                                // Проверяем диагональ 1 - элементы массива 0, 4, 8
                                if (!parent.buttons[0].getLabel().equals("") &&
                                        parent.buttons[0].getLabel().equals(parent.buttons[4].getLabel()) &&
                                        parent.buttons[0].getLabel().equals(parent.buttons[8].getLabel())) {
                                    theWinner = parent.buttons[0].getLabel();
                                    highlightWinner(0, 4, 8);
                                } else
                                    // Проверяем диагональ 2 - элементы массива 3, 4, 6
                                    if (!parent.buttons[2].getLabel().equals("") &&
                                            parent.buttons[2].getLabel().equals(parent.buttons[4].getLabel()) &&
                                            parent.buttons[2].getLabel().equals(parent.buttons[6].getLabel())) {
                                        theWinner = parent.buttons[2].getLabel();
                                        highlightWinner(2, 4, 6);
                                    }
        return theWinner;
    }

    private void highlightWinner(int win1, int win2, int win3) {
        parent.buttons[win1].setBackground(Color.ORANGE);
        parent.buttons[win2].setBackground(Color.ORANGE);
        parent.buttons[win3].setBackground(Color.ORANGE);
    }
}
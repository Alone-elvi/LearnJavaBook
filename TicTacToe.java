/*
 * Игра в крестики-нолики 3х3
 * */

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;

public class TicTacToe {
    //  Создаём панель
    JPanel windowContent;
    JButton[] buttons = new JButton[9];
    JButton button_new_game = new JButton("New Game");
    JPanel p1, p2, topPanel;
    int emptySquaresLeft = 9;
    JLabel topPanelLabel;

    TicTacToe() {
        topPanel = new JPanel();
        GridLayout topPanelGrid = new GridLayout(1, 3);
        topPanel.setLayout(topPanelGrid);
        topPanelLabel = new JLabel("Your Turn!");
        topPanel.add(topPanelLabel, "South");

        p1 = new JPanel();
        GridLayout centerPanel = new GridLayout(3, 3);
        p1.setLayout(centerPanel);

        //  Тут пробуем создать кнопки помещая значения в массив и добавляем действие
        TicTacToeEngine ticTacToeEngine = new TicTacToeEngine(this);
        // Объявим кнопки, необходимо 9 штук.
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].addActionListener(ticTacToeEngine);
            buttons[i].setBackground(Color.CYAN);
            p1.add(buttons[i]);
        }

        button_new_game = new JButton("New Game");
        button_new_game.addActionListener(ticTacToeEngine);

        //  Задаём менеджер отображения для панели
        windowContent = new JPanel();
        //  Задаём схему для этой панели
        BorderLayout b1 = new BorderLayout();
        windowContent.setLayout(b1);

        //  Создаём панель с GridLayout, которая содержит 9 кнопок. 10 кнопок с числами и кнопки с точной и знаком равно.


        //  Добавляем кнопки на панель из массива

        p2 = new JPanel();
        GridLayout g2 = new GridLayout(1, 3);
        p2.setLayout(g2);

        p2.add(button_new_game);

//  Помещаем панель p1 в центральную область окна.

        windowContent.add("Center", p1);
        windowContent.add("South", p2);
//  Создаём фрейм и задаём его основную панель
        JFrame frame = new JFrame("TicTacToe");
        frame.setContentPane(windowContent);

//  Делаем панель достаточной, чтобы вместить все компоненты

        frame.pack();

//  Наконец отображаем окно.

        frame.setVisible(true);

    }

    public static void main(String[] args) {
        TicTacToe tictactoe = new TicTacToe();
    }
}
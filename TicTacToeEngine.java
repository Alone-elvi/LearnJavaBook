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
        }

        parent.emptySquaresLeft = 9;
        parent.button_new_game.setEnabled(false);

        return;
    }

}
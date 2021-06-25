import javax.swing.*;
import java.awt.FlowLayout;

public class SimpleCalculator {
    public static void main(String[] args) {
//  Создаём панель
        JPanel windowCotent = new JPanel();
//  Задаём менеджер отображения для этой панели
        FlowLayout fl = new FlowLayout();
        windowCotent.setLayout(fl);
//  Создаём компоненты в памяти
        JLabel label1 = new JLabel("Number 1:");
        JTextField field1 = new JTextField(10);
        JLabel label2 = new JLabel("Number 2:");
        JTextField field2 = new JTextField(10);
        JLabel label3 = new JLabel("Sum:");
        JTextField result = new JTextField(10);
        JButton go = new JButton("Add");

//  Добавляем компоненты на панель
        windowCotent.add(label1);
        windowCotent.add(field1);
        windowCotent.add(label2);
        windowCotent.add(field2);
        windowCotent.add(label3);
        windowCotent.add(result);
        windowCotent.add(go);
//  Задаём фрейм и задаём на него панель
        JFrame frame = new JFrame("My First Calculator");
        frame.setContentPane(windowCotent);

// Задаём размео и делаем фрейм видимым
        frame.setSize(400, 100);
        frame.setVisible(true);
    }
}

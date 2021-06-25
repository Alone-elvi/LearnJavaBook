import javax.swing.*;
import java.awt.*;

public class CalculatorGridBagLayout {
    //  Создаём панель
    JPanel windowContent;

    JTextField displayField;
    JButton button0;
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    JButton button7;
    JButton button8;
    JButton button9;
    JButton buttonPoint;
    JButton buttonEqual;

    JPanel p1;

    //  В конструкторе создаются все компоненты и добавлятся на фрейм с помощью комбинации BorderLayout и GridLayout
    CalculatorGridBagLayout() {

//  Задаём менеджер отображения для этой панели

        windowContent = new JPanel();

//  Задаём схему для этой панели

//        BorderLayout b1 = new BorderLayout();
//        windowContent.setLayout(b1);

//  Создаём и отображаем поле. Добавляем его в "Северную" область экрана

//        displayField = new JTextField(30);
//        windowContent.add("North", displayField);

//  Создаём кнопки, используя конструктор класса JButton, который принимает текст кнопки в качестве параметра

        button0 = new JButton("0");
        button1 = new JButton("1");
        button2 = new JButton("2");
        button3 = new JButton("3");
        button4 = new JButton("4");
        button5 = new JButton("5");
        button6 = new JButton("6");
        button7 = new JButton("7");
        button8 = new JButton("8");
        button9 = new JButton("9");
        buttonPoint = new JButton(".");
        buttonEqual = new JButton("=");

//  Создаём GridBagLayout для панели окна

        GridBagLayout gb = new GridBagLayout();
        windowContent.setLayout(gb);

//  Создаём  экземпляр класса GridBagConstraints
//  Эти строки нужно повторять для каждой компоненты, которая добавляется в ячейку

        GridBagConstraints constr = new GridBagConstraints();

////  Задаём  ограничения  для строки ввода калькулятора координата x в таблице
//
//        constr.x = 0;
//
////  Координата y в таблице
//
//        constr.y = 0;

//  Эта ячейка имеет такую же высоту, что и стандартные ячейки

        constr.gridheight = 1;

//  Эта ячейка  имеет ширину равную 6 стандартных ячеек

        constr.gridwidth = 6;

//  Заполняем всё пространство ячейки

        constr.fill = constr.BOTH;

//  Пропорция по горизонтали, которую будет занимать компонент

        constr.weightx = 1.0;

//  Пропорция по вертикали, которую будет занимать компонент

        constr.weighty = 1.0;

//  Позиция компонента внутри ячейки

        constr.anchor = constr.CENTER;

        displayField = new JTextField();

//  Устанавливаем ограничение для поля ввода

        gb.setConstraints(displayField, constr);

//  Добавляем поля ввода в окно

        windowContent.add(displayField);

//  Создаём панель для кнопок

        p1 = new JPanel();
        GridBagConstraints constr_p1 = new GridBagConstraints();

        //  Эта ячейка имеет такую же высоту, что и стандартные ячейки

        constr_p1.gridheight = 1;

//  Эта ячейка  имеет ширину равную 6 стандартных ячеек

        constr_p1.gridwidth = 6;

//  Заполняем всё пространство ячейки

        constr_p1.fill = constr.BOTH;

//  Пропорция по горизонтали, которую будет занимать компонент

        constr_p1.weightx = 1.0;

//  Пропорция по вертикали, которую будет занимать компонент

        constr_p1.weighty = 1.0;

//  Позиция компонента внутри ячейки

//  Добавляем кнопки на панель

        p1.add(button1);
        p1.add(button2);
        p1.add(button3);
        p1.add(button4);
        p1.add(button5);
        p1.add(button6);
        p1.add(button7);
        p1.add(button8);
        p1.add(button9);
        p1.add(button0);
        p1.add(buttonPoint);
        p1.add(buttonEqual);

//  Помещаем панель p1 в центральную область окна.

        gb.setConstraints(p1, constr_p1);
        windowContent.add(p1);

//  Создаём фрейм и задаём его основную панель

        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(windowContent);

//  Делаем панель достаточной, чтобы вместить все компоненты

        frame.pack();

//  Наконец отображаем окно.

        frame.setVisible(true);
    }


    public static void main(String[] args) {
        CalculatorGridBagLayout calc = new CalculatorGridBagLayout();
    }
}


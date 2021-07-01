import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//import javax.swing.JOptionPane;
import javax.swing.JButton;

public class CalculatorEngine implements ActionListener {

    Calculator parent; // Ссылка на окно калькулятора.

//    +, -, /, или *
    char selectedAction = ' ';
    double currentResult = 0;

// Калькулятор сохраняет ссылку на окно калькулятора в переменной экземпляра класса.

    CalculatorEngine(Calculator parent){
        this.parent = parent;
    }

    public void actionPerformed(ActionEvent e) {
//        Если этот метод можно оставить пустым, ничего не произойдёт, когда JVM вызовет его.

//  Получаем источник события
        JButton clickedButton = (JButton) e.getSource();
        String dispFieldText = parent.displayFormattedField.getText();
        double displayValue = 0;

//  Получить число из дисплея калькулятора, если он не пустой. Восклицательный знак - оператор отрицания.
        Object src = e.getSource();

        if(!"".equals(dispFieldText)){
            if (src == parent.buttonPoint){
                if(dispFieldText.indexOf('.', 1) != -1){
                    dispFieldText = dispFieldText.substring(0, -1);
                }
            }
            else{
                displayValue = Double.parseDouble(dispFieldText);
            }
        }

        if (src == parent.buttonPlus){
            selectedAction = '+';
            currentResult = displayValue;
            parent.displayFormattedField.setText("");
        }else if(src == parent.buttonMinus){
            selectedAction = '-';
            currentResult = displayValue;
            parent.displayFormattedField.setText("");
        }else if(src == parent.buttonDivide){
            selectedAction = '/';
            currentResult = displayValue;
            parent.displayFormattedField.setText("");
        }else if(src == parent.buttonMultiply){
            selectedAction = '*';
            currentResult = displayValue;
            parent.displayFormattedField.setText("");
        }else if(src == parent.buttonEqual){
//  Совершить арифметическое действие, в зависимости от selectedAction, обновить переменную currentResult и
//  показать результат на дисплее.
            if(selectedAction == '+'){
                currentResult+=displayValue;
//  Сконвертировать результат в строку, добавляя его к пустой строке и показать его.
                parent.displayFormattedField.setText(""+currentResult);
            }else if(selectedAction == '-'){
                currentResult-=displayValue;
//  Сконвертировать результат в строку, добавляя его к пустой строке и показать его.
                parent.displayFormattedField.setText(""+currentResult);
            }else if(selectedAction == '/'){
                if (Math.abs(currentResult/=displayValue = 1 / (currentResult/=displayValue)) < Double.POSITIVE_INFINITY) {
                    currentResult/=displayValue;
//  Сконвертировать результат в строку, добавляя его к пустой строке и показать его.
                    parent.displayFormattedField.setText(""+currentResult);
                } else {
                    parent.displayFormattedField.setText("На ноль делить нииизя!!!");
                }
            }else if(selectedAction == '*'){
                currentResult*=displayValue;
//  Сконвертировать результат в строку, добавляя его к пустой строке и показать его.
                parent.displayFormattedField.setText(""+currentResult);
            }

        }else {
//  Для всех цифровых кнопок присоединить надпись на кнопке к надписи на дисплее
            String clickedButtonLabel = clickedButton.getText();
            parent.displayFormattedField.setText(dispFieldText+clickedButtonLabel);
        }



//  Вызываем окно с сообщением. Добавляем надпись на кнопке к тексту окна сообщения.

//        JOptionPane.showConfirmDialog(null,
//                "You pressed: "+  clickedButtonLabel, "Just a test",
//                JOptionPane.PLAIN_MESSAGE);

    }
}

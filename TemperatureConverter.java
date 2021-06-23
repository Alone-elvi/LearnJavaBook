public class TemperatureConverter {

    public static String conventTemp(int temperature, char convertTo){
        if (convertTo == 'F'){
            temperature = temperature * 9/5 + 32;
        }
        System.out.println("Температура равна: " + Integer.toString(temperature) + " " + convertTo);
        return Integer.toString(temperature);
    }

    public static void main(String[] args) {
        TemperatureConverter object = new TemperatureConverter();
        System.out.println(object.conventTemp(20, 'C'));
    }
}

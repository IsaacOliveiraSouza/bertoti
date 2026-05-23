public class Main {
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        calc.setOperacao(new Soma());
        System.out.println("Soma: " + calc.executarOperacao(5, 3)); // 8

        calc.setOperacao(new Subtracao());
        System.out.println("Subtração: " + calc.executarOperacao(5, 3)); // 2
    }
}
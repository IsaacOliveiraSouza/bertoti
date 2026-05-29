public class Main {
    public static void main(String[] args) {
        Calculadora calcSoma = new Calculadora();
        System.out.println("Soma: " + calcSoma.calcular(5, 3)); // 8

        Calculadora calcSub = new CalculadoraSubtracao();
        System.out.println("Subtração: " + calcSub.calcular(5, 3)); // 2

        Calculadora calcMult = new CalculadoraMultiplicacao();
        System.out.println("Multiplicação: " + calcMult.calcular(5, 3)); // 15
    }
}
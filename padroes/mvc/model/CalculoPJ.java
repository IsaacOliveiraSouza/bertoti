package model;

// Strategy: cálculo PJ — desconta apenas 15% de imposto
public class CalculoPJ implements EstrategiaCalculo {
    @Override
    public double calcular(double salarioBase) {
        return salarioBase * (1 - 0.15);
    }

    @Override
    public String descricao() {
        return "PJ (desc. imposto 15%)";
    }
}

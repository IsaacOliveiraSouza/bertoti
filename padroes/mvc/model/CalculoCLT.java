package model;

// Strategy: cálculo CLT — desconta 27,5% de IR e 11% INSS
public class CalculoCLT implements EstrategiaCalculo {
    @Override
    public double calcular(double salarioBase) {
        return salarioBase * (1 - 0.275 - 0.11);
    }

    @Override
    public String descricao() {
        return "CLT (desc. IR 27,5% + INSS 11%)";
    }
}

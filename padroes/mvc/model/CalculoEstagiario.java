package model;

// Strategy: cálculo Estagiário — sem descontos, valor bruto
public class CalculoEstagiario implements EstrategiaCalculo {
    @Override
    public double calcular(double salarioBase) {
        return salarioBase;
    }

    @Override
    public String descricao() {
        return "Estagiário (sem descontos)";
    }
}

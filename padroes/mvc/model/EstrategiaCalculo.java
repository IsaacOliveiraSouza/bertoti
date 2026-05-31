package model;

// Strategy: interface para algoritmo de cálculo de salário
public interface EstrategiaCalculo {
    double calcular(double salarioBase);
    String descricao();
}

package model;

// Composite: interface comum para Funcionario (leaf) e Departamento (composite)
public interface Componente {
    String getNome();
    double calcularSalario();
    void exibir(String indent);
}

public class Calculadora {
    private Operacao operacao;

    public void setOperacao(Operacao op) {
        this.operacao = op;
    }

    public int executarOperacao(int a, int b) {
        return operacao.calcular(a, b);
    }
}
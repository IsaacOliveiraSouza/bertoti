// PADRÃO: novos inscritos podem ser adicionados sem modificar o Canal
public class Main {
    public static void main(String[] args) {
        Canal canal = new Canal();

        canal.inscrever(new Usuario("Alice"));
        canal.inscrever(new Usuario("Bob"));
        canal.inscrever(new Usuario("Carlos")); // basta chamar inscrever()!

        canal.postarVideo("Padrão Observer em Java");
    }
}

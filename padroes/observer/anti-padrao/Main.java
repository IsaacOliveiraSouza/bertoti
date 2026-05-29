// ANTI-PADRÃO: para adicionar um novo inscrito, é preciso modificar a classe Canal
public class Main {
    public static void main(String[] args) {
        Usuario alice = new Usuario("Alice");
        Usuario bob = new Usuario("Bob");

        Canal canal = new Canal(alice, bob);
        canal.postarVideo("Padrão Observer em Java");

        // Problema: e se quisermos adicionar um terceiro usuário?
        // Teríamos que alterar o construtor e o método postarVideo() do Canal!
    }
}

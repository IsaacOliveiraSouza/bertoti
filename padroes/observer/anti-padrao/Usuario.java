public class Usuario {
    private String nome;

    public Usuario(String nome) {
        this.nome = nome;
    }

    public void notificar(String video) {
        System.out.println(nome + " foi notificado: " + video);
    }
}

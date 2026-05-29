// ANTI-PADRÃO: Canal conhece diretamente cada usuário
// Problema: para adicionar um novo tipo de notificado, é preciso modificar o Canal
public class Canal {

    private Usuario usuario1;
    private Usuario usuario2;

    public Canal(Usuario u1, Usuario u2) {
        this.usuario1 = u1;
        this.usuario2 = u2;
    }

    public void postarVideo(String titulo) {
        System.out.println("Novo vídeo: " + titulo);
        usuario1.notificar(titulo);
        usuario2.notificar(titulo);
    }
}

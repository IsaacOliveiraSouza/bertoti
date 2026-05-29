import java.util.ArrayList;
import java.util.List;

// PADRÃO: Canal não conhece os tipos concretos dos inscritos
public class Canal {
    private List<Inscrito> inscritos = new ArrayList<>();

    public void inscrever(Inscrito i) {
        inscritos.add(i);
    }

    public void desinscrever(Inscrito i) {
        inscritos.remove(i);
    }

    public void postarVideo(String titulo) {
        System.out.println("Novo vídeo: " + titulo);
        for (Inscrito i : inscritos) {
            i.notificar(titulo);
        }
    }
}

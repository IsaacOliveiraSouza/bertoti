# Anti-Padrão — Observer

> **Problema:** `Canal` conhece diretamente cada `Usuario` concreto como atributo. Para adicionar um novo inscrito é necessário **modificar** o construtor e o método `postarVideo()` de `Canal`.

```mermaid
classDiagram
    class Canal {
        -Usuario usuario1
        -Usuario usuario2
        +Canal(Usuario u1, Usuario u2)
        +postarVideo(String titulo) void
    }

    class Usuario {
        -String nome
        +Usuario(String nome)
        +notificar(String video) void
    }

    class Main {
        +main(String[] args) void
    }

    Canal --> Usuario : usuario1
    Canal --> Usuario : usuario2
    Main ..> Canal
    Main ..> Usuario
```

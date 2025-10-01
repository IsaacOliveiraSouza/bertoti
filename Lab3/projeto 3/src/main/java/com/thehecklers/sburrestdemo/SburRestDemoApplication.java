package com.thehecklers.sburrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class SburRestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SburRestDemoApplication.class, args);
	}

}
@CrossOrigin(origins = {"http://localhost:8080","http://127.0.0.1:5500"})
@RestController
@RequestMapping("/animals")


class RestApiDemoController {
    private List<Animal> animals = new ArrayList<>();

    public RestApiDemoController() {
        animals.addAll(List.of(
                new Animal("Leão"),
                new Animal("Tigre"),
                new Animal("Elefante"),
                new Animal("Girafa")
        ));
    }

    @GetMapping
    Iterable<Animal> getAnimals() {
        return animals;
    }

    @GetMapping("/{id}")
    Optional<Animal> getAnimalById(@PathVariable String id) {
        for (Animal a: animals) {
            if (a.getId().equals(id)) {
                return Optional.of(a);
            }
        }

        return Optional.empty();
    }

    @PostMapping
    Animal postAnimal(@RequestBody Animal animal) {
        animals.add(animal);
        return animal;
    }

    @PutMapping("/{id}")
    ResponseEntity<Animal> putAnimal(@PathVariable String id,
                                     @RequestBody Animal animal) {
        int animalIndex = -1;

        for (Animal a: animals) {
            if (a.getId().equals(id)) {
                animalIndex = animals.indexOf(a);
                animals.set(animalIndex, animal);
            }
        }

        return (animalIndex == -1) ?
                new ResponseEntity<>(postAnimal(animal), HttpStatus.CREATED) :
                new ResponseEntity<>(animal, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteAnimal(@PathVariable String id) {
        animals.removeIf(a -> a.getId().equals(id));
    }
}

class Animal {
    private final String id;
    private String name;

    public Animal(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Animal(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
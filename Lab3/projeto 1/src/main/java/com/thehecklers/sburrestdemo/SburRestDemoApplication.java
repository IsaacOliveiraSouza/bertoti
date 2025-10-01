package com.thehecklers.sburrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

@RestController
@RequestMapping("/animals")
class AnimalController {
    private List<Animal> animals = new ArrayList<>();

//    public AnimalController() {
//        animals.addAll(List.of(
//                new Animal("Dog"),
//                new Animal("Cat"),
//                new Animal("Lion"),
//                new Animal("Elephant")
//        ));
//    }

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
// {
//   "id": "123e4567-e89b-12d3-a456-426614174000",
//   "name": "Girafa"
// }
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

    @JsonCreator
    public Animal(@JsonProperty("id") String id,
                  @JsonProperty(value = "name", required = true) String name) {
        this.id = (id == null || id.isBlank()) ? UUID.randomUUID().toString() : id;
        this.name = name;
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
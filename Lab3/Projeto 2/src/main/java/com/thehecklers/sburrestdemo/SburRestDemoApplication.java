package com.thehecklers.sburrestdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
    private final AnimalRepository repository;

    AnimalController(AnimalRepository repository) {
        this.repository = repository;
    }

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
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Animal> getAnimalById(@PathVariable String id) {
        return repository.findById(id);
    }
// {
//   "id": "123e4567-e89b-12d3-a456-426614174000",
//   "name": "Girafa"
// }
    @PostMapping
    Animal postAnimal(@RequestBody Animal animal) {
        return repository.save(animal);
    }

    @PutMapping("/{id}")
    ResponseEntity<Animal> putAnimal(@PathVariable String id,
                                     @RequestBody Animal animal) {
        int updated = repository.update(id, animal);
        return (updated == 0) ?
                new ResponseEntity<>(postAnimal(new Animal(id, animal.getName())), HttpStatus.CREATED) :
                new ResponseEntity<>(new Animal(id, animal.getName()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteAnimal(@PathVariable String id) {
        repository.deleteById(id);
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
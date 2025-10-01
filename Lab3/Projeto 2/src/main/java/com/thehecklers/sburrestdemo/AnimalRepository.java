package com.thehecklers.sburrestdemo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class AnimalRepository {
    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Animal> animalRowMapper = (rs, rowNum) ->
            new Animal(rs.getString("id"), rs.getString("name"));

    AnimalRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    List<Animal> findAll() {
        return jdbcTemplate.query("SELECT id, name FROM animals", animalRowMapper);
    }

    Optional<Animal> findById(String id) {
        List<Animal> results = jdbcTemplate.query(
                "SELECT id, name FROM animals WHERE id = ?",
                animalRowMapper,
                id
        );
        return results.stream().findFirst();
    }

    Animal save(Animal animal) {
        jdbcTemplate.update(
                "INSERT INTO animals (id, name) VALUES (?, ?)",
                animal.getId(),
                animal.getName()
        );
        return animal;
    }

    int update(String id, Animal animal) {
        return jdbcTemplate.update(
                "UPDATE animals SET name = ? WHERE id = ?",
                animal.getName(),
                id
        );
    }

    void deleteById(String id) {
        jdbcTemplate.update("DELETE FROM animals WHERE id = ?", id);
    }
}



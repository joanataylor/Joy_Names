package com.joana.bundler.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.joana.bundler.models.Joy;

@Repository
public interface JoyRepository extends CrudRepository<Joy, Long>{
//The repository speaks with the Model
//to perform database operations on the entities and
//relationships defined in the Model layer.

  List<Joy> findAll();
}
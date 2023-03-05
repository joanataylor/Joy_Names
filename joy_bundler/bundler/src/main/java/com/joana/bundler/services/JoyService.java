package com.joana.bundler.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joana.bundler.models.Joy;
import com.joana.bundler.repositories.JoyRepository;

@Service
public class JoyService {

//The service speaks with the Repository,
//here we are calling an instance of the Repository
  @Autowired
  JoyRepository joyRepository;

//method to create a joy, takes in a joy in ()
  public Joy createName(Joy name) {
    return joyRepository.save(name);
  }
//methode to list all the names
  public List<Joy> allNames() {
    List<Joy> allNames = joyRepository.findAll();
    return allNames;
  }

  // retrieves a name
  public Joy findName(Long id) {
    Optional<Joy> optionalName = joyRepository.findById(id);
    if (optionalName.isPresent()) {
      return optionalName.get();
    } else {
      return null;
    }
  }

  // deletes a name
  public void deleteName(Joy name) {
    joyRepository.delete(name);
  }

  // edit/updates a name
  public void updateName(Joy name) {
    joyRepository.save(name);
  }

}
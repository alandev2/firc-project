package com.alan.crud.controlllers;

import com.alan.crud.entities.Training;
import com.alan.crud.repositories.TrainingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping(path = "/trainings")
public class TrainingController {
  @Autowired
  TrainingRepository trainingRepository;

  @GetMapping
  public Iterable<Training> list() {
    return trainingRepository.findAll();
  }

  @PostMapping
  public Training save(@RequestBody Training training) {
    trainingRepository.save(training);
    return training;
  }

  @GetMapping(path="{id}")
  public Object get(@PathVariable Long id) {
    var training = trainingRepository.findById(id);

    JSONObject response = new JSONObject();

    if(training.isEmpty()) {
      response.put("status", "error");
      response.put("message", "id not found");
      return response;
    }

    response.put("status", "success");
    response.put("data", training);

    return response;
  }

  // FALTA FAZER
  // ATUALIZAR AQUI
  // FALTA FAZER

  @DeleteMapping(path="{id}")
  public Object delete(@PathVariable Long id) {
    var training = trainingRepository.findById(id);

    JSONObject response = new JSONObject();
    if(training.isEmpty()) {
      response.put("status", "error");
      response.put("message", "id not found");
      return response;
    }

    trainingRepository.deleteById(id);

    response.put("status", "success");
    response.put("message", "training removed with success");
    return response;
  }
}

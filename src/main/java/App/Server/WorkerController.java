package App.Server;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.NumberOfDocuments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RestController
@RequestMapping("/worker")
@CrossOrigin

public class WorkerController {
  @Autowired
  private WorkerRepo workerRepository;
  @Autowired
  private MongoTemplate mongoTemplate;

  @GetMapping("/")
  public List<Worker> GetAll(){
    return workerRepository.findAll();
  }

  @PostMapping("/add")
  public Worker AddWorker(@RequestBody Worker worker){
    return workerRepository.save(worker);
  }

  @DeleteMapping("/{id}")
  public void RemoveWorker(@PathVariable String id){
    workerRepository.deleteById(id);
  }

  @PostMapping("/rate")
  public Worker UpdateRating(@RequestBody Worker feedback){
    String id = feedback.getId();
    double rating = feedback.getRating();
    Worker oldDetails = workerRepository.findById(id).orElse(null);
    int numOfRatings=oldDetails.getNumOfRatings();
    rating=(oldDetails.getRating()*numOfRatings+rating)/(numOfRatings+1);
    oldDetails.setRating(rating);
    oldDetails.setNumOfRatings(numOfRatings+1);
    workerRepository.deleteById(id);
    return workerRepository.save(oldDetails);
  }

}
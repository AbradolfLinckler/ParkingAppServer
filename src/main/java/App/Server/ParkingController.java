package App.Server;

import java.io.Console;
import java.util.List;
import java.util.Optional;

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

import App.Server.parking_slot.ParkingSlot;
import App.Server.parking_space.ParkingSpace;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RestController
@RequestMapping("/parking")
@CrossOrigin

public class ParkingController {
  @Autowired
  private ParkingRepo parkingRepository;
  @Autowired
  private MongoTemplate mongoTemplate;

  @PostMapping("/addspace")
  public boolean AddSpace(@RequestBody ParkingSpace parkingSpace){
    Optional<Parking> op=parkingRepository.findById("parking00");
    Parking p=op.get();
    // private ParkingSpace ps=new ParkingSpace();
    return true;
  }
  
}

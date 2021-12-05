package App.Server;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/space")
@CrossOrigin

public class SpaceController {
  @Autowired
  private SpaceRepo spaceRepository;
  @Autowired
  private MongoTemplate mongoTemplate;

  @GetMapping("/")
  public List<Space> GetSpaces(){
    return spaceRepository.findAll();
  }

  @GetMapping("/{spaceQuery}")
  public List<Space> AvailableSlots(@PathVariable String spaceQuery){
    List<Space> allSpaces=spaceRepository.findAll();
    List<Space> availableSpaces=new ArrayList<Space>();
    for(int i=0;i<allSpaces.size();i++){
      Boolean f=true;
      Space slot=allSpaces.get(i); //gets a slot
      ArrayList<String> slotBookings=slot.getBookings(); //gets bookings arraylist of the slot
      for(int j=0;j<slotBookings.size();j++){ //iterate through the bookings of the slot
        if(spaceQuery.equals(slotBookings.get(j))) f=false; // checks if a particular booming exists or not
      }
      if(f){
        availableSpaces.add(slot);
      }
    }
    return availableSpaces;
  }

  @PostMapping("/add")
  public Space PostSpace(@RequestBody Space space){
    return spaceRepository.save(space);
  }

  @PostMapping("/update")
  public Space UpdateSpace(@RequestBody Space newBooking) {
    Space oldBooking = spaceRepository.findById(newBooking.getId()).orElse(null);
    ArrayList<String> newbookings=newBooking.getBookings();
    ArrayList<String> oldbookings=oldBooking.getBookings();
    int nnew=newbookings.size(), nold=oldbookings.size();
    ArrayList<String> totalbookings=new ArrayList<String>();
    for(int i=0;i<nold;i++) totalbookings.add(oldbookings.get(i));
    for(int i=0;i<nnew;i++) totalbookings.add(newbookings.get(i));
    oldBooking.setBookings(totalbookings);
    spaceRepository.deleteById(newBooking.getId());
    return spaceRepository.save(oldBooking);
  }

  @DeleteMapping("/{id}")
  public void RemoveSpace(@PathVariable String id){
    spaceRepository.deleteById(id);
  }

}
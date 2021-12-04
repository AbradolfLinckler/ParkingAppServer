package App.Server;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import App.Server.parking_space.*;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@Data
@AllArgsConstructor

public class Parking {
  @Id
  private String id="parking00";
  private int numOfSpaces;
  private ParkingSpace parkingSpaces[];

  Parking(){
    this.numOfSpaces=0;
    this.parkingSpaces=new ParkingSpace[1000];
  }

  public int NSapces(){
    return this.numOfSpaces;
  }

}

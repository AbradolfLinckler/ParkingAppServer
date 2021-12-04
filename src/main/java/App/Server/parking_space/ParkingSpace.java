package App.Server.parking_space;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import App.Server.parking_slot.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpace {
  @Id
  private String id;
  private ParkingSlot parkingSlots[];
  private int spaceNumber;

  ParkingSpace(int spaceNumber){
    this.spaceNumber=spaceNumber;
    this.parkingSlots=new ParkingSlot[168];

  }

}

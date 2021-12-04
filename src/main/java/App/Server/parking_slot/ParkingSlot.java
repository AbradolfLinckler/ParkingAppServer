package App.Server.parking_slot;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Document
@Data
@AllArgsConstructor

public class ParkingSlot {
  @Id
  private String id;
  private String startTime;
  private String endTime;
  private String date;
  private Boolean isBooked;
  private String workerName;
  private double rating;
  private int numberOfRatings;

}

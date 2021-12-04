package App.Server;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ParkingRepo extends MongoRepository<Parking,String>{}

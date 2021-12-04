package App.Server;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpaceRepo extends MongoRepository<Space,String>{}


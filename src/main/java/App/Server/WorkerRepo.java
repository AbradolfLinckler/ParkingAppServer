package App.Server;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface WorkerRepo extends MongoRepository<Worker ,String>{}


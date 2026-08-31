package net.codesrhereaman.jounalapp.repository;

import net.codesrhereaman.jounalapp.journalentry.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);

    User deleteByUserName(String userName);

    
}

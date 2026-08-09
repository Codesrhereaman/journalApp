package net.codesrhereaman.jounalApp.Repository;

import net.codesrhereaman.jounalApp.JournalEntry.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {

    User findByUserName(String userName);

    User deleteByUserName(String userName);

    
}

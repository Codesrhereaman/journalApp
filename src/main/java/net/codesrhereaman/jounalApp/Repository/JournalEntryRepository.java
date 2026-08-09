package net.codesrhereaman.jounalApp.Repository;

import net.codesrhereaman.jounalApp.JournalEntry.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}

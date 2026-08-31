package net.codesrhereaman.jounalapp.repository;

import net.codesrhereaman.jounalapp.journalentry.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {

}

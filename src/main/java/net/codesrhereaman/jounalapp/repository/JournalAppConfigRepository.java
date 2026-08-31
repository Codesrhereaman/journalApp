package net.codesrhereaman.jounalapp.repository;

import net.codesrhereaman.jounalapp.journalentry.JournalAppConfigEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalAppConfigRepository extends MongoRepository<JournalAppConfigEntity, ObjectId> {
}

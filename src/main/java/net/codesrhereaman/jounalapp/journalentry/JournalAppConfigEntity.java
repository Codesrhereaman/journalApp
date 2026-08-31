package net.codesrhereaman.jounalapp.journalentry;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@RequiredArgsConstructor
@Document(collection = "journal_app_config")
public class JournalAppConfigEntity {

    private String key;

    private String value;

}

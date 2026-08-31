package net.codesrhereaman.jounalapp.journalentry;

import lombok.*;
import net.codesrhereaman.jounalapp.enums.Sentiments;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDateTime;

//using lombok annotation are used to create getter and setter during compile time
//@Getter
//@Setter
//@EqualsAndHashCode
//@NoArgsConstructor
//@AllArgsConstructor
@Data
@Document(collection = "Journal_Entries")
public class JournalEntry {

    @Id
    private ObjectId id;

    private String title;

    private String content;

    private LocalDateTime date;

    private Sentiments sentiment;

}

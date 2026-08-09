package net.codesrhereaman.jounalApp.JournalEntry;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//using lombok annotation are used to create getter and setter during compile time
//@Getter
//@Setter
//@EqualsAndHashCode



@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Builder
public class User {

    @Id
    private ObjectId userid;
    @Indexed(unique = true)  //it will not directly be indexed when a new user created hence we have to explicitly tell it in app properties
    @NonNull
    private String userName;
    @NonNull
    private String password;
    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();
    private List<String> userRoles;

}

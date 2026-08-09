package net.codesrhereaman.jounalApp.JournalEntry;

import lombok.*;

@Data
@NoArgsConstructor
public class AdminCreateRequest {
    private String userName;
    private String password;
}
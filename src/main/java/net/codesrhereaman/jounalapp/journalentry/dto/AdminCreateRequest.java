package net.codesrhereaman.jounalapp.journalentry.dto;

import lombok.*;

@Data
@NoArgsConstructor
public class AdminCreateRequest {
    private String userName;
    private String password;
}
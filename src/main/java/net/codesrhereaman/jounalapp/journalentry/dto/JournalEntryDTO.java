package net.codesrhereaman.jounalapp.journalentry.dto;


import lombok.Data;
import net.codesrhereaman.jounalapp.enums.Sentiments;

@Data
public class JournalEntryDTO {

    private String title;
    private String content;
    private Sentiments sentiment;
}

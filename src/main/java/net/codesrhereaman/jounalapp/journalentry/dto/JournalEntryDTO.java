package net.codesrhereaman.jounalapp.journalentry.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import net.codesrhereaman.jounalapp.enums.Sentiments;

@Data
public class JournalEntryDTO {

    @NotNull
    @Schema(description = "title of the journal")
    private String title;
    private String content;
    private Sentiments sentiment;
}

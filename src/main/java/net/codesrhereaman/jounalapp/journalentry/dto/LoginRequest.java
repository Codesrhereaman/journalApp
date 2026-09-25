package net.codesrhereaman.jounalapp.journalentry.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotNull
    @Schema(description = "the user username")
    String userName,
    @NotNull
    String password
){}

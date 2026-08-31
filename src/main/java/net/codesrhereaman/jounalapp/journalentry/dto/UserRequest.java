package net.codesrhereaman.jounalapp.journalentry.dto;

import net.codesrhereaman.jounalapp.enums.Sentiments;

public record UserRequest(
        String userName,
        String password,
        String email,
        Boolean sentimentalAnalysis
) {}

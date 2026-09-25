package net.codesrhereaman.jounalapp.journalentry.dto;


public record RegisterRequest(
        String userName,
        String password,
        String email,
        Boolean sentimentalAnalysis
) {}

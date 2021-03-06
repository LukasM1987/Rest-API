package com.crud.tasks.domain;

import com.crud.tasks.config.AdminConfig;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

@Builder
@Getter
public class Mail {
    private final String mailTo;
    private final String subject;
    private final String message;
    private final String toCc;

    public Mail(String mailTo, String subject, String message, String toCc) {
        this.mailTo = mailTo;
        this.subject = subject;
        this.message = message;
        this.toCc = toCc;
    }

    public Optional<String> getToCc() {
        return Optional.ofNullable(toCc);
    }
}

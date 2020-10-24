package com.projects.redditClone.Exception;

import org.springframework.mail.MailException;

public class SpringRedditException extends RuntimeException {
    public SpringRedditException(String s, MailException e) {
        super(s);
    }
}

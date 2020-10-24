package com.projects.redditClone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticationEmail {

    private String subject;
    private String recipient;
    private String body;
}

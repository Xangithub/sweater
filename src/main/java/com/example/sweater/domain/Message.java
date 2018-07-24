package com.example.sweater.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "message")
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String tag;

    public Message(String text, String tag) {
        this.text = text;
        this.tag = tag;
    }

    public Message() {
    }
}

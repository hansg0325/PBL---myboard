package com.example.myboard.model.request;

import lombok.Data;

@Data
public class BoardPostRequest {
    private String title;
    private String body;
}

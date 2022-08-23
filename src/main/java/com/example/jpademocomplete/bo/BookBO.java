package com.example.jpademocomplete.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookBO {

    private String title;

    private String description;

    private Long price;

}

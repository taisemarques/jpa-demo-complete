package com.example.jpademocomplete.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewBO {

    private String voterName;

    private int numStars;

    private String comment;

}

package com.example.jpademocomplete.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceOfferBO {

    private Long newPrice;

    private String promotionalText;

}

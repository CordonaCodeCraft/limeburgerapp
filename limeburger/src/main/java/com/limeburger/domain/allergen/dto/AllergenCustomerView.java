package com.limeburger.domain.allergen.dto;

import lombok.Data;

@Data
public class AllergenCustomerView {

    private String name;
    private String description;
    private String dangerIndex;
    private String imageUrl;


}

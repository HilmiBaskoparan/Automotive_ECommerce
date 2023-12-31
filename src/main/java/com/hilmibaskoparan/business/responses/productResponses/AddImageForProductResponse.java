package com.hilmibaskoparan.business.responses.productResponses;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddImageForProductResponse {
	
    private	String name;
	private String description;
	private double price;
	private String categoryName;
	private int quantity;
	List<String> url;
}

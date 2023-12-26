package com.hilmibaskoparan.business.responses.productResponses;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor 
@Data
public class GetAllProductResponse {

	private int id;
	private String name;
	private String description;
	private double price;
	private String categoryName;
	private String brandName;
	private int quantity;
	List<String> url;
}

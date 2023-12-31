package com.hilmibaskoparan.business.responses.productResponses;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindTopFiveProductResponse {
	private int id;
	private String name;
	private String description;
	private double price;
	private String categoryName;
	private String brandName;
	private int quantity;
	List<String> url;
}

package com.shopeasy.model;




import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	private Integer productId;
	private String productName;
	private String picture;
	private Integer quantity;
	private Double price;
	private String productDescription;
	private Double discount;
	
	@Enumerated(EnumType.STRING)
	private CategoryType category_type;
	
}

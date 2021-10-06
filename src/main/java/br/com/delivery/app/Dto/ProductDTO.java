package br.com.delivery.app.Dto;

import br.com.delivery.app.Model.Category;
import br.com.delivery.app.Model.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDTO {

	private Long id_product;

	@NotBlank(message="Por favor entre com o nome")
	private String name;

	@NotNull(message="Por favor entre com o preço")
	private BigDecimal price;

	private BigDecimal promotion_price;

	@NotNull(message="Por favor entre com a descrição")
	private String description;

	private boolean spotlight;

	private String photo;

	private Long id_category;

	private Category category;

	public ProductDTO(Product product) {
		name            = product.getName();
		price           = product.getPrice();
		promotion_price = product.getPromotion_price();
		description     = product.getDescription();
		spotlight       = product.isSpotlight();
		photo           = product.getPhoto();
		category        = product.getCategory() ;
	}

}

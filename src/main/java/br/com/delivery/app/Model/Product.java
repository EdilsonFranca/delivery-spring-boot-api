package br.com.delivery.app.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_product;

	@NotBlank(message="Por favor entre com o nome")
	private String name;

	@NotNull(message="Por favor entre com o preço")
	private BigDecimal price;

	private BigDecimal promotion_price;

	@NotNull(message="Por favor entre com a descrição")
	private String description;
	
	private boolean spotlight;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(
			name="id_category",
			referencedColumnName = "id_category",
			insertable = true,
			updatable = true
	)
    private Category category;
   
	@Column(nullable = true, length = 64)
	private String photo;

}

package br.com.delivery.app.Model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "items")
public class Items implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_item;

	private String name;
	
	private BigDecimal price;
	
	private Integer quantity;
	
	private String observation;

	@OneToMany(
			targetEntity = ItemAdditional.class,
			fetch = FetchType.EAGER,cascade=CascadeType.ALL
	)
    private Set<ItemAdditional> item_additional;
}

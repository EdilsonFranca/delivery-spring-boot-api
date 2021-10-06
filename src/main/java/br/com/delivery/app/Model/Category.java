package br.com.delivery.app.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "categorys")
public class Category implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable=false)
	private Long id_category;

	@NotBlank(message="Por favor entre com o nome")
	private String name;
	
	@JsonIgnore
	@OneToMany(
			targetEntity=Product.class,
			mappedBy="category",cascade=CascadeType.ALL,
			fetch = FetchType.LAZY
	)
    private List<Product> products;

	@OneToMany(
			targetEntity = Additional.class,
			fetch = FetchType.EAGER,
			cascade = CascadeType.ALL
	)
    private List<Additional> additional;

    public void addAdditional(Additional additional) {
         this.additional.add(additional);
	}
}

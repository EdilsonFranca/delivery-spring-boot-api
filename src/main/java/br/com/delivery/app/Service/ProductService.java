package br.com.delivery.app.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.delivery.app.Dto.ProductDTO;
import br.com.delivery.app.Model.Product;
import br.com.delivery.app.Repository.ProductRepository;


@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repository;


	@Transactional(readOnly = true)
	public List<Product> find() {
		return repository.findAllSpotlight();
	}
	
	@Transactional(readOnly = true)
	public List<Product> findAll() {
		return repository.findAll();
	}

	public ProductDTO findById(long id_product) {
	    Product product = repository.findByIdProduct(id_product);
		return new ProductDTO(product);
	}
	
	public ProductDTO update_product(long id_product) {
	    Product product = repository.findByIdProduct(id_product);
		return new ProductDTO(product);
	}


}
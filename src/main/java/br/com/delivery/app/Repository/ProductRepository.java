package br.com.delivery.app.Repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.delivery.app.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  
  @Query("from Product p where p.promotion_price != null")
  Collection<Product> findAllPromotionPrice();

  /*
  @Query("SELECT p.id_product , p.description,p.name , p.price ,p.promotion_price ,a ,c.name FROM Product p left join  p.category c left JOIN c.additional a ")
  Collection<?> findAllSpotlight();
  */
  
  @Query("FROM Product p where p.spotlight = 1")
  List<Product> findAllSpotlight();

  @Query("from Product p where p.id_product = :id_product ")
  Product findByIdProduct(long id_product);

}

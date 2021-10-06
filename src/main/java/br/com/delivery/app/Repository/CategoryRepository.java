package br.com.delivery.app.Repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.delivery.app.Model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	  
	  @Query("FROM Category order by position")
	  List<Category> findAllByCategory();
	  
	  @Query("Select name,id_category FROM Category ")
	  List<?> findNameCategory();

}

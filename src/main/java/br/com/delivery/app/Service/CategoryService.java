package br.com.delivery.app.Service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.delivery.app.Dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.delivery.app.Model.Category;
import br.com.delivery.app.Repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repositoryCategory;
	
	public List<CategoryDTO> findCategory() {
		List<Category> list = repositoryCategory.findAllByCategory();
		return list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
	}
	
	public List findNameCategory() {
		return repositoryCategory.findNameCategory();
	}
}

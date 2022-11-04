package eu.solvemath.solvemath.services;

import eu.solvemath.solvemath.domain.Category;
import eu.solvemath.solvemath.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Set<Category> findAll(){
        Set<Category> categories = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(categories::add);
        return categories;
    }

    public void add(Category category){
        repository.save(category);
    }
    
}

package eu.solvemath.solvemath.bootstrap;

import eu.solvemath.solvemath.domain.Category;
import eu.solvemath.solvemath.domain.Product;
import eu.solvemath.solvemath.repositories.CategoryRepository;
import eu.solvemath.solvemath.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public Bootstrap(CategoryRepository categoryRepository, CategoryService categoryService) {
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        
    }
}

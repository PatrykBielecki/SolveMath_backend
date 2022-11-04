package eu.solvemath.solvemath.services;

import eu.solvemath.solvemath.domain.Product;
import eu.solvemath.solvemath.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Set<Product> findAll(){
        Set<Product> products = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(products::add);
        return products;
    }

    public void add(Product product){
        repository.save(product);
    }
}

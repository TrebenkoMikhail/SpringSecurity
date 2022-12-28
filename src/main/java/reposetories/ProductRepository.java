package reposetories;

import entities.Products;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository {

    void save(Products products);
}

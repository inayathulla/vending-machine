package com.javator.vm.services;

import com.javator.vm.entities.Product;
import java.util.List;

public interface ProductService {
    List<Product> findAll();
}

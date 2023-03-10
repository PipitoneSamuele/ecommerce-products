package it.ecommerce.products_microservice.repository;

import org.springframework.data.repository.CrudRepository;

import it.ecommerce.products_microservice.model.Product;

public interface ProductsRepository extends CrudRepository<Product, Integer> {

}

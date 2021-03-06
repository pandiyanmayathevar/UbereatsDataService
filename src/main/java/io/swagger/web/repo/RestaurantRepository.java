package io.swagger.web.repo;

import io.swagger.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findByPostalCode(String postalCode);
    List<Restaurant> findByCategoryId(int categoryId);
}

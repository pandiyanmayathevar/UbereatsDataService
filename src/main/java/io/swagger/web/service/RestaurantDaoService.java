package io.swagger.web.service;


import io.swagger.web.repo.RestaurantRepository;
import io.swagger.model.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = { "io.swagger", "io.swagger.web" })
public class RestaurantDaoService {

    @Autowired
    private RestaurantRepository  restaurantRepository;


    public List<Restaurant> findByCategoryId(int categoryId) {
        return restaurantRepository.findByCategoryId(categoryId);
    }

    //findByPostalCode
    public List<Restaurant> findByPostalCode(String postalCode) {
        return restaurantRepository.findByPostalCode(postalCode);
    }

    public List<Restaurant> findAll() {
        return restaurantRepository.findAll();
    }

    public Restaurant save(Restaurant restaurant ) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant update(Restaurant restaurant) {
        int Id = restaurant.getId();
        Restaurant restaurantByID = restaurantRepository.findOne(Id);
        restaurantByID.setName(restaurant.getName());
        restaurantByID.setCategoryId(restaurant.getCategoryId());
        restaurantByID.setStreetNameNumber(restaurant.getStreetNameNumber());
        restaurantByID.setPostalCode(restaurant.getPostalCode());
        restaurantByID.setCity(restaurant.getCity());
        restaurantByID.setProvince(restaurant.getProvince());
        return restaurantRepository.save(restaurantByID);
    }

    public Restaurant findOne(int id) {
        return restaurantRepository.findOne(id);
    }

    public Restaurant deleteById(Restaurant restaurant) {
        int Id = restaurant.getId();
        restaurantRepository.delete(Id);
        return null;
    }

}

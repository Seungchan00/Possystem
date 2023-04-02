package PosSys.PosSys.service;

import PosSys.PosSys.Repository.RestaurantRepository;
import PosSys.PosSys.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    @Transactional(readOnly = false)
    public void saveRestaurant(Restaurant restaurant){
        restaurantRepository.save(restaurant);
    }
    public Restaurant findOne(Long restaurantId){
        return restaurantRepository.findOne(restaurantId);
    }
    public List<Restaurant> findRestaurants(){
        return restaurantRepository.findAll();
    }

}

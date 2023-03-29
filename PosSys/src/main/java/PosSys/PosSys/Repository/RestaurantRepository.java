package PosSys.PosSys.Repository;


import PosSys.PosSys.domain.Restaurant;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RestaurantRepository {

    private final EntityManager em;

    public void save(Restaurant restaurant) {
        if (restaurant.getId() == null) {
            em.persist(restaurant);
        } else {
            Restaurant managedRestaurant = em.find(Restaurant.class, restaurant.getId());
            managedRestaurant.change(restaurant.getName(), restaurant.getLocation());
        }
    }


    public Restaurant findOne(Long id){
        return em.find(Restaurant.class, id);
    }
    public List<Restaurant> findAll(){
        return em.createQuery("select r from Restaurant r", Restaurant.class).getResultList();
    }
}
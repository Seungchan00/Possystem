package PosSys.PosSys.controller;

import PosSys.PosSys.domain.Restaurant;
import PosSys.PosSys.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;


    @GetMapping("/restaurant")
    public String showRestaruant(Model model){
        List<Restaurant> restaurantlist = restaurantService.findRestaurants();
        model.addAttribute("restaurantlist",restaurantlist);
        return "restaurants/restaurantView";
    }


    @GetMapping("/restaurantsearchname")
    public String searchRestaurantForm(){
        return "/restaurants/restaurantsearch";
    }

    @PostMapping("/restaurants/search")
    public String searchRestaurant(@RequestParam("name") String name, Model model) {
        List<Restaurant> restaurants = restaurantService.findByname(name);
        if(restaurants == null || restaurants.isEmpty()) {
            model.addAttribute("error", "해당하는 식당을 찾을 수 없습니다.");
        } else {
            model.addAttribute("restaurants", restaurants);
        }
        return "restaurants/search";
    }
/*
    @GetMapping("/restaurants/{restaurant_name}search")
    public String searchrestaurant(@PathVariable("restaurant_name") String restaurantname, Model model){
        List<Restaurant> restaurants = restaurantService.findByname(restaurantname);
        if(restaurants == null){
            model.addAttribute("error", "해당하는 식당을 찾을수없습니다.");
        }else {
            model.addAttribute("restaurants", restaurants);
        }
        return "restaurants/search";
    }
*/


}

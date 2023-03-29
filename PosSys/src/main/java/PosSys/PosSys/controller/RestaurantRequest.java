package PosSys.PosSys.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestaurantRequest {
    private Long restaurant_id;
    private String restaurant_name;
    private String restaurant_location;
    private int table_x;
    private int table_y;

}

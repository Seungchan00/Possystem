package PosSys.PosSys.controller;

import PosSys.PosSys.domain.Restaurant;
import PosSys.PosSys.domain.TableInfo;
import PosSys.PosSys.service.RestaurantService;
import PosSys.PosSys.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TableInfoController {
    private final TableInfoService tableInfoService;
    private final RestaurantService restaurantService;


    @GetMapping("/restaurants/{restaurantid}/botton")
    public String showTableinfo(@PathVariable("restaurantid")Long restaurantsid, Model model){

       Restaurant restaurant = restaurantService.findOne(restaurantsid);                   //restaurantid값을 받아서 찾음 식당객체를 가져옴

        List<TableInfo> tableInfos = tableInfoService.findtableinfobyid(restaurantsid);
        model.addAttribute("tableInfos",tableInfos);

        return "restaurants/restaurantbotton";
    }


}

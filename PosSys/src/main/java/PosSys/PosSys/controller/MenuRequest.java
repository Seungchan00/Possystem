package PosSys.PosSys.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MenuRequest {

    private long table_id;
    //private String menu_name;
    //private int menu_count;
    private List<String> menu;
    private List<Integer> quantity;
    private int table_people;
}

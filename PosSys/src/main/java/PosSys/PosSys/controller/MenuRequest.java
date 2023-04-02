package PosSys.PosSys.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequest {

    private long table_id;
    private String menu_name;
    private int menu_count;
    private int table_people;
}

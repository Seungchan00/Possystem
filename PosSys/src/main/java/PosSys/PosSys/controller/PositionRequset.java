package PosSys.PosSys.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PositionRequset {

    private Long table_id;
    private int table_x;
    private int table_y;
    private long Restaurant_id;


}

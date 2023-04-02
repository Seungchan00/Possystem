package PosSys.PosSys.controller;

import PosSys.PosSys.domain.TableInfo;
import PosSys.PosSys.domain.TableSeat;
import lombok.Getter;
import lombok.Setter;

@Getter
public class UpdateRequest {

    private Long table_id;

    private Boolean tableSeat = true;

}

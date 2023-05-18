package PosSys.PosSys.controller;


import PosSys.PosSys.domain.*;
import PosSys.PosSys.service.MenuService;
import PosSys.PosSys.service.ReservationService;
import PosSys.PosSys.service.RestaurantService;
import PosSys.PosSys.service.TableInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor

public class apiController {
    private final ReservationService reservationService;
    private final RestaurantService restaurantService;
    private final TableInfoService tableInfoService;
    private final MenuService menuService;

    @PostMapping("/first")
    public void CreateRestaurant(@RequestBody RestaurantRequest request){               //처음에 insert 식당 정보
        Restaurant restaurant = new Restaurant();
        restaurant.setId(request.getRestaurant_id());
        restaurant.setName(request.getRestaurant_name());
        restaurant.setLocation(request.getRestaurant_location());

        System.out.println(request.getRestaurant_id()+request.getRestaurant_name()+request.getRestaurant_location());
        restaurantService.saveRestaurant(restaurant);
    }
    @PostMapping("/position")
    public void Createtable(@RequestBody PositionRequset request){                  // 처음 자리정보들 업데이트
        TableInfo tableInfo = new TableInfo();

        tableInfo.setTable_x(request.getTable_x());
        tableInfo.setTable_y(request.getTable_y());
        tableInfo.setId(request.getTable_id());
        tableInfo.setTable_seat(TableSeat.NOSEATED);
        tableInfo.setRemaintime(0);
        Long restaurantId = request.getRestaurant_id();
        Restaurant restaurant = restaurantService.findOne(restaurantId);
        tableInfo.setRestaurant(restaurant);

        tableInfoService.saveTable(tableInfo);

    }
    @PostMapping("/check") //예약 확인
    public void reservationCheck(@RequestBody ReservationRequest request) {
        Long restaurant_id = request.getRestaurant_id();
        Long table_number = request.getTable_number();
        System.out.println(restaurant_id +  table_number);
        List<Reservation> reservations = reservationService.getReservationsByRestaurantIdTableId(restaurant_id, table_number);

        for (Reservation reservation : reservations) {
            reservation.setReservationstatus(Reservationstatus.RESERVED);
            reservationService.saveReservation(reservation);
        }


    }


    @PostMapping("/menu")// menu 기능
    public ResponseEntity<?> addMenu(@RequestBody MenuRequest request){              //메뉴추가     Table_id, Menu_name ,Menu_count

        List<String> menuList = request.getMenu();
        List<Integer> quantityList = request.getQuantity();
        Long tableInfoId= request.getTable_id();
        TableInfo tableinfo =  tableInfoService.findOne(tableInfoId);

        if (tableinfo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid table info ID. Please provide a valid ID.");
        }

        for (int i = 0; i < menuList.size(); i++) {
            Menu menu = new Menu();
            menu.setMenu_name(menuList.get(i));
            menu.setMenu_count(quantityList.get(i));
            menu.setTableInfo(tableinfo);
            menuService.saveMenu(menu);
        }
        return ResponseEntity.ok("Menu items added successfully.");
    }
    @PostMapping("/person")
    public void addPerson(@RequestBody MenuRequest request){

        Long tableinfoid = request.getTable_id();
        TableInfo tableinfo = tableInfoService.findOne(tableinfoid);
        LocalDateTime currentTime = LocalDateTime.now();
        tableinfo.setTable_starttime(currentTime);
        tableinfo.setTable_people(request.getTable_people());
        tableinfo.setTable_seat(TableSeat.SEATED);
        tableinfo.setRemaintime(request.getRemaintime());
        tableInfoService.saveTable(tableinfo);

        tableInfoService.updateRemainingTimes();
     //   int remainingTime = request.getRemaintime();
     //   tableInfoService.updateRemainingTime(tableinfoid, remainingTime);
    }
    @PostMapping("/update_table_status")
    public void UpdateSeat(@RequestBody UpdateRequest request){         // table_id , Tableseat  테이블 번호, 테이블 정보 --> 결제했을때 테이블 정보 reset
        UpdateRequest updateRequest = new UpdateRequest();

        Long tableInfoId = request.getTable_id();
        TableInfo tableinfo =  tableInfoService.findOne(tableInfoId);           // 나중에 DB에 저장하려면 table id 로 조회한뒤에 entity만들어진거에 인원수,메뉴정보,시작시간,끝시간,저장

        //if(request.getTableSeat() == false) {
        tableinfo.setTable_seat(TableSeat.NOSEATED);
        //}
        tableinfo.setTable_starttime(null);
        tableinfo.setTable_people(0);
        tableinfo.setRemaintime(0);
        tableInfoService.saveTable(tableinfo);

    }

    @GetMapping("/tables")
    public String showTables(Model model) {
        List<TableInfo> tableInfos = tableInfoService.findtableinfos();
        List<Long> tableIds = tableInfos.stream().map(TableInfo::getId).collect(Collectors.toList());

        model.addAttribute("tableInfos", tableInfos);
        model.addAttribute("tableIds", tableIds);

        return "your_template_name_here";
    }

    @GetMapping("timeset")
    public List<TableInfo> getTableInfos() {
        return tableInfoService.findtableinfos();
    }


    @GetMapping("timeset/{tableId}/{remainingTime}")
    public void updateRemainingTime(@PathVariable Long tableId, @PathVariable int remainingTime) {
        tableInfoService.updateRemainingTimes();
    }


    @GetMapping("/get_remaining_time/{tableId}")
    public int getRemainingTime(@PathVariable("tableId") Long tableId) {
        return tableInfoService.findOne(tableId).getRemaintime();
    }

}

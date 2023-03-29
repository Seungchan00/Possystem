package PosSys.PosSys.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class apiController {


    @PostMapping("/position")
    public String addRestaurant(@RequestBody RestaurantRequest request, Model model) {
        model.addAttribute("restaurant_id", request.getRestaurant_id());
        model.addAttribute("restaurant_name", request.getRestaurant_name());
        model.addAttribute("restaurant_location", request.getRestaurant_location());
        model.addAttribute("table_x", request.getTable_x());
        model.addAttribute("table_y", request.getTable_y());
        return "restaurant";
    }

    @PostMapping("/menu")
    public String addMenu(@RequestBody MenuRequest request, Model model) {
        model.addAttribute("table_id", request.getTable_id());
        model.addAttribute("menu", request.getMenu());
        model.addAttribute("menu_num", request.getMenu_num());

        return "restaurant";
    }
}
  /*
}

    @app.route('/update', methods=['GET'])
    def get_button_values():
    global table_seat1, table_seat2, table_seat3, table_seat4, table_seat5, table_seat6, table_seat7, table_seat8
    return jsonify({
        'seat1': table_seat1,
                'seat2': table_seat2,
                'seat3': table_seat3,
                'seat4': table_seat4,
                'seat5': table_seat5,
                'seat6': table_seat6,
                'seat7': table_seat7,
                'seat8': table_seat8
    })

    @app.route('/send', methods=['POST'])
    def update_button_values():
    global table_seat1, table_seat2, table_seat3, table_seat4, table_seat5, table_seat6, table_seat7, table_seat8
    data = request.json
            table_seat1 = data['table_seat1']
    table_seat2 = data['table_seat2']
    table_seat3 = data['table_seat3']
    table_seat4 = data['table_seat4']
    table_seat5 = data['table_seat5']
    table_seat6 = data['table_seat6']
    table_seat7 = data['table_seat7']
    table_seat8 = data['table_seat8']


            return jsonify({'message': 'Data received successfully'})

    @app.route('/api/timeData', methods=['POST'])
    def update_time():
    data = request.get_json()
    table_start_time = data['table_start_time']
    table_end_time = data['table_end_time']

            # 로그에 시작시간과 종료시간 출력
    print(f"Start Time: {table_start_time}")
    print(f"End Time: {table_end_time}")

    # TODO: 시작시간과 종료시간을 이용하여 원하는 작업 수행

    return 'Time updated successfully'

            # 초기화
            table_status = {
            "Table1": False,
            "Table2": False,
            "Table3": False,
            "Table4": False,
            "Table5": False,
            "Table6": False,
            "Table7": False,
            "Table8": False
}

    @app.route('/update_table_status', methods=['POST'])
    def update_table_status():
        data = request.json
        table_number = data['table_number']
        table_seat = data['table_seat']

        # table_name에 해당하는 테이블 상태 업데이트
        table_status[table_number] = table_seat

        # 결과값 출력
        print(f"테이블 번호 : {table_number} 자리여부: {table_seat}")

        return jsonify({'message': 'Table status updated successfully'})

        if __name__ == '__main__':
        app.run(host='172.31.1.142', port=3105)
}
*/
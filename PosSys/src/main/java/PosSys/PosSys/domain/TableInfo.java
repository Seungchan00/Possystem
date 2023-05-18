
package PosSys.PosSys.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class TableInfo {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_id", insertable = true, updatable = true)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private int table_x;

    private int table_y;

    @Enumerated(EnumType.STRING)
    private TableSeat table_seat;

    @OneToMany(mappedBy = "tableInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Menu> menuList = new ArrayList<>();

    private int table_people;

    private LocalDateTime table_starttime;

    private LocalDateTime table_endtime;

    @Column(name = "remaintime")
    private int remaintime;






}
//==생성 메서드==//
    /*
    public static TableInfo createTableInfo(long id ,int table_x, int table_y) {
    TableInfo tableInfo = new TableInfo();
    tableInfo.setId(id);
    tableInfo.setTable_x(table_x);
    tableInfo.setTable_y(table_y);
    tableInfo.setTable_seat(TableSeat.NOSEATED);

        return tableInfo;
    }

}

public class TableInfo{


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_number")
    private Long tableInfo_number;    // 테이블 좌석 번호

    @Column(name = "table_x")
    private int tableInfo_X;       //table X좌표
    @Column(name = "talbe_y")
    private int tableInfo_Y;       //table Y좌표
    @Column(name = "table_seat")
    private int tableInfo_seat;  //좌석수
    @Column(name = "table_start_time")
    private LocalDateTime tableInfo_startTime;
    @Column(name = "table_end_time")
    private LocalDateTime talbeInfo_endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private Table table;
    // ManyToOne: TableInfo 엔티티와 Table 엔티티 간의 다대일(N:1) 관계를 설정하는 어노테이션
    // JoinColumn: 외래 키를 매핑할 때 사용하는 어노테이


}
*/
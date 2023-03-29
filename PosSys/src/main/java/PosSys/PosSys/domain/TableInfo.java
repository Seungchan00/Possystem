
package PosSys.PosSys.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Entity
@Getter @Setter
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

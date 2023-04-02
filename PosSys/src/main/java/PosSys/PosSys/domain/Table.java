/*
package PosSys.PosSys.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter

@jakarta.persistence.Table(name="tabling")
public class Table {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")       // 테이블 id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    // ManyToOne: Table 엔티티와 Restaurant 엔티티 간의 다대일(N:1) 관계를 설정하는 어노테이션
    // JoinColumn: 외래 키를 매핑할 때 사용하는 어노테이션


    @OneToMany(mappedBy = "table",fetch = FetchType.LAZY)
    private List<TableInfo> tableInfoList = new ArrayList<>();


}
*/
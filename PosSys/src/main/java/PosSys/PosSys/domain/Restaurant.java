
package PosSys.PosSys.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@jakarta.persistence.Table(name = "restaurant")
public class Restaurant {

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restaurant_id")
    private Long id;
    @Column(name = "restaurant_name")
    private String name;
    @Column(name = "restaurant_location")
    private String location;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TableInfo> tableInfoList = new ArrayList<>();


    public void change(String name, String location) {
        this.name = name;
        this.location = location;
    }

}

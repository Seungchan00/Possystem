
package PosSys.PosSys.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@jakarta.persistence.Table(name = "restaurant")
public class Restaurant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "restaurant_id")
    private Long id;
    @Column(name = "restaurant_name")
    private String name;
    @Column(name = "restaurant_location")
    private String location;

    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Table table;


    public void change(String name, String location) {
        this.name = name;
        this.location = location;
    }

}

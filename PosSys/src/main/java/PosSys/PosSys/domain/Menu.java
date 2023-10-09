package PosSys.PosSys.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Menu{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id")
    private TableInfo tableInfo;

    private String menu_name;

    private int menu_count;


    // getters and setters


    }


/*
package PosSys.PosSys.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Embeddable
@Getter
public class Menu{

    private String menu_name;

    private int menu_count;

    // getters and setters
    protected Menu(){

    }

    public Menu(String menu_name, int menu_count) {
        this.menu_name = menu_name;
        this.menu_count = menu_count;

    }

}
*/
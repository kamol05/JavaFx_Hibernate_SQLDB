package pet.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Machine {
    @Id
    private int id;

    @Column(nullable = true)
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "machine")
    List<Orders> orders = new ArrayList<Orders>();

//    @Column(nullable = true)
//    private int number;
    @Column(nullable = true)
    private float width;
    @Column(nullable = true)
    private int dentities;
    @Column(nullable = true)
    private int speed; //?

    @Override
    public String toString() {
        return id + "-STANOK";
    }
}

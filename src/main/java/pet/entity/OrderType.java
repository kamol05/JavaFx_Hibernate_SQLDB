package pet.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderType {
    @Id
    private int id;
    private String description;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "orderType")
    List<Orders> orders = new ArrayList<Orders>();

    @Override
    public String toString() {
        return description;
    }
}

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
public class Client {
    @Id
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "client")
    List<Orders> orders = new ArrayList<Orders>();

    @Override
    public String toString() {
        return name;

    }
}

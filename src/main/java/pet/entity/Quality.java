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
public class Quality {
    @Id
    private int id;
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private QualityType type;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "quality")
    List<Orders> orders = new ArrayList<Orders>();

    @Override
    public String toString() {
        return name;
    }
}

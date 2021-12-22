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
public class QualityType {
    @Id
    private int id;
//    private String colors;
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    List<Quality> qualities = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "qualityType")
    List<Orders> orders = new ArrayList<Orders>();

    @Override
    public String toString() {
        return description;
    }
}

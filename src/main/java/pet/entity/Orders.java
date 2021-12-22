package pet.entity;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Client client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Machine machine;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private OrderType orderType;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Quality quality;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private QualityType qualityType;

    private Date date;
    private int m2Total;
    private int m2ForWork;
    private int atk; //?
    private int m2Weaved;
    private int m2Left;
    private int daysWork;
    private int orderNumber;
    private int stockNumber;
    private String willWeave;


    @Override
    public String toString() {
        return orderNumber + " " + stockNumber + " " + client;
    }
}

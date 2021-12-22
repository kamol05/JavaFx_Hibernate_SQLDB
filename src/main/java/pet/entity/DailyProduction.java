package pet.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Getter
@Setter
public class DailyProduction {

    public DailyProduction() {
    }

    @Id
    private int id;
    private Date date;
    private String machine;
    private int speed;
    private int workedSteps;
    private int workedm2;
    private int norma;
    private int percentage;

    @OneToOne
    private Orders order;

    private int machinewidth;
    private String clientname;











}



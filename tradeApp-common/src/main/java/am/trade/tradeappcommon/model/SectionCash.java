package am.trade.tradeappcommon.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "section_cash")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SectionCash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User user;
    @Column
    private double incoming;
    @Column
    private double outcoming;
    @Column
    private String description;
    @Column
    private Date date;
}

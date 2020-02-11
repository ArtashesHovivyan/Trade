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
@Table(name = "transfer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private User to;
    @ManyToOne
    private People toPeople;
    @ManyToOne
    private User from;
    @Column
    private double price;
    @Column
    private Date date;
}

package am.trade.tradeappcommon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @ManyToOne
    private Order order;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private double count;
    @Column
    private String categoryName;
    @Column
    private double priceIn;
    @Column
    private double priceOut;
}

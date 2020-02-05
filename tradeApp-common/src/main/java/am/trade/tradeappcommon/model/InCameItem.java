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
@Table(name = "incame_item")
public class InCameItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Items items;
    @ManyToOne
    private InCame inCame;
    @Column
    private double count;
}

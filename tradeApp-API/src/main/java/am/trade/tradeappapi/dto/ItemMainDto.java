package am.trade.tradeappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemMainDto {
    private int id;

    private String title;

    private double count;

    private double priceOut;
}

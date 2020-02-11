package am.trade.tradeappapi.dto;

import am.trade.tradeappcommon.model.Items;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindOrderDto {

    private Date date;
    private List<ItemMainDto> itemMainDtos;
    private double orderSum;
}

package am.trade.tradeappweb.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddOrderDto {
    private String phoneNumber;
    private List<OrderItemDto> orderItemDtos;
    private double count;
}

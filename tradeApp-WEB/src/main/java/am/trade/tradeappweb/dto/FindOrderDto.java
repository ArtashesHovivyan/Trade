package am.trade.tradeappweb.dto;

import am.trade.tradeappcommon.model.People;
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

    private int id;
    private Date date;
    private List<ItemMainDto> itemMainDtos;
    private double orderSum;
    private People people;
    private UserDto userDto;
}

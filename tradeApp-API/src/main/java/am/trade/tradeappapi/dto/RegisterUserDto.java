package am.trade.tradeappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterUserDto {

    private String name;
    private String surname;
    private String login;
    private String password;
    private Integer[] rolesId;
}

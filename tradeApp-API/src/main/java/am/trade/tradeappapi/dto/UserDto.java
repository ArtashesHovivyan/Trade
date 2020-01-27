package am.trade.tradeappapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String login;
//    Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4NjE1MDE5NSwiaWF0IjoxNTgwMTUwMTk1fQ.EAjfN95WZznamSutT0tBdxSxQAbfyi39qovE6SqyYBfR2kee1t857lh9cXwk_MjaUS1IKPG7Yld32Y_j7jKkcQ

}
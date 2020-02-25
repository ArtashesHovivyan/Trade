package am.trade.tradeappweb.dto;

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
//    Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwb3hvcyIsImV4cCI6MTU4NzM2NjQzMiwiaWF0IjoxNTgxMzY2NDMyfQ.s3sraEqNkG3vRK5K41663uU5N72DTq9uk1j0nib-zs9ZZBPVXD3C0G6OOZFH19-4JmH1mWxOPEHjapyF8LL_XQ
//    Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTU4NjE1MDE5NSwiaWF0IjoxNTgwMTUwMTk1fQ.EAjfN95WZznamSutT0tBdxSxQAbfyi39qovE6SqyYBfR2kee1t857lh9cXwk_MjaUS1IKPG7Yld32Y_j7jKkcQ

//    add Order json
//    {
//        "people": {
//        "id": 1,
//                "name": "Tiko",
//                "surname": "Khach",
//                "email": "tik@mail.ru",
//                "phone": "093-898394",
//                "address": "Tik",
//                "userType": "BUYER"
//    },
//        "user": {
//        "id": 9,
//                "name": "Styopa",
//                "surname": "Khachatryan",
//                "login": "admin",
//                "password": "$2a$10$o4MFQoVbdDzaiPNzC.5d/uPjXF4Ff/RoxnxY5YkL4CoSEKcgyn0yK"
//    },
//        "itemsList": [
//        {
//            "id": 2,
//                "title": "sss",
//                "description": "ss",
//                "count": 10,
//                "minCount": 3,
//                "barcode": "123456",
//                "category": {
//            "id": 1,
//                    "name": "Cat1",
//                    "description": "Cat1",
//                    "parentId": 1
//        },
//            "priceIn": 800,
//                "priceOut": 1100
//        }
//]
//    }


}
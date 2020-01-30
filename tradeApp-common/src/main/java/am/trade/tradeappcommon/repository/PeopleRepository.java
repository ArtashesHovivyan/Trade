package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.People;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<People, Integer> {

    People findByEmail(String email);

    People findById(int id);

    People getPeopleByEmailOrPhone(String email, String phone);

    People getPeopleByPhone(String phone);


}

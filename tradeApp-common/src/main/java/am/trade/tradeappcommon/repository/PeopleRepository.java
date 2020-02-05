package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.People;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PeopleRepository extends JpaRepository<People, Integer> {

    People findByEmail(String email);

    People findById(int id);

    @Query(value = "SELECT u FROM People u WHERE u.email =:search OR u.phone =:search")
    People findByEmailOrPhone(@Param("search") String search);

    People getPeopleByPhone(String phone);
}

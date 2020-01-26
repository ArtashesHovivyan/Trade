package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> getUserByLogin(String login);
}

package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Optional<Category> getByName(String name);

}

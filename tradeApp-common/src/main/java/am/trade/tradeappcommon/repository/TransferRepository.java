package am.trade.tradeappcommon.repository;

import am.trade.tradeappcommon.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Integer> {
}

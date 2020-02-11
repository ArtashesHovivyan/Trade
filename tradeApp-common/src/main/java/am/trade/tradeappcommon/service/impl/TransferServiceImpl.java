package am.trade.tradeappcommon.service.impl;

import am.trade.tradeappcommon.model.Transfer;
import am.trade.tradeappcommon.repository.TransferRepository;
import am.trade.tradeappcommon.service.TransferService;
import org.springframework.stereotype.Service;

@Service
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    public TransferServiceImpl(TransferRepository transferRepository) {
        this.transferRepository = transferRepository;
    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }
}

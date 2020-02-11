package am.trade.tradeappapi.endpoint;

import am.trade.tradeappapi.dto.TransferDto;
import am.trade.tradeappapi.security.CurrentUser;
import am.trade.tradeappcommon.model.SectionCash;
import am.trade.tradeappcommon.service.SectionCashService;
import am.trade.tradeappcommon.service.TransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/transfer")
public class TransferEndpoint {

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final SectionCashService sectionCashService;
    private final TransferService transferService;

    public TransferEndpoint(SectionCashService sectionCashService, TransferService transferService) {
        this.sectionCashService = sectionCashService;

        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity transfer(@RequestBody TransferDto transferDto, @AuthenticationPrincipal CurrentUser currentUser) {
        SectionCash fromSectionCash = sectionCashService.getById(transferDto.getSectionId());
        String date = sdf.format(new Date());
        SectionCash toSectionCash = sectionCashService.searchByDateAndUserId(date, currentUser.getUser().getId());
        double toIncoming = toSectionCash.getIncoming();
        double fromOutComing = transferDto.getOutComing();
        double toIncomingSum = toIncoming + fromOutComing;
        am.trade.tradeappcommon.model.Transfer transfer = new am.trade.tradeappcommon.model.Transfer();
        transfer.setFrom(fromSectionCash.getUser());
        transfer.setTo(currentUser.getUser());
        transfer.setPrice(transferDto.getOutComing());
        transferService.save(transfer);
        toSectionCash.setIncoming(toIncomingSum);
        toSectionCash.setDescription(transferDto.getDescription());
        sectionCashService.save(toSectionCash);
        fromSectionCash.setIncoming(fromSectionCash.getIncoming() - fromOutComing);
        fromSectionCash.setOutcoming(fromSectionCash.getOutcoming()+fromOutComing);
        sectionCashService.save(fromSectionCash);
        return ResponseEntity.ok("Transfer are saved");
//        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

}

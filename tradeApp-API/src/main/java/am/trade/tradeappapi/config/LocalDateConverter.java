package am.trade.tradeappapi.config;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;

@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {
    @Override
    public Timestamp convertToDatabaseColumn(LocalDate ldt) {
        return Timestamp.valueOf(ldt.atStartOfDay());
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp ts) {
        return ts.toLocalDateTime().toLocalDate();
    }
}
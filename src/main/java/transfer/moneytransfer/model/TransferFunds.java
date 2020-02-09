package transfer.moneytransfer.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@RequiredArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class TransferFunds {

    private final String currency;
    private final BigDecimal amount;
}

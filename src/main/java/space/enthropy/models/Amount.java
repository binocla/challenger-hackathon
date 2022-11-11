package space.enthropy.models;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class Amount implements Serializable {
    @Serial
    private static final long serialVersionUID = 5967569054371806454L;

    @NotBlank
    private String currency;
    @Positive
    @Digits(integer = 10, fraction = 2)
    private BigDecimal value;
}

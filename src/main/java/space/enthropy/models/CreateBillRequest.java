package space.enthropy.models;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
public class CreateBillRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -7272870757188361520L;

    @NotNull
    private Amount amount;
    @Nullable
    private Instant expirationDateTime;
    @Nullable
    private Customer customer;
    @Nullable
    @SuppressWarnings("java:S1948")
    private Object customFields;
    @NotEmpty(message = "Flags are required")
    private List<String> flags;
}

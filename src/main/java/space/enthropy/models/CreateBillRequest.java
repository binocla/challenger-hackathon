package space.enthropy.models;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
@SuppressWarnings("all")
public class CreateBillRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -7272870757188361520L;

    @NotNull
    private Amount amount;
    @Nullable
    private String expirationDateTime;
    @Nullable
    private Customer customer;
    @Nullable
    private Object customFields;
    @NotEmpty
    private List<String> flags;
}

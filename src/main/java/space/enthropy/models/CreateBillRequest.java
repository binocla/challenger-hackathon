package space.enthropy.models;

import com.qiwi.billpayments.sdk.model.MoneyAmount;
import com.qiwi.billpayments.sdk.model.in.CustomFields;
import com.qiwi.billpayments.sdk.model.in.Customer;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.time.ZonedDateTime;

@Data
@SuppressWarnings("all")
public class CreateBillRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = -7272870757188361520L;

    @NotNull
    private MoneyAmount amount;
    @Nullable
    private String comment;
    @Nullable
    private ZonedDateTime expirationDateTime;
    @Nullable
    private Customer customer;
    @Nullable
    private CustomFields customFields;
}

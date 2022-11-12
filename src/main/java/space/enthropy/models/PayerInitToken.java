package space.enthropy.models;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
public class PayerInitToken implements Serializable {
    @Serial
    private static final long serialVersionUID = -674793840176641762L;
    @NotBlank(message = "RequestId is required")
    private String requestId;
    @NotBlank(message = "Phone is required")
    @Length(min = 11, max = 11, message = "Phone must be 11 digits")
    @Schema(example = "79050331122")
    private String phone;
    @NotBlank(message = "AccountId is required")
    private String accountId;
}

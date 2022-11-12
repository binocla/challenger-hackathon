package space.enthropy.models;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;


@Data
public class PayerUpdateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2503240180270487796L;

    @NotBlank(message = "Phone is required")
    @Length(min = 11, max = 11, message = "Phone must be 11 digits")
    private String phone;
    @NotBlank(message = "SiteId is required")
    private String siteId;
    @NotBlank(message = "RequestId is required")
    private String requestId;
}

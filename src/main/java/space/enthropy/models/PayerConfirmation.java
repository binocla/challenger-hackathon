package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class PayerConfirmation implements Serializable {
    @Serial
    private static final long serialVersionUID = 348822149566518350L;

    private String requestId;
    private String smsCode;
}

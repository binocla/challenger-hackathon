package space.enthropy.models;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = 8836854416300008596L;

    @NotBlank
    private String account;
}

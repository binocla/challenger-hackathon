package space.enthropy.models;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class CreatedToken implements Serializable {
    @Serial
    private static final long serialVersionUID = 5799300734966852745L;

    private String token;
    private String name;

}

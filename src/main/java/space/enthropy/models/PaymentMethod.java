package space.enthropy.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "payment_methods")
public class PaymentMethod implements Serializable {
    @Serial
    private static final long serialVersionUID = 4748619700623919509L;


    @NotBlank
    @Id
    @Column(name = "id", nullable = false)
    private String paymentToken;
    @Column
    private UUID paymentFakeId;
    @NotBlank
    @Column
    @Schema(example = "TOKEN")
    private String type;
    @Column
    @Schema(example = "437c71a4-8517-4134-8b3f-a9f11d83548b")
    private String accountId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PaymentMethod that = (PaymentMethod) o;
        return paymentFakeId != null && Objects.equals(paymentFakeId, that.paymentFakeId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

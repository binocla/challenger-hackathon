package space.enthropy.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
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
    private String type;
    @Column
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

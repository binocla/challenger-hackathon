package space.enthropy.models;

import io.smallrye.common.constraint.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.Hibernate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;


@Entity
@Table(name = "payer")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PayerEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -5390055061794219477L;

    @Id
    @Column(nullable = false)
    @NotNull
    @Schema(example = "f1b2c3d4-e5f6-g7h8-i9j0-k1l2m3n4o5p6")
    private UUID accountId;
    @NotBlank(message = "Phone is required")
    @Column(nullable = false)
    @Length(min = 11, max = 11, message = "Phone must be 11 digits")
    @Schema(example = "79050331122")
    private String phone;
    @Nullable
    @Column
    @Schema(example = "sa3khn-11")
    private String siteId;
    @Nullable
    @Column(unique = true)
    private String requestId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        PayerEntity that = (PayerEntity) o;
        return accountId != null && Objects.equals(accountId, that.accountId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

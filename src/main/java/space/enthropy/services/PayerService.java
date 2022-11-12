package space.enthropy.services;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import space.enthropy.models.PayerConfirmation;
import space.enthropy.models.PayerEntity;
import space.enthropy.models.PayerInitToken;
import space.enthropy.models.PayerTokenResponse;
import space.enthropy.models.PayerUpdateRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

public interface PayerService {

    /**
     * Creates payer in Qiwi
     *
     * @param payerEntity payer to create
     * @return created payer
     * @author Sergey Shamov
     */
    Uni<PayerEntity> createPayer(@NotNull @Valid PayerEntity payerEntity);

    /**
     * Updates payer in Qiwi
     *
     * @param payerUpdateRequest payer to update
     * @return updated payer
     * @author Sergey Shamov
     */
    Uni<Integer> updatePayer(@NotNull UUID id, @NotNull @Valid PayerUpdateRequest payerUpdateRequest);

    /**
     * Initializes token for payer
     *
     * @param clientToken    client token
     * @param siteId         site id
     * @param payerInitToken payer init token
     * @return payer token response
     * @author Sergey Shamov
     */
    Uni<PayerTokenResponse> initToken(@NotBlank String clientToken, @NotBlank String siteId, @NotNull @Valid PayerInitToken payerInitToken);

    /**
     * Confirms token for payer
     *
     * @param siteId            site id
     * @param clientToken       client token
     * @param payerConfirmation payer confirmation
     * @return payer token response
     * @author Sergey Shamov
     */
    Uni<PayerTokenResponse> confirmToken(@NotBlank String siteId, @NotBlank String clientToken, @NotNull @Valid PayerConfirmation payerConfirmation);


    /**
     * Gets payer from Qiwi by id
     *
     * @param id payer id
     * @return payer
     * @author Sergey Shamov
     */
    Uni<PayerEntity> getPayerById(@NotNull UUID id);
}

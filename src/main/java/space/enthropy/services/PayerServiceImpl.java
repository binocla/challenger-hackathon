package space.enthropy.services;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.clients.PayerClient;
import space.enthropy.models.PayerConfirmation;
import space.enthropy.models.PayerEntity;
import space.enthropy.models.PayerInitToken;
import space.enthropy.models.PayerTokenResponse;
import space.enthropy.models.PayerUpdateRequest;
import space.enthropy.repositories.PayerRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@ApplicationScoped
public class PayerServiceImpl implements PayerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayerServiceImpl.class);

    @Inject
    PayerRepository payerRepository;

    @RestClient
    PayerClient payerClient;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Uni<PayerEntity> createPayer(@NotNull @Valid PayerEntity payerEntity) {
        LOGGER.debug("Creating payer with request: {}", payerEntity);
        return payerRepository.save(payerEntity)
                .onItem().invoke(payer -> LOGGER.debug("Payer created with response: {}", payer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    @SuppressWarnings({"java:S2139", "java:S112"})
    public Uni<Integer> updatePayer(@NotNull UUID id, @NotNull @Valid PayerUpdateRequest payerUpdateRequest) {
        PayerEntity payerEntity = new PayerEntity();
        LOGGER.debug("Updating payer with request: {}", payerUpdateRequest);
        try {
            BeanUtils.copyProperties(payerEntity, payerUpdateRequest);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("Error while copying properties", e);
            throw new RuntimeException(e);
        }
        return payerRepository.updatePayer(id, payerEntity)
                .onItem().invoke(payer -> LOGGER.debug("Payer updated with response: {}", payer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<PayerTokenResponse> initToken(@NotBlank String clientToken, @NotBlank String siteId, @NotNull @Valid PayerInitToken payerInitToken) {
        LOGGER.debug("Init token with request: {}", payerInitToken);
        return payerClient.initToken(siteId, "Bearer " + clientToken, payerInitToken)
                .onItem().invoke(payer -> LOGGER.debug("Payer token initialized with response: {}", payer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<PayerTokenResponse> confirmToken(String siteId, String clientToken, PayerConfirmation payerConfirmation) {
        LOGGER.debug("Confirm token with request: {}", payerConfirmation);
        return payerClient.confirmToken(siteId, clientToken, payerConfirmation)
                .onItem().invoke(payer -> LOGGER.debug("Payer token confirmed with response: {}", payer));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public Uni<PayerEntity> getPayerById(@NotNull UUID id) {
        LOGGER.debug("Getting payer by id: {}", id);
        return payerRepository.getById(id)
                .invoke(payer -> LOGGER.debug("Payer found with response: {}", payer));
    }
}

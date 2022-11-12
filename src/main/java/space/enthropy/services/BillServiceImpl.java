package space.enthropy.services;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.clients.BillCreateClient;
import space.enthropy.models.CreateBillRequest;
import space.enthropy.models.CreateBillResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.UUID;

@ApplicationScoped
public class BillServiceImpl implements BillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillServiceImpl.class);

    @ConfigProperty(name = "qiwi.site.id")
    String siteId;

    @ConfigProperty(name = "qiwi.site.token")
    String siteToken;

    @RestClient
    BillCreateClient billCreateClient;

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("java:S2201")
    public Uni<CreateBillResponse> createBill(@NotNull @Valid CreateBillRequest createBillRequest) {
        // TODO rework
        BigDecimal var = createBillRequest.getAmount().getValue();
        var.setScale(2);
        LOGGER.debug("Creating bill with request: {}", createBillRequest);
        return billCreateClient.createBill(siteId,
                        UUID.randomUUID().toString(),
                        "Bearer " + siteToken,
                        createBillRequest)
                .invoke(billResponse -> LOGGER.debug("Bill created with response: {}", billResponse));
    }
}

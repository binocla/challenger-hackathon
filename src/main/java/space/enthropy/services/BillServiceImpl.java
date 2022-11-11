package space.enthropy.services;

import com.qiwi.billpayments.sdk.client.BillPaymentClient;
import com.qiwi.billpayments.sdk.client.BillPaymentClientFactory;
import com.qiwi.billpayments.sdk.model.in.CreateBillInfo;
import com.qiwi.billpayments.sdk.model.out.BillResponse;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.models.CreateBillRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.Valid;
import java.net.URISyntaxException;
import java.util.UUID;

@ApplicationScoped
public class BillServiceImpl implements BillService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BillServiceImpl.class);
    private final BillPaymentClient billPaymentClient;

    public BillServiceImpl(@ConfigProperty(name = "qiwi.secret.key") String secretKey) {
        this.billPaymentClient = BillPaymentClientFactory.createDefault(secretKey);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Uni<BillResponse> createBill(@NotNull @Valid CreateBillRequest createBillRequest) {
        return Uni.createFrom().nullItem()
                .invoke(task -> LOGGER.debug("Creating bill with request: {}", createBillRequest))
                .map(task -> new CreateBillInfo(UUID.randomUUID().toString(),
                        createBillRequest.getAmount(), createBillRequest.getComment(),
                        createBillRequest.getExpirationDateTime(), createBillRequest.getCustomer(),
                        "http://localhost:8080/bill"))
                .map(bill -> {
                    BillResponse billResponse = null;
                    try {
                        billResponse = billPaymentClient.createBill(bill);
                        LOGGER.debug("Bill created: {}", billResponse);
                    } catch (URISyntaxException e) {
                        LOGGER.error("Error while creating bill", e);
                    }
                    return billResponse;
                });
    }
}

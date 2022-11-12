package space.enthropy.repositories;

import io.quarkus.hibernate.reactive.panache.PanacheRepository;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.models.PayerEntity;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class PayerRepository implements PanacheRepository<PayerEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayerRepository.class);

    @Timed(name = "space.entrophy.repositories.payerrepository.save", description = "Save payer measurement")
    public Uni<PayerEntity> save(PayerEntity payerEntity) {
        LOGGER.info("Saving payer entity: {}", payerEntity);
        return persistAndFlush(payerEntity);
    }

    @Timed(name = "space.enthropy.repositories.payerrepository.getbyid", description = "Get payer by id measurement")
    public Uni<PayerEntity> getById(UUID id) {
        LOGGER.debug("Getting payer entity by id: {}", id);
        return find("id", id).firstResult();
    }

    @Timed(name = "space.enthropy.repositories.payerrepository.updatepayer", description = "Update payer measurement")
    public Uni<Integer> updatePayer(UUID id, PayerEntity payerEntity) {
        LOGGER.debug("Updating payer entity: {}", payerEntity);
        return update("phone = ?1, siteId = ?2, requestId = ?3 where accountId = ?4", payerEntity.getPhone(), payerEntity.getSiteId(), payerEntity.getRequestId(), id);
    }

}

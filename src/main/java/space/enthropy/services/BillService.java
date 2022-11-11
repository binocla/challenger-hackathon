package space.enthropy.services;


import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import space.enthropy.models.CreateBillRequest;
import space.enthropy.models.CreateBillResponse;

import javax.validation.Valid;

public interface BillService {

    /**
     * Creates bill in Qiwi
     *
     * @param createBillRequest request to create bill
     * @return created bill
     * @author Sergey Shamov
     */
    Uni<CreateBillResponse> createBill(@NotNull @Valid CreateBillRequest createBillRequest);
}

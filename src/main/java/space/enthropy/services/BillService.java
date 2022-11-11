package space.enthropy.services;

import com.qiwi.billpayments.sdk.model.out.BillResponse;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import space.enthropy.models.CreateBillRequest;

import javax.validation.Valid;

public interface BillService {

    /**
     * Creates bill in Qiwi
     *
     * @param createBillRequest request to create bill
     * @return created bill
     * @author Sergey Shamov
     */
    Uni<BillResponse> createBill(@NotNull @Valid CreateBillRequest createBillRequest);
}

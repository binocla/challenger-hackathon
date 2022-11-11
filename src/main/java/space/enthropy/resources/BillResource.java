package space.enthropy.resources;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import space.enthropy.models.CreateBillRequest;
import space.enthropy.models.CreateBillResponse;
import space.enthropy.services.BillServiceImpl;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/bill")
@Tag(name = "Bill API", description = "Bill operations")
public class BillResource {
    @Inject
    BillServiceImpl billService;

    @POST
    @Timed(name = "space.enthropy.resources.billresource.createBill", description = "A measure of how long it takes to create bill")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponse(responseCode = "200", description = "Bill created", content = @Content(schema = @Schema(implementation = CreateBillResponse.class)))
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<CreateBillResponse> createBill(@NotNull @Valid CreateBillRequest createBillRequest) {
        return billService.createBill(createBillRequest);
    }
}
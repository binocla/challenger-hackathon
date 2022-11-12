package space.enthropy.resources;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Uni;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import space.enthropy.models.PayerConfirmation;
import space.enthropy.models.PayerEntity;
import space.enthropy.models.PayerInitToken;
import space.enthropy.models.PayerTokenResponse;
import space.enthropy.models.PayerUpdateRequest;
import space.enthropy.services.PayerService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.util.UUID;

@Path("/payer")
@Tag(name = "Payer API", description = "Payer operations")
public class PayerResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PayerResource.class);
    @Inject
    PayerService payerService;

    @POST
    @Timed(name = "space.enthropy.resources.payerresource.createpayer", description = "A measure of how long it takes to create payer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles payer creation", description = "Handles payer creation")
    @APIResponse(responseCode = "201", description = "Payer created")
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<RestResponse<Void>> addPayer(@NotNull @Valid PayerEntity payer) {
        LOGGER.debug("addPayer: {}", payer);
        return payerService.createPayer(payer)
                .onItem().transform(resp -> RestResponse.created(URI.create("/payer/" + resp.getAccountId())));
    }

    @PUT
    @Path("/{id}")
    @Timed(name = "space.enthropy.resources.payerresource.updatepayer", description = "A measure of how long it takes to update payer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles payer update", description = "Handles payer update")
    @APIResponse(responseCode = "200", description = "Payer updated")
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<RestResponse<Integer>> updatePayer(@PathParam("id") @NotNull UUID id, @NotNull @Valid PayerUpdateRequest payerUpdateRequest) {
        LOGGER.debug("updatePayer: {}", payerUpdateRequest);
        return payerService.updatePayer(id, payerUpdateRequest)
                .onItem().transform(RestResponse::ok);
    }

    @GET
    @Path("/{id}")
    @Timed(name = "space.enthropy.resources.payerresource.getpayer", description = "A measure of how long it takes to get payer")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles payer retrieval", description = "Handles payer retrieval")
    @APIResponse(responseCode = "200", description = "Payer found", content = @Content(schema = @Schema(implementation = PayerEntity.class), mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "404", description = "Payer not found")
    public Uni<RestResponse<PayerEntity>> getPayer(@PathParam("id") UUID id) {
        LOGGER.debug("getPayer: {}", id);
        return payerService.getPayerById(id)
                .onItem().transform(resp -> {
                    if (resp == null) {
                        return RestResponse.notFound();
                    }
                    return RestResponse.ok(resp);
                });
    }

    @POST
    @Path("/token/init/{siteId}")
    @Timed(name = "space.enthropy.resources.payerresource.inittokenforpayer", description = "A measure of how long it takes to init token for payer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles payer token init", description = "Handles payer token init")
    @APIResponse(responseCode = "200", description = "Payer token is being initialized", content = @Content(schema = @Schema(implementation = PayerTokenResponse.class), mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<PayerTokenResponse> initTokenForPayer(@NotBlank @HeaderParam("x-token") String token, @NotBlank @PathParam("siteId") @Schema(example = "sa3khn-11") String siteId, @NotNull @Valid PayerInitToken payerInitToken) {
        LOGGER.debug("initTokenForPayer: {}", payerInitToken);
        return payerService.initToken(token, siteId, payerInitToken);
    }

    @POST
    @Path("/token/confirm/{siteId}")
    @Timed(name = "space.enthropy.resources.payerresource.confirmtokenforpayer", description = "A measure of how long it takes to confirm token for payer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Handles payer token confirmation", description = "Handles payer token confirmation")
    @APIResponse(responseCode = "200", description = "Payer token is being confirmed", content = @Content(schema = @Schema(implementation = PayerTokenResponse.class), mediaType = MediaType.APPLICATION_JSON))
    @APIResponse(responseCode = "400", description = "Bad request")
    public Uni<PayerTokenResponse> confirmTokenForPayer(@PathParam("siteId") @Schema(example = "sa3khn-11") String siteId, @NotBlank @HeaderParam("x-token") String token, @NotNull @Valid PayerConfirmation payerConfirmation) {
        LOGGER.debug("confirmTokenForPayer: {}", payerConfirmation);
        return payerService.confirmToken(siteId, token, payerConfirmation);
    }


}

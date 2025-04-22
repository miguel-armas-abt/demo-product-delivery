package com.demo.poc.entrypoint.contactdata.service;

import com.demo.poc.commons.custom.states.StateBuilder;
import com.demo.poc.commons.custom.states.context.Context;
import com.demo.poc.commons.custom.exceptions.NoSuchPendingOrderException;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.states.dto.response.ContextResponse;
import com.demo.poc.commons.custom.states.enums.State;
import com.demo.poc.commons.custom.states.sevice.StateService;
import com.demo.poc.entrypoint.contactdata.dto.request.ContactDataRequestDto;
import com.demo.poc.entrypoint.contactdata.dto.response.ContactDataResponseDto;
import com.demo.poc.entrypoint.contactdata.mapper.ContactDataContextMapper;
import com.demo.poc.entrypoint.contactdata.mapper.ContactDataResponseMapper;
import com.demo.poc.entrypoint.contactdata.repository.contactdata.ContactDataRepository;
import com.demo.poc.entrypoint.contactdata.repository.contactdata.wrapper.response.ContactDataResponseWrapper;
import com.demo.poc.entrypoint.contactdata.repository.orders.PendingOrderRepository;
import com.demo.poc.entrypoint.contactdata.repository.orders.wrapper.response.PendingOrderResponseWrapper;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
@RequiredArgsConstructor
public class ContactDataServiceImpl implements ContactDataService, StateService {

  @RestClient
  PendingOrderRepository pendingOrderRepository;

  @RestClient
  ContactDataRepository contactDataRepository;

  private final StateBuilder stateBuilder;
  private final ContactDataContextMapper requestMapper;
  private final ContactDataResponseMapper responseMapper;

  @Override
  public Uni<ContactDataResponseDto> getContactData(ContactDataRequestDto request) {
    String customerId = request.getCustomer().getCustomerId();
    String pendingOrderId = request.getPendingOrderId();

    Uni<PendingOrderResponseWrapper> pendingOrderUni = getPendingOrder(customerId, pendingOrderId);
    Uni<ContactDataResponseWrapper> contactDataUni = contactDataRepository.getContactData(customerId);

    return Uni.combine().all()
        .unis(pendingOrderUni, contactDataUni)
        .with((pendingOrder, contactData) -> requestMapper.toContext(pendingOrder, contactData, request))
        .map(context -> (ProductDeliveryContext) this.processState(context, null))
        .map(context -> {
          ContextResponse contextResponse = stateBuilder.of(context).buildAsResponse();
          return responseMapper.toResponse(contextResponse, context.getContactData().getAddresses());
        });
  }

  private Uni<PendingOrderResponseWrapper> getPendingOrder(String customerId, String pendingOrderId) {
    return pendingOrderRepository.getPendingOrders(customerId)
        .filter(pendingOrder -> pendingOrder.getId().equals(pendingOrderId))
        .toUni()
        .onItem().ifNull().failWith(new NoSuchPendingOrderException(pendingOrderId));
  }

  @Override
  public Context processState(Context context, State previousState) {
    return stateBuilder.init(context)
        .continueToNextState(() -> State.AVAILABLE_DATES)
        .build();
  }
}

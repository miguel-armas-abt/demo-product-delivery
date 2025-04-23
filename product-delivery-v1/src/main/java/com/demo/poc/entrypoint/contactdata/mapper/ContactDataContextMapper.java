package com.demo.poc.entrypoint.contactdata.mapper;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import com.demo.poc.commons.core.config.MappingConfig;
import com.demo.poc.commons.custom.states.context.ProductDeliveryContext;
import com.demo.poc.commons.custom.enums.Priority;
import com.demo.poc.commons.custom.exceptions.EmailNotFoundException;
import com.demo.poc.commons.custom.exceptions.PhoneNotFoundException;
import com.demo.poc.commons.custom.states.enums.State;
import com.demo.poc.entrypoint.contactdata.dto.request.ContactDataRequestDto;
import com.demo.poc.entrypoint.contactdata.repository.contactdata.wrapper.response.ContactDataResponseWrapper;
import com.demo.poc.entrypoint.contactdata.repository.orders.wrapper.response.PendingOrderResponseWrapper;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MappingConfig.class)
public interface ContactDataContextMapper {

  @Mapping(target = "pendingOrder", source = "pendingOrder")
  @Mapping(target = "contactData.customerId", source = "contactData.customerId")
  @Mapping(target = "contactData.documentType", source = "contactData.documentType")
  @Mapping(target = "contactData.documentNumber", source = "contactData.documentNumber")
  @Mapping(target = "contactData.names", source = "contactData.names")
  @Mapping(target = "contactData.surnames", source = "contactData.surnames")
  @Mapping(target = "contactData.addresses", source = "contactData.addresses")
  ProductDeliveryContext baseContext(PendingOrderResponseWrapper pendingOrder,
                                     ContactDataResponseWrapper contactData);

  default ProductDeliveryContext toContext(PendingOrderResponseWrapper pendingOrder,
                                           ContactDataResponseWrapper contactData,
                                           ContactDataRequestDto request) {
    ProductDeliveryContext context = baseContext(pendingOrder, contactData);
    context.getContactData().setEmail(selectEmail(request.getCustomer().getDeliveryEmail(), contactData.getEmails()));
    context.getContactData().setPhone(selectPhone(request.getCustomer().getDeliveryPhone(), contactData.getPhones()));
    context.setCurrentState(State.firstState());
    return context;
  }

  static String selectEmail(String deliveryEmail, List<ContactDataResponseWrapper.EmailWrapper> emails) {
    return Optional.ofNullable(deliveryEmail)
        .filter(StringUtils::isNotEmpty)
        .orElseGet(() -> emails.stream()
            .filter(email -> Priority.FIRST.getCode().equals(email.getPriority()))
            .map(ContactDataResponseWrapper.EmailWrapper::getValue)
            .findFirst()
            .orElseThrow(EmailNotFoundException::new)
        ).toUpperCase(Locale.ROOT);
  }

  static String selectPhone(String deliveryPhone, List<ContactDataResponseWrapper.PhoneWrapper> phones) {
    return Optional.ofNullable(deliveryPhone)
        .filter(StringUtils::isNotEmpty)
        .orElseGet(() -> phones.stream()
            .filter(phone -> Priority.FIRST.getCode().equals(phone.getPriority()))
            .map(ContactDataResponseWrapper.PhoneWrapper::getNumber)
            .findFirst()
            .orElseThrow(PhoneNotFoundException::new)
        );
  }
}

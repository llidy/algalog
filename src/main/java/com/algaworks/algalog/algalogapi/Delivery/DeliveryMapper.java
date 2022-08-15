package com.algaworks.algalog.algalogapi.Delivery;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DeliveryMapper {

    private ModelMapper modelMapper;

    public DeliveryResponse toModel(Delivery delivery) {

        return modelMapper.map(delivery, DeliveryResponse.class);
    }

    public List<DeliveryResponse> toCollectionModel(List<Delivery> deliveries) {
        return deliveries.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Delivery toEntity(DeliveryRequest deliveryRequest) {
        return modelMapper.map(deliveryRequest, Delivery.class);
    }
}

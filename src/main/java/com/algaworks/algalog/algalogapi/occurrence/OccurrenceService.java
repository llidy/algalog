package com.algaworks.algalog.algalogapi.occurrence;

import com.algaworks.algalog.algalogapi.Delivery.Delivery;
import com.algaworks.algalog.algalogapi.Delivery.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class OccurrenceService {

    private DeliveryService deliveryService;

    @Transactional
    public Occurrence register(Long deliveryId, String description) {
        Delivery delivery = deliveryService.search(deliveryId);

        return delivery.addOccurrence(description);
    }
}

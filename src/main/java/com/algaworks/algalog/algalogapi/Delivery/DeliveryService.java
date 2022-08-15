package com.algaworks.algalog.algalogapi.Delivery;

import com.algaworks.algalog.algalogapi.Customers.Customer;
import com.algaworks.algalog.algalogapi.Customers.CustomerService;
import com.algaworks.algalog.algalogapi.exception.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final CustomerService customerService;


    @Transactional
    public Delivery solicitation(Delivery delivery) {
        Customer customer = customerService.customerExists(delivery.getCustomer().getId());

        delivery.setCustomer(customer);
        delivery.setStatus(StatusDelivery.PENDING);
        delivery.setDateStart(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }

    public Delivery search(Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada"));

    }

    public void finalization(@PathVariable Long deliveryId) {
        Delivery delivery = search(deliveryId);

        delivery.finish(delivery);

        deliveryRepository.save(delivery);
    }

}

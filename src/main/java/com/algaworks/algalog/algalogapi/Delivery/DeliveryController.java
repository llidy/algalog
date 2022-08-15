package com.algaworks.algalog.algalogapi.Delivery;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryService deliveryService;

    @Autowired
    private final DeliveryMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryResponse solicitation(@Valid @RequestBody DeliveryRequest deliveryRequest) {
        Delivery newDelivery = mapper.toEntity(deliveryRequest);
        Delivery solicitationDelivery = deliveryService.solicitation(newDelivery);
        return mapper.toModel(solicitationDelivery);

    }

    @PutMapping("/{deliveryId}/finishing")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finish(@PathVariable Long deliveryId) {

        deliveryService.finalization(deliveryId);
    }

    @GetMapping
    public List<DeliveryResponse> list() {

        return mapper.toCollectionModel(deliveryRepository.findAll());
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryResponse> getId(@PathVariable Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .map(delivery -> ResponseEntity.ok(mapper.toModel(delivery)))
                .orElse(ResponseEntity.notFound().build());
    }

}

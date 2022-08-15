package com.algaworks.algalog.algalogapi.occurrence;

import com.algaworks.algalog.algalogapi.Delivery.Delivery;
import com.algaworks.algalog.algalogapi.Delivery.DeliveryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries/{deliveryId}/occurrences")
public class OccurrenceController {
    private OccurrenceService occurrenceService;
    private OccurrenceMapper occurrenceMapper;
    private DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OccurrenceResponse register(@PathVariable Long deliveryId,
                                       @Valid @RequestBody OccurrenceRequest occurrenceRequest) {

        Occurrence recordedOccurrence = occurrenceService.register(deliveryId, occurrenceRequest.getDescription());

        return occurrenceMapper.toModel(recordedOccurrence);

    }

    @GetMapping
    public List<OccurrenceResponse> list(@PathVariable Long deliveryId) {
        Delivery delivery = deliveryService.search(deliveryId);
        return occurrenceMapper.toCollectionModel(delivery.getOccurrences());
    }

}

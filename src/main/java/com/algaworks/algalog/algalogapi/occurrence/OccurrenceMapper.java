package com.algaworks.algalog.algalogapi.occurrence;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OccurrenceMapper {

    private ModelMapper modelMapper;

    public OccurrenceResponse toModel(Occurrence occurrence) {
        return modelMapper.map(occurrence, OccurrenceResponse.class);
    }

    public List<OccurrenceResponse> toCollectionModel(List<Occurrence> occurrences) {
        return occurrences.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}

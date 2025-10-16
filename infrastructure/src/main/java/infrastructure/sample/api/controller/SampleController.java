package infrastructure.sample.api.controller;

import application.sample.create.CreateSampleUseCase;
import application.sample.delete.DeleteSampleUseCase;
import application.sample.find.FindSampleByIdUseCase;
import application.sample.update.UpdateSampleUseCase;
import domain.query.PageFilter;
import domain.sample.SampleSearchQuery;
import infrastructure.mapper.PageFilterConverter;
import infrastructure.sample.api.request.PageFilterParams;
import infrastructure.sample.api.request.SampleRequest;
import infrastructure.sample.api.response.SampleResponse;
import infrastructure.sample.persistence.SampleGatewayImpl;
import infrastructure.sample.persistence.table.SampleTable;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortHandlerMethodArgumentResolverSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
@AllArgsConstructor
public class SampleController {
    private final CreateSampleUseCase createSampleUseCase;
    private final FindSampleByIdUseCase findSampleByIdUseCase;
    private final UpdateSampleUseCase updateSampleUseCase;
    private final DeleteSampleUseCase deleteSampleUseCase;

    private final SampleGatewayImpl sampleGateway;

    @PostMapping
    public ResponseEntity<SampleResponse> createSample(@Valid @RequestBody SampleRequest request) {
        final var output = createSampleUseCase.execute(request.toSampleInput());
        return ResponseEntity.status(HttpStatus.CREATED).body(SampleResponse.fromOutput(output));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SampleResponse> getSampleById(@PathVariable UUID id) {
        final var output = findSampleByIdUseCase.execute(id);
        return ResponseEntity.ok(SampleResponse.fromOutput(output));
    }

    @GetMapping
    public ResponseEntity<Object> findAllSamplesPaged(Pageable pageable) {
        var result = sampleGateway.findAllSortable(PageFilterConverter.from(pageable));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/v2")
    public ResponseEntity<Object> findAllSamplesV2(Pageable pageable) {
        final var sort = pageable.getSort();
        for (Sort.Order order : sort) {
            System.out.println("Property: " + order.getProperty() + ", Direction: " + order.getDirection());
            new PageFilter.Order(order.getProperty(), PageFilter.Direction.fromString(order.getDirection().name()));
            new Sort.Order(order.getDirection(), order.getProperty());
        }
        System.out.println(sort);
        final Page<SampleTable> output = sampleGateway.findAll(pageable);
        return ResponseEntity.ok(output);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SampleResponse> updateSample(
            @PathVariable UUID id,
            @Valid @RequestBody SampleRequest request) {
        final var output = updateSampleUseCase.execute(request.toUpdateSample(id));
        return ResponseEntity.ok(SampleResponse.fromOutput(output));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSample(@PathVariable UUID id) {
        deleteSampleUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}

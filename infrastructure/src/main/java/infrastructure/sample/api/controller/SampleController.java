package infrastructure.sample.api.controller;

import application.sample.create.CreateSampleUseCase;
import application.sample.delete.DeleteSampleUseCase;
import application.sample.dto.output.SampleOutput;
import application.sample.find.FindSampleByIdUseCase;
import application.sample.findAll.FindAllSamplesUseCase;
import application.sample.update.UpdateSampleUseCase;
import domain.pagination.Pagination;
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
    private final FindAllSamplesUseCase findAllSamplesUseCase;

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
    public ResponseEntity<Pagination<SampleOutput>> findAllSamplesPaged(Pageable pageable) {
        var result = findAllSamplesUseCase.execute(PageFilterConverter.from(pageable));
        return ResponseEntity.ok(result);
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

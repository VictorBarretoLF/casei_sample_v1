package infrastructure.sample.persistence;

import domain.pagination.Pagination;
import domain.query.PageFilter;
import domain.sample.Sample;
import domain.sample.SampleGateway;
import domain.sample.SampleSearchQuery;
import infrastructure.sample.persistence.repository.SampleRepository;
import infrastructure.sample.persistence.table.SampleTable;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SampleGatewayImpl implements SampleGateway {
    private final SampleRepository repository;

    @Override
    public Sample save(Sample sample) {
        final var sampleTable = SampleTable.fromSample(sample);
        final var savedSampleTable = repository.save(sampleTable);
        return savedSampleTable.toDomain();
    }

    @Override
    public Optional<Sample> findById(UUID id) {
        return repository.findById(id)
                .map(SampleTable::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    @Override
    public Pagination<Sample> findAll(PageFilter query) {
        final var pageRequest = PageRequest.of(query.getPage(), query.getPerPage());
        final Page<SampleTable> page = repository.findAll(pageRequest);

        return new Pagination<>(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.getContent().stream()
                        .map(SampleTable::toDomain)
                        .toList(),
                page.isFirst(),
                page.isLast()
        );
    }

    public Page<SampleTable> findAll(Pageable query) {
        return repository.findAll(query);
    }
}

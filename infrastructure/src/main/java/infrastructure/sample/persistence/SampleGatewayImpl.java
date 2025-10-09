package infrastructure.sample.persistence;

import domain.pagination.Pagination;
import domain.sample.Sample;
import domain.sample.SampleGateway;
import domain.sample.SampleSearchQuery;
import infrastructure.sample.persistence.repository.SampleRepository;
import infrastructure.sample.persistence.table.SampleTable;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
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
    public Pagination<Sample> findAll(SampleSearchQuery query) {
        return null;
    }

    @Override
    public void deleteById(UUID id) {

    }
}

package domain.sample;

import domain.pagination.Pagination;
import java.util.Optional;
import java.util.UUID;

public interface SampleGateway {
    Sample save(Sample sample);

    Optional<Sample> findById(UUID id);

    Pagination<Sample> findAll(SampleSearchQuery query);

    void deleteById(UUID id);
}

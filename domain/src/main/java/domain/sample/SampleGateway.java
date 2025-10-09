package domain.sample;

import java.util.Optional;
import java.util.UUID;

public interface SampleGateway {
    Sample save(Sample sample);

    Optional<Sample> findById(UUID id);

    void deleteById(UUID id);
}

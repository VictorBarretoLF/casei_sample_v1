package application.sample.output;

import domain.sample.Sample;
import java.time.Instant;
import java.util.UUID;

public record SampleOutput(UUID id, String name, Instant createdAt, Instant updatedAt) {
    public static SampleOutput from(final Sample sample) {
        return new SampleOutput(
                sample.getId(),
                sample.getName(),
                sample.getCreatedAt(),
                sample.getUpdatedAt()
        );
    }
}

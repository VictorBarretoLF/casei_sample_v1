package infrastructure.sample.api.response;

import application.sample.dto.output.SampleOutput;
import java.time.Instant;
import java.util.UUID;

public record SampleResponse(
        UUID id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {
    public static SampleResponse fromOutput(SampleOutput output) {
        return new SampleResponse(
                output.id(),
                output.name(),
                output.createdAt(),
                output.updatedAt()
        );
    }
}

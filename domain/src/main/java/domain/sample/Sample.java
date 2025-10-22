package domain.sample;

import java.time.Instant;
import java.util.UUID;

public class Sample {
    private final UUID id;
    private final String name;
    private final Instant createdAt;
    private final Instant updatedAt;

    public Sample(UUID id, String name, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Sample with(String name) {
        return new Sample(this.id, name, this.createdAt, this.updatedAt);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}

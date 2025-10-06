package domain.sample;

import java.time.Instant;

public class Sample {
    private final String id;
    private final String name;
    private final Instant createdAt;
    private final Instant updatedAt;

    public Sample(String id, String name, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public String getId() {
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

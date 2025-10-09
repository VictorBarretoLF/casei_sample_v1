package infrastructure.sample.persistence.table;

import domain.sample.Sample;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sample")
public class SampleTable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Instant updatedAt;

    public static SampleTable fromSample(Sample sample) {
        return new SampleTable(
                sample.getId(),
                sample.getName(),
                sample.getCreatedAt(),
                sample.getUpdatedAt()
        );
    }

    public Sample toDomain() {
        return new Sample(
                this.id,
                this.name,
                this.createdAt,
                this.updatedAt
        );
    }
}

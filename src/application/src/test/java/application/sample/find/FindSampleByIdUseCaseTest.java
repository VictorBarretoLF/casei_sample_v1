package application.sample.find;

import application.UseCaseTest;
import application.sample.dto.output.SampleOutput;
import domain.sample.Sample;
import domain.sample.SampleGateway;
import domain.sample.exceptions.SampleNotFoundException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class FindSampleByIdUseCaseTest extends UseCaseTest {
    @Mock
    private SampleGateway sampleGateway;

    @InjectMocks
    private FindSampleByIdUseCase findSampleByIdUseCase;

    @Test
    void shouldFindSampleByIdSuccessfully() {
        // Given
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample sample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(sample));

        // When
        SampleOutput output = findSampleByIdUseCase.execute(sampleId);

        // Then
        assertNotNull(output);
        assertEquals(sample.getId(), output.id());
        assertEquals(sample.getName(), output.name());
        assertEquals(sample.getCreatedAt(), output.createdAt());
        assertEquals(sample.getUpdatedAt(), output.updatedAt());

        verify(sampleGateway, times(1)).findById(sampleId);
    }

    @Test
    void shouldThrowExceptionWhenSampleNotFound() {
        // Given
        UUID sampleId = UUID.randomUUID();

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.empty());

        // When & Then
        SampleNotFoundException exception = assertThrows(
                SampleNotFoundException.class,
                () -> findSampleByIdUseCase.execute(sampleId)
        );

        assertNotNull(exception.getMessage());
        verify(sampleGateway, times(1)).findById(sampleId);
    }

    @Test
    void shouldReturnCorrectSampleOutput() {
        // Given
        UUID sampleId = UUID.randomUUID();
        Instant createdAt = Instant.now().minusSeconds(3600);
        Instant updatedAt = Instant.now();
        Sample sample = new Sample(sampleId, "Sample Name", createdAt, updatedAt);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(sample));

        // When
        SampleOutput output = findSampleByIdUseCase.execute(sampleId);

        // Then
        assertNotNull(output);
        assertEquals(sampleId, output.id());
        assertEquals("Sample Name", output.name());
        assertEquals(createdAt, output.createdAt());
        assertEquals(updatedAt, output.updatedAt());
    }

    @Test
    void shouldCallGatewayWithCorrectId() {
        // Given
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample sample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(sample));

        // When
        findSampleByIdUseCase.execute(sampleId);

        // Then
        verify(sampleGateway).findById(sampleId);
    }

    @Test
    void shouldMapSampleToOutputCorrectly() {
        // Given
        UUID sampleId = UUID.randomUUID();
        Instant createdAt = Instant.parse("2025-01-01T10:00:00Z");
        Instant updatedAt = Instant.parse("2025-01-02T15:30:00Z");
        Sample sample = new Sample(sampleId, "Mapped Sample", createdAt, updatedAt);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(sample));

        // When
        SampleOutput output = findSampleByIdUseCase.execute(sampleId);

        // Then
        assertEquals(sampleId, output.id());
        assertEquals("Mapped Sample", output.name());
        assertEquals(createdAt, output.createdAt());
        assertEquals(updatedAt, output.updatedAt());
    }
}
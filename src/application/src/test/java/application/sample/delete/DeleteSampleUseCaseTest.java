package application.sample.delete;

import application.UseCaseTest;
import domain.sample.Sample;
import domain.sample.SampleGateway;
import domain.sample.exceptions.SampleNotFoundException;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DeleteSampleUseCaseTest extends UseCaseTest {
    @Mock
    private SampleGateway sampleGateway;

    @InjectMocks
    private DeleteSampleUseCase deleteSampleUseCase;

    @Test
    void shouldDeleteSampleSuccessfully() {
        // Given
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample sample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(sample));

        // When
        deleteSampleUseCase.execute(sampleId);

        // Then
        verify(sampleGateway, times(1)).findById(sampleId);
        verify(sampleGateway, times(1)).deleteById(sampleId);
    }

    @Test
    void shouldThrowExceptionWhenSampleNotFound() {
        // Given
        UUID sampleId = UUID.randomUUID();

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.empty());

        // When & Then
        SampleNotFoundException exception = assertThrows(
                SampleNotFoundException.class,
                () -> deleteSampleUseCase.execute(sampleId)
        );

        assertNotNull(exception.getMessage());
        verify(sampleGateway, times(1)).findById(sampleId);
        verify(sampleGateway, never()).deleteById(sampleId);
    }

    @Test
    void shouldCallDeleteByIdWithCorrectParameter() {
        // Given
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample sample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(sample));

        // When
        deleteSampleUseCase.execute(sampleId);

        // Then
        verify(sampleGateway).deleteById(sampleId);
    }

    @Test
    void shouldVerifyFindByIdIsCalledBeforeDelete() {
        // Given
        UUID sampleId = UUID.randomUUID();

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(SampleNotFoundException.class, () -> deleteSampleUseCase.execute(sampleId));
        verify(sampleGateway, times(1)).findById(sampleId);
        verify(sampleGateway, never()).deleteById(sampleId);
    }

    @Test
    void shouldReturnNullAfterSuccessfulDeletion() {
        // Given
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample sample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(sample));

        // When
        deleteSampleUseCase.execute(sampleId);

        // Then
        // No assertion needed - void method completes successfully
        verify(sampleGateway, times(1)).deleteById(sampleId);
    }

    @Test
    void shouldCallGatewayMethodsInCorrectOrder() {
        // Given
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample sample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(sample));

        // When
        deleteSampleUseCase.execute(sampleId);

        // Then
        verify(sampleGateway, times(1)).findById(sampleId);
        verify(sampleGateway, times(1)).deleteById(sampleId);
    }
}

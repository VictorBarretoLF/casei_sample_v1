package application.sample.update;

import application.UseCaseTest;
import application.sample.dto.input.UpdateSampleInput;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UpdateSampleUseCaseTest extends UseCaseTest {
    @Mock
    private SampleGateway sampleGateway;

    @InjectMocks
    private UpdateSampleUseCase updateSampleUseCase;

    @Test
    void shouldUpdateSampleSuccessfully() {
        // Given
        UUID sampleId = UUID.randomUUID();
        UpdateSampleInput input = new UpdateSampleInput(sampleId, "Updated Sample");
        Instant now = Instant.now();
        Sample existingSample = new Sample(sampleId, "Old Sample", now, now);
        Sample updatedSample = new Sample(sampleId, "Updated Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(existingSample));
        when(sampleGateway.save(any(Sample.class))).thenReturn(updatedSample);

        // When
        SampleOutput output = updateSampleUseCase.execute(input);

        // Then
        assertNotNull(output);
        assertEquals(updatedSample.getId(), output.id());
        assertEquals(updatedSample.getName(), output.name());
        assertEquals(updatedSample.getCreatedAt(), output.createdAt());
        assertEquals(updatedSample.getUpdatedAt(), output.updatedAt());

        verify(sampleGateway, times(1)).findById(sampleId);
        verify(sampleGateway, times(1)).save(any(Sample.class));
    }

    @Test
    void shouldThrowExceptionWhenSampleNotFound() {
        // Given
        UUID sampleId = UUID.randomUUID();
        UpdateSampleInput input = new UpdateSampleInput(sampleId, "Updated Sample");

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.empty());

        // When & Then
        SampleNotFoundException exception = assertThrows(
                SampleNotFoundException.class,
                () -> updateSampleUseCase.execute(input)
        );

        assertNotNull(exception.getMessage());
        verify(sampleGateway, times(1)).findById(sampleId);
        verify(sampleGateway, never()).save(any(Sample.class));
    }

    @Test
    void shouldCallGatewayWithCorrectSampleData() {
        // Given
        UUID sampleId = UUID.randomUUID();
        UpdateSampleInput input = new UpdateSampleInput(sampleId, "Updated Sample");
        Instant now = Instant.now();
        Sample existingSample = new Sample(sampleId, "Old Sample", now, now);
        Sample updatedSample = new Sample(sampleId, "Updated Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(existingSample));
        when(sampleGateway.save(any(Sample.class))).thenReturn(updatedSample);

        // When
        updateSampleUseCase.execute(input);

        // Then
        verify(sampleGateway).save(argThat(sample ->
                sample.getId().equals(sampleId) &&
                        sample.getName().equals("Updated Sample") &&
                        sample.getCreatedAt() == null &&
                        sample.getUpdatedAt() == null
        ));
    }

    @Test
    void shouldReturnOutputWithUpdatedSampleData() {
        // Given
        UUID sampleId = UUID.randomUUID();
        UpdateSampleInput input = new UpdateSampleInput(sampleId, "Updated Sample");
        Instant now = Instant.now();
        Sample existingSample = new Sample(sampleId, "Old Sample", now, now);
        Sample updatedSample = new Sample(sampleId, "Updated Sample", now, now);

        when(sampleGateway.findById(sampleId)).thenReturn(Optional.of(existingSample));
        when(sampleGateway.save(any(Sample.class))).thenReturn(updatedSample);

        // When
        SampleOutput output = updateSampleUseCase.execute(input);

        // Then
        assertNotNull(output);
        assertEquals(sampleId, output.id());
        assertEquals("Updated Sample", output.name());
        assertNotNull(output.createdAt());
        assertNotNull(output.updatedAt());
    }
}

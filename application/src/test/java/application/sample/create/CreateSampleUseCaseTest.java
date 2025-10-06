package application.sample.create;

import application.UseCaseTest;
import application.sample.dto.input.CreateSampleInput;
import application.sample.dto.output.SampleOutput;
import domain.sample.Sample;
import domain.sample.SampleGateway;
import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CreateSampleUseCaseTest extends UseCaseTest {
    @Mock
    private SampleGateway sampleGateway;

    @InjectMocks
    private CreateSampleUseCase createSampleUseCase;

    @Test
    void shouldCreateSampleSuccessfully() {
        // Given
        CreateSampleInput input = new CreateSampleInput("Test Sample");
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample savedSample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.save(any(Sample.class))).thenReturn(savedSample);

        // When
        SampleOutput output = createSampleUseCase.execute(input);

        // Then
        assertNotNull(output);
        assertEquals(savedSample.getId(), output.id());
        assertEquals(savedSample.getName(), output.name());
        assertEquals(savedSample.getCreatedAt(), output.createdAt());
        assertEquals(savedSample.getUpdatedAt(), output.updatedAt());

        verify(sampleGateway, times(1)).save(any(Sample.class));
    }

    @Test
    void shouldCallGatewayWithCorrectSampleData() {
        // Given
        CreateSampleInput input = new CreateSampleInput("Test Sample");
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample savedSample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.save(any(Sample.class))).thenReturn(savedSample);

        // When
        createSampleUseCase.execute(input);

        // Then
        verify(sampleGateway).save(argThat(sample ->
                sample.getId() == null &&
                        sample.getName().equals("Test Sample") &&
                        sample.getCreatedAt() == null &&
                        sample.getUpdatedAt() == null
        ));
    }

    @Test
    void shouldReturnOutputWithSavedSampleData() {
        // Given
        CreateSampleInput input = new CreateSampleInput("Test Sample");
        UUID sampleId = UUID.randomUUID();
        Instant now = Instant.now();
        Sample savedSample = new Sample(sampleId, "Test Sample", now, now);

        when(sampleGateway.save(any(Sample.class))).thenReturn(savedSample);

        // When
        SampleOutput output = createSampleUseCase.execute(input);

        // Then
        assertNotNull(output);
        assertEquals("Test Sample", output.name());
        assertNotNull(output.id());
        assertNotNull(output.createdAt());
        assertNotNull(output.updatedAt());
    }

    @Test
    void shouldPropagateExceptionWhenGatewayFails() {
        // Given
        CreateSampleInput input = new CreateSampleInput("Test Sample");

        when(sampleGateway.save(any(Sample.class)))
                .thenThrow(new RuntimeException("Database error"));

        // When & Then
        assertThrows(RuntimeException.class, () -> createSampleUseCase.execute(input));
        verify(sampleGateway, times(1)).save(any(Sample.class));
    }
}
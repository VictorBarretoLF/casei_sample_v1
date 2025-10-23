package infrastructure.sample.api.request;

import application.sample.dto.input.CreateSampleInput;
import application.sample.dto.input.UpdateSampleInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record SampleRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
        String name
) {
    public CreateSampleInput toSampleInput() {
        return new CreateSampleInput(this.name);
    }

    public UpdateSampleInput toUpdateSample(UUID id) {
        return new UpdateSampleInput(id, this.name);
    }
}
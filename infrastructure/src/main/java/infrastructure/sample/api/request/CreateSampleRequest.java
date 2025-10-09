package infrastructure.sample.api.request;

import application.sample.dto.input.CreateSampleInput;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateSampleRequest(
        @NotBlank(message = "Name is required")
        @Size(min = 1, max = 255, message = "Name must be between 1 and 255 characters")
        String name
) {
    public CreateSampleInput toSampleInput() {
        return new CreateSampleInput(this.name);
    }
}
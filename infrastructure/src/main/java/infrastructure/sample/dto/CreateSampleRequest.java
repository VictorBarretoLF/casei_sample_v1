package infrastructure.sample.dto;

import application.sample.dto.input.CreateSampleInput;

public record CreateSampleRequest(String name) {
    public CreateSampleInput toSampleInput() {
        return new CreateSampleInput(this.name);
    }
}

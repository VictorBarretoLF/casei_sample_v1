package infrastructure.sample.api.request;

import application.sample.dto.input.CreateSampleInput;

public record CreateSampleRequest(String name) {
    public CreateSampleInput toSampleInput() {
        return new CreateSampleInput(this.name);
    }
}

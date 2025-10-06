package application.sample.dto.input;

import java.util.UUID;

public record UpdateSampleInput(UUID id, String name) {
}

package application.sample.delete;

import application.UseCase;
import domain.sample.SampleGateway;
import domain.sample.exceptions.SampleNotFoundException;
import java.util.UUID;

public class DeleteSampleUseCase extends UseCase<UUID, Void> {
    private final SampleGateway sampleGateway;

    public DeleteSampleUseCase(final SampleGateway sampleGateway) {
        this.sampleGateway = sampleGateway;
    }

    @Override
    public Void execute(UUID id) {
        sampleGateway.findById(id)
                .orElseThrow(() -> new SampleNotFoundException(id));

        sampleGateway.deleteById(id);

        return null;
    }
}
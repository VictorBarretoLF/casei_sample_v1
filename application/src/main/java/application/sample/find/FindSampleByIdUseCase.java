package application.sample.find;

import application.UseCase;
import domain.sample.exceptions.SampleNotFoundException;
import domain.sample.SampleGateway;
import java.util.UUID;

public class FindSampleByIdUseCase extends UseCase<UUID, Object> {
    private final SampleGateway sampleGateway;

    public FindSampleByIdUseCase(final SampleGateway sampleGateway) {
        this.sampleGateway = sampleGateway;
    }

    @Override
    public Object execute(UUID id) {
        return sampleGateway.findById(id)
                .orElseThrow(() -> new SampleNotFoundException(id));
    }
}

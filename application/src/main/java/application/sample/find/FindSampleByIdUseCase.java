package application.sample.find;

import application.UseCase;
import application.sample.output.SampleOutput;
import domain.sample.exceptions.SampleNotFoundException;
import domain.sample.SampleGateway;
import java.util.UUID;

public class FindSampleByIdUseCase extends UseCase<UUID, SampleOutput> {
    private final SampleGateway sampleGateway;

    public FindSampleByIdUseCase(final SampleGateway sampleGateway) {
        this.sampleGateway = sampleGateway;
    }

    @Override
    public SampleOutput execute(UUID id) {
        return sampleGateway.findById(id)
                .map(SampleOutput::from)
                .orElseThrow(() -> new SampleNotFoundException(id));
    }
}

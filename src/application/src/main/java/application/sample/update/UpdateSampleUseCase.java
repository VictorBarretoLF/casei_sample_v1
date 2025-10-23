package application.sample.update;

import application.UseCase;
import application.sample.dto.input.UpdateSampleInput;
import application.sample.dto.output.SampleOutput;
import domain.sample.Sample;
import domain.sample.SampleGateway;
import domain.sample.exceptions.SampleNotFoundException;

public class UpdateSampleUseCase extends UseCase<UpdateSampleInput, SampleOutput> {
    private final SampleGateway sampleGateway;

    public UpdateSampleUseCase(final SampleGateway sampleGateway) {
        this.sampleGateway = sampleGateway;
    }

    @Override
    public SampleOutput execute(UpdateSampleInput input) {
        final var sample = sampleGateway.findById(input.id())
                .orElseThrow(() -> new SampleNotFoundException(input.id()));

        final var updatedSample = sample.with(input.name());
        final var savedSample = sampleGateway.save(updatedSample);

        return SampleOutput.from(savedSample);
    }
}
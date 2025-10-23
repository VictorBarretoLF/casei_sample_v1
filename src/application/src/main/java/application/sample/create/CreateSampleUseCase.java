package application.sample.create;

import application.UseCase;
import application.sample.dto.input.CreateSampleInput;
import application.sample.dto.output.SampleOutput;
import domain.sample.Sample;
import domain.sample.SampleGateway;

public class CreateSampleUseCase extends UseCase<CreateSampleInput, SampleOutput> {
    private final SampleGateway sampleGateway;

    public CreateSampleUseCase(final SampleGateway sampleGateway) {
        this.sampleGateway = sampleGateway;
    }

    @Override
    public SampleOutput execute(CreateSampleInput input) {
        final Sample sample = new Sample(null, input.name(), null, null);
        final Sample savedSample = sampleGateway.save(sample);
        return SampleOutput.from(savedSample);
    }
}

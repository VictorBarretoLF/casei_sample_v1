package application.sample.update;

import application.UseCase;
import application.sample.input.UpdateSampleInput;
import application.sample.output.SampleOutput;
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
        sampleGateway.findById(input.id())
                .orElseThrow(() -> new SampleNotFoundException(input.id()));

        final Sample updatedSample = new Sample(
                input.id(),
                input.name(),
                null,
                null
        );

        final Sample savedSample = sampleGateway.save(updatedSample);

        return SampleOutput.from(savedSample);
    }
}
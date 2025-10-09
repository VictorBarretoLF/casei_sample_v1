package application.sample.findAll;

import domain.sample.SampleGateway;

public class FindAllSamplesUseCase {
    private final SampleGateway sampleGateway;

    public FindAllSamplesUseCase(SampleGateway sampleGateway) {
        this.sampleGateway = sampleGateway;
    }
}

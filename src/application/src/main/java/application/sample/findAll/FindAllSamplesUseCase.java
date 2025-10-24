package application.sample.findAll;

import application.UseCase;
import application.sample.dto.output.SampleOutput;
import domain.pagination.Pagination;
import domain.query.PageFilter;
import domain.sample.SampleGateway;

public class FindAllSamplesUseCase extends UseCase<PageFilter, Pagination<SampleOutput>> {
    private final SampleGateway sampleGateway;

    public FindAllSamplesUseCase(final SampleGateway sampleGateway) {
        this.sampleGateway = sampleGateway;
    }

    @Override
    public Pagination<SampleOutput> execute(final PageFilter pageFilter) {
        final var pagination = sampleGateway.findAll(pageFilter);
        return pagination.map(SampleOutput::from);
    }
}

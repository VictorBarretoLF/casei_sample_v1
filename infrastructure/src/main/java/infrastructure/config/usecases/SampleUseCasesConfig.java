package infrastructure.config.usecases;

import application.sample.create.CreateSampleUseCase;
import application.sample.find.FindSampleByIdUseCase;
import application.sample.update.UpdateSampleUseCase;
import domain.sample.SampleGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleUseCasesConfig {
    @Bean
    public CreateSampleUseCase createSampleUseCase(SampleGateway sampleGateway) {
        return new CreateSampleUseCase(sampleGateway);
    }

    @Bean
    public FindSampleByIdUseCase findSampleByIdUseCase(SampleGateway sampleGateway) {
        return new FindSampleByIdUseCase(sampleGateway);
    }

    @Bean
    public UpdateSampleUseCase updateSampleUseCase(SampleGateway sampleGateway) {
        return new UpdateSampleUseCase(sampleGateway);
    }
}

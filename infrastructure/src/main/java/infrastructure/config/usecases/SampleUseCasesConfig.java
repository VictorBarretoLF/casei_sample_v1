package infrastructure.config.usecases;

import application.sample.create.CreateSampleUseCase;
import domain.sample.SampleGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleUseCasesConfig {
    @Bean
    public CreateSampleUseCase createSampleUseCase(SampleGateway sampleGateway) {
        return new CreateSampleUseCase(sampleGateway);
    }
}

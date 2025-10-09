package infrastructure.sample.api.controller;

import application.sample.create.CreateSampleUseCase;
import infrastructure.sample.api.request.CreateSampleRequest;
import infrastructure.sample.api.response.SampleResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
@AllArgsConstructor
public class SampleController {
    private final CreateSampleUseCase createSampleUseCase;

    @GetMapping
    public ResponseEntity<SampleResponse> getSample(@RequestBody CreateSampleRequest request) {
        final var output = createSampleUseCase.execute(request.toSampleInput());
        return ResponseEntity.status(HttpStatus.CREATED).body(SampleResponse.fromOutput(output));
    }
}

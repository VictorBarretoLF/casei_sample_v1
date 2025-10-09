package infrastructure.sample.api.controller;

import infrastructure.sample.dto.CreateSampleRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sample")
public class SampleController {
    @GetMapping
    public ResponseEntity<CreateSampleRequest> getSample(@RequestBody CreateSampleRequest request) {
        System.out.println(request.toSampleInput());
        return ResponseEntity.ok(request);
    }
}

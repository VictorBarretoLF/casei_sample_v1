package domain.sample.exceptions;

import domain.exceptions.DomainException;
import domain.exceptions.ErrorMessages;
import java.util.UUID;

public class SampleNotFoundException extends DomainException {
    public SampleNotFoundException(UUID id) {
        super(ErrorMessages.SAMPLE_NOT_FOUND.formatted(id));
    }
}

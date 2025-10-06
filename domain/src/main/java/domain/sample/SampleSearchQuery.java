package domain.sample;

public record SampleSearchQuery(int page, int perPage, String terms, String sort, String direction) {
}

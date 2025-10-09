package domain.pagination;

import java.util.List;
import java.util.function.Function;

public record PageFilter<T>(
        Metadata metadata,
        List<T> items
) {
    public PageFilter(int currentPage, int perPage , List<T> items) {
        this(new Metadata(currentPage, perPage), items);
    }

    public <R> PageFilter<R> map(final Function<T, R> mapper) {
        final List<R> aNewList = this.items.stream()
                .map(mapper)
                .toList();

        return new PageFilter<>(metadata(), aNewList);
    }
}

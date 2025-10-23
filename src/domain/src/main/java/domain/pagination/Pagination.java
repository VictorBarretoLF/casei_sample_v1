package domain.pagination;

import java.util.List;
import java.util.function.Function;

public record Pagination<T>(
        int currentPage,
        int size,
        long totalElements,
        int totalPages,
        List<T> content,
        boolean isFirstPage,
        boolean isLastPage
) {
    public <R> Pagination<R> map(final Function<T, R> mapper) {
        final List<R> mappedContent = this.content.stream()
                .map(mapper)
                .toList();

        return new Pagination<>(
                this.currentPage,
                this.size,
                this.totalElements,
                this.totalPages,
                mappedContent,
                this.isFirstPage,
                this.isLastPage
        );
    }
}

package domain.pagination;

import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaginationTest {
    @Test
    void shouldCreatePaginationWithMetadataConstructor() {
        // Given
        Metadata metadata = new Metadata(1, 10, 100L);
        List<String> data = List.of("item1", "item2", "item3");

        // When
        Pagination<String> pagination = new Pagination<>(metadata, data);

        // Then
        assertEquals(metadata, pagination.meta());
        assertEquals(data, pagination.data());
    }

    @Test
    void shouldCreatePaginationWithConvenienceConstructor() {
        // Given
        List<String> data = List.of("item1", "item2", "item3");

        // When
        Pagination<String> pagination = new Pagination<>(1, 10, 100L, data);

        // Then
        assertEquals(1, pagination.meta().currentPage());
        assertEquals(10, pagination.meta().perPage());
        assertEquals(100L, pagination.meta().total());
        assertEquals(data, pagination.data());
    }

    @Test
    void shouldHandleEmptyDataList() {
        // Given
        List<String> emptyData = List.of();

        // When
        Pagination<String> pagination = new Pagination<>(1, 10, 0L, emptyData);

        // Then
        assertEquals(1, pagination.meta().currentPage());
        assertEquals(10, pagination.meta().perPage());
        assertEquals(0L, pagination.meta().total());
        assertTrue(pagination.data().isEmpty());
    }

    @Test
    void shouldMapDataToDifferentType() {
        // Given
        List<String> stringData = List.of("1", "2", "3");
        Pagination<String> stringPagination = new Pagination<>(1, 10, 100L, stringData);
        Function<String, Integer> mapper = Integer::parseInt;

        // When
        Pagination<Integer> intPagination = stringPagination.map(mapper);

        // Then
        assertEquals(stringPagination.meta(), intPagination.meta());
        assertEquals(List.of(1, 2, 3), intPagination.data());
    }
}
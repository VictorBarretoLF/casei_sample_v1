package domain.pagination;


import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PaginationTest {

    @Test
    void shouldCreatePaginationWithCorrectValues() {
        // Given
        List<String> content = Arrays.asList("item1", "item2", "item3");

        // When
        Pagination<String> pagination = new Pagination<>(
                0,
                10,
                30L,
                3,
                content,
                true,
                false
        );

        // Then
        assertEquals(0, pagination.currentPage());
        assertEquals(10, pagination.size());
        assertEquals(30L, pagination.totalElements());
        assertEquals(3, pagination.totalPages());
        assertEquals(3, pagination.content().size());
        assertTrue(pagination.isFirstPage());
        assertFalse(pagination.isLastPage());
    }

    @Test
    void shouldMapPaginationContentCorrectly() {
        // Given
        List<Integer> content = Arrays.asList(1, 2, 3, 4, 5);
        Pagination<Integer> pagination = new Pagination<>(
                0,
                5,
                5L,
                1,
                content,
                true,
                true
        );

        // When
        Pagination<String> mappedPagination = pagination.map(num -> "Number: " + num);

        // Then
        assertEquals(5, mappedPagination.content().size());
        assertEquals("Number: 1", mappedPagination.content().get(0));
        assertEquals("Number: 5", mappedPagination.content().get(4));
        assertEquals(pagination.currentPage(), mappedPagination.currentPage());
        assertEquals(pagination.size(), mappedPagination.size());
        assertEquals(pagination.totalElements(), mappedPagination.totalElements());
        assertEquals(pagination.totalPages(), mappedPagination.totalPages());
        assertEquals(pagination.isFirstPage(), mappedPagination.isFirstPage());
        assertEquals(pagination.isLastPage(), mappedPagination.isLastPage());
    }

    @Test
    void shouldMapEmptyPaginationCorrectly() {
        // Given
        Pagination<Integer> emptyPagination = new Pagination<>(
                0,
                10,
                0L,
                0,
                List.of(),
                true,
                true
        );

        // When
        Pagination<String> mappedPagination = emptyPagination.map(num -> "Number: " + num);

        // Then
        assertTrue(mappedPagination.content().isEmpty());
        assertEquals(0L, mappedPagination.totalElements());
        assertEquals(0, mappedPagination.totalPages());
    }

    @Test
    void shouldIndicateFirstPageCorrectly() {
        // Given & When
        Pagination<String> firstPage = new Pagination<>(
                0,
                10,
                30L,
                3,
                List.of("item1"),
                true,
                false
        );

        Pagination<String> middlePage = new Pagination<>(
                1,
                10,
                30L,
                3,
                List.of("item2"),
                false,
                false
        );

        // Then
        assertTrue(firstPage.isFirstPage());
        assertFalse(middlePage.isFirstPage());
    }

    @Test
    void shouldIndicateLastPageCorrectly() {
        // Given & When
        Pagination<String> lastPage = new Pagination<>(
                2,
                10,
                30L,
                3,
                List.of("item3"),
                false,
                true
        );

        Pagination<String> middlePage = new Pagination<>(
                1,
                10,
                30L,
                3,
                List.of("item2"),
                false,
                false
        );

        // Then
        assertTrue(lastPage.isLastPage());
        assertFalse(middlePage.isLastPage());
    }

    @Test
    void shouldHandleSinglePageCorrectly() {
        // Given & When
        Pagination<String> singlePage = new Pagination<>(
                0,
                10,
                5L,
                1,
                Arrays.asList("item1", "item2", "item3", "item4", "item5"),
                true,
                true
        );

        // Then
        assertTrue(singlePage.isFirstPage());
        assertTrue(singlePage.isLastPage());
        assertEquals(1, singlePage.totalPages());
        assertEquals(5L, singlePage.totalElements());
    }

    @Test
    void shouldCalculateTotalPagesCorrectly() {
        // Given & When
        Pagination<String> pagination1 = new Pagination<>(
                0,
                10,
                25L,
                3,
                List.of(),
                true,
                false
        );

        Pagination<String> pagination2 = new Pagination<>(
                0,
                10,
                100L,
                10,
                List.of(),
                true,
                false
        );

        // Then
        assertEquals(3, pagination1.totalPages());
        assertEquals(10, pagination2.totalPages());
    }

    @Test
    void shouldPreserveMetadataWhenMapping() {
        // Given
        Pagination<Integer> original = new Pagination<>(
                2,
                20,
                100L,
                5,
                Arrays.asList(41, 42, 43),
                false,
                false
        );

        // When
        Pagination<String> mapped = original.map(Object::toString);

        // Then
        assertEquals(original.currentPage(), mapped.currentPage());
        assertEquals(original.size(), mapped.size());
        assertEquals(original.totalElements(), mapped.totalElements());
        assertEquals(original.totalPages(), mapped.totalPages());
        assertEquals(original.isFirstPage(), mapped.isFirstPage());
        assertEquals(original.isLastPage(), mapped.isLastPage());
    }

    @Test
    void shouldMapComplexObjectsCorrectly() {
        // Given
        record Person(String name, int age) {}
        List<Person> people = Arrays.asList(
                new Person("Alice", 30),
                new Person("Bob", 25)
        );

        Pagination<Person> personPagination = new Pagination<>(
                0,
                10,
                2L,
                1,
                people,
                true,
                true
        );

        // When
        Pagination<String> namePagination = personPagination.map(Person::name);

        // Then
        assertEquals(2, namePagination.content().size());
        assertEquals("Alice", namePagination.content().get(0));
        assertEquals("Bob", namePagination.content().get(1));
    }
}
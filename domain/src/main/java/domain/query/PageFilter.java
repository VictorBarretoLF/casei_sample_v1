package domain.query;

public class PageFilter {
    private Integer page;
    // change to size when possible
    private Integer perPage;

    public PageFilter() {
        this.page = 0;
        this.perPage = 20;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    // Alias for setPerPage
    public void setSize(Integer size) {
        this.perPage = size;
    }
}

//public record PageFilter<T>(
//        Metadata metadata,
//        List<T> items
//) {
//    public PageFilter(int currentPage, int perPage , List<T> items) {
//        this(new Metadata(currentPage, perPage), items);
//    }
//
//    public <R> PageFilter<R> map(final Function<T, R> mapper) {
//        final List<R> aNewList = this.items.stream()
//                .map(mapper)
//                .toList();
//
//        return new PageFilter<>(metadata(), aNewList);
//    }
//}

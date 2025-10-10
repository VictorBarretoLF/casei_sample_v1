package domain.sample;

public class SampleSearchQuery {
    private Integer page;
    private Integer perPage;

    public SampleSearchQuery() {
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

    public void setSize(Integer size) {
        this.perPage = size;
    }
}
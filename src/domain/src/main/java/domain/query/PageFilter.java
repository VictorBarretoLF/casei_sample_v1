package domain.query;

import java.util.ArrayList;
import java.util.List;

public class PageFilter {
    private int page;
    private int perPage;
    private List<Order> sorts;

    public PageFilter() {
        this.page = 0;
        this.perPage = 20;
        this.sorts = new ArrayList<>();
    }

    public PageFilter(int page, int perPage, List<Order> sorts) {
        this.page = page;
        this.perPage = perPage;
        this.sorts = sorts;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    // Alias for setPerPage
    public void setSize(int size) {
        this.perPage = size;
    }

    public List<Order> getSort() {
        return sorts;
    }

    public void setSort(List<Order> sorts) {
        this.sorts = sorts;
    }

    public static class Order {
        private String property;
        private Direction direction;

        public Order(String property, Direction direction) {
            this.property = property;
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }
    }

    public enum Direction {
        ASC, DESC;

        public static Direction fromString(String value) {
            for (Direction direction : Direction.values()) {
                if (direction.name().equalsIgnoreCase(value)) {
                    return direction;
                }
            }
            throw new IllegalArgumentException("Invalid direction: " + value);
        }

        @Override
        public String toString() {
            return this.name();
        }
    }

}

package infrastructure.mapper;

import domain.query.PageFilter;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageFilterConverter {
    public static PageFilter fromPageable(Pageable pageable) {
        List<PageFilter.Order> sorts = pageable.getSort()
                .stream()
                .map(sort -> new PageFilter.Order(
                        sort.getProperty(),
                        PageFilter.Direction.fromString(sort.getDirection().name())
                ))
                .toList();

        return new PageFilter(pageable.getPageNumber(), pageable.getPageSize(), sorts);
    }

    public static PageRequest toPageRequest(PageFilter pageFilter) {
        if (pageFilter.getSort() == null || pageFilter.getSort().isEmpty()) {
            return PageRequest.of(pageFilter.getPage(), pageFilter.getPerPage());
        }

        List<Sort.Order> sorts = pageFilter.getSort().stream()
                .map(order -> Sort.Order.by(order.getProperty())
                        .with(Sort.Direction.fromString(order.getDirection().name())))
                .toList();

        return PageRequest.of(pageFilter.getPage(), pageFilter.getPerPage(), Sort.by(sorts));
    }
}
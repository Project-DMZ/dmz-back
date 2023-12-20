package com.idenit.global.utils.paging;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;

import lombok.Builder;
import lombok.Getter;

/**
 * packageName    : com.idenit.global.utils
 * fileName       : Paging
 * author         : Jihun Kim
 * date           : 10/18/23
 * description    : 
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 10/18/23        Jihun Kim       최초 생성
 */
@Getter
@Builder
public class Paging {

	private long totalElements;
	private int totalPages;
	private int numberOfElements;
	private boolean first;
	private List<?> content;

	public static Paging of(Page<?> page) {

		List<?> content = page.getContent();
		long totalElements = page.getTotalElements();
		int totalPages = page.getTotalPages();
		int numberOfElements = page.getNumberOfElements();
		boolean first = page.isFirst();

		return Paging.builder()
			.content(content)
			.totalElements(totalElements)
			.totalPages(totalPages)
			.numberOfElements(numberOfElements)
			.first(first)
			.build();
	}

	public static OrderSpecifier<?> getOrder(PathBuilder<?> expression, Sort sort) {
		List<OrderSpecifier<?>> orders = new ArrayList<>();

		sort.stream().forEach(order -> {
			Order direction = order.isAscending() ? Order.ASC : Order.DESC;
			String prop = order.getProperty();

			orders.add(new OrderSpecifier(direction, expression.get(prop)));
		});

		return orders.isEmpty() ? null : orders.get(0);
	}
}

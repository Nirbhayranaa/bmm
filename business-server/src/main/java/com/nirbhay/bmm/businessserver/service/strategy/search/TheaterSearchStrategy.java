package com.nirbhay.bmm.businessserver.service.strategy.search;

import com.nirbhay.bmm.model.bs.SearchCriteria;
import com.nirbhay.bmm.model.bs.Theater;

import java.util.List;

/**
 * @author Nirbhay Rana
 */
public interface TheaterSearchStrategy {
    String apply(SearchCriteria criteria);
}

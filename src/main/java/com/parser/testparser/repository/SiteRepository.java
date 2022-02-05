package com.parser.testparser.repository;

import com.parser.testparser.domain.Site;
import org.springframework.data.repository.CrudRepository;

public interface SiteRepository extends CrudRepository<Site, Long> {
}

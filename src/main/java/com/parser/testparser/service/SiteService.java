package com.parser.testparser.service;

import com.parser.testparser.domain.Site;
import com.parser.testparser.repository.SiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SiteService {
    private SiteRepository repository;

    public Site save(Site site) {
        return repository.save(site);
    }

}

package com.labcomu.edu;

import com.labcomu.edu.client.OrcidGateway;
import com.labcomu.edu.client.OrgGateway;
import com.labcomu.edu.resource.Organization;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;

@Service
@Validated
@RequiredArgsConstructor
public class EduService {
    private final OrgGateway orgGateway;
    private final OrcidGateway orcidGateway;

    @Autowired
    private RetryRegistry registry;

    public Organization getOrganization(String url) {
        Organization organization = orgGateway.getOrganization(url);
        organization.setResearchers(organization.getResearchers().stream().map(researcher -> orcidGateway.getResearcher(researcher.getOrcid())).toList());
        return organization;
    }

    @PostConstruct
    public void postConstruct() {
        registry
                .retry("orcIdSearch")
                .getEventPublisher()
                .onRetry(System.out::println);
    }
}

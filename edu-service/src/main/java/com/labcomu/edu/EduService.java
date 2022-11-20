package com.labcomu.edu;

import com.labcomu.edu.client.OrcidGateway;
import com.labcomu.edu.client.OrgGateway;
import com.labcomu.edu.resource.Organization;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import java.time.Duration;

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

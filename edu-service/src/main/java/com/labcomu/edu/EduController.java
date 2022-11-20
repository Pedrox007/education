package com.labcomu.edu;

import com.labcomu.edu.resource.Organization;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/edu")
@Validated
@RequiredArgsConstructor
public class EduController {
  private final EduService service;

  private final Logger logger = LoggerFactory.getLogger(EduController.class);

  @Retry(name = "orcIdSearch")
  @CircuitBreaker(name = "orgCB", fallbackMethod = "fallback")
  @GetMapping("organization/{url}")
  public Organization getOrganization(@NotNull @PathVariable String url) {
    return service.getOrganization(url);
  }

  public int fallback(Throwable e){
    logger.info("Erro na requisição: " + e);
    return 0;
  }

}

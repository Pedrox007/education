package com.labcomu.edu;

import com.labcomu.edu.resource.Organization;
import com.labcomu.faultinjection.annotation.Delay;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("api/v1/edu")
@Validated
@RequiredArgsConstructor
public class EduController {
  private final EduService service;

  private final Logger logger = LoggerFactory.getLogger(EduController.class);

  @RateLimiter(name = "orgRL", fallbackMethod = "rateFallback")
  @GetMapping("organization/{url}")
  public ResponseEntity<Organization> getOrganization(@NotNull @PathVariable String url) {
    return new ResponseEntity<>(service.getOrganization(url), HttpStatus.OK);
  }

  public ResponseEntity<Object> rateFallback(String url, RequestNotPermitted rnp){
    logger.info("Erro na requisição, falha: " + rnp.getMessage());
    return new ResponseEntity<>("Error" ,HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

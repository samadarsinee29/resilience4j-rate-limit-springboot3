
package com.example.demo.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RateLimitController {

    @GetMapping("/auto")
    @RateLimiter(name = "apiLimiter", fallbackMethod = "rateLimitFallback")
    public ResponseEntity<String> rateLimitedApi() {
        return ResponseEntity.ok("✅ You passed the rate limiter");
    }

    public ResponseEntity<String> rateLimitFallback(RequestNotPermitted ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                             .body("⛔ Too many requests – please try again later");
    }
}

# 🚦 Rate Limiting & Throttling with Resilience4j (Spring Boot 3)

This project demonstrates how to implement API rate limiting and throttling using Resilience4j in a Spring Boot 3 application.

---

## 📌 Key Concepts

- **Rate Limiting:** Restricts how many requests a user/service can make within a given time window.
- **Throttling:** Defines how excess requests are handled—fail fast, delayed, or denied.

---

## ⚙️ Tech Stack

- Spring Boot 3
- Java 17
- Resilience4j
- Spring AOP

---

## 🧩 Project Structure

springboot3-resilience4j-ratelimit/
├── pom.xml
├── README.md
└── src
├── main
│ ├── java
│ │ └── com/example/demo/controller/RateLimitController.java
│ └── resources
│ └── application.yml


---

## 🛠️ Configuration (`application.yml`)

```yaml
resilience4j:
  ratelimiter:
    instances:
      apiLimiter:
        limit-for-period: 5
        limit-refresh-period: 10s
        timeout-duration: 0
5 requests every 10 seconds

Timeout = 0 (fail immediately if rate exceeded)

🚀 Controller Example

@RestController
@RequestMapping("/api")
public class RateLimitController {

    @RateLimiter(name = "apiLimiter", fallbackMethod = "rateLimitFallback")
    @GetMapping("/auto")
    public ResponseEntity<String> rateLimitedApi() {
        return ResponseEntity.ok("✅ You passed the rate limiter");
    }

    public ResponseEntity<String> rateLimitFallback(RequestNotPermitted ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                             .body("⛔ Too many requests – please try again later");
    }
}
🧪 Test Output

curl http://localhost:8080/api/auto
Response (after 5 requests in 10s):


⛔ Too many requests – please try again later
📦 Required Dependencies

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot3</artifactId>
</dependency>
<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-ratelimiter</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

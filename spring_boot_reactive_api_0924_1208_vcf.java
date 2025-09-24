// 代码生成时间: 2025-09-24 12:08:00
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@SpringBootApplication
public class SpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootApplication.class, args);
    }

    // Define a REST controller
    @RestController
    class ReactiveRestController {

        private final WebClient webClient;
        private final List<ServerSentEvent<String>> events = new CopyOnWriteArrayList<>();

        public ReactiveRestController(WebClient.Builder webClientBuilder) {
            this.webClient = webClientBuilder.baseUrl("https://api.example.com").build();
        }

        // GET endpoint that streams data in a reactive way
        @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
        public Flux<ServerSentEvent<String>> stream() {
            return Flux.intervalMillis(1000)
                .map(sequence -> "Event " + sequence)
                .map(Mono.just::<String>from)
                .map(Mono::just)
                .map(ServerSentEvent::<String>builder)
                .map(builder -> builder.event("message").data(builder.build().data()).build());
        }

        // Exception handler method for handling exceptions
        @ExceptionHandler(Exception.class)
        public Mono<String> handleException(Exception ex) {
            return Mono.just("An error occurred: " + ex.getMessage());
        }
    }
}

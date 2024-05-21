package com.springBootReactive.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;


@SpringBootApplication
public class FirstReactiveProjectApplication implements CommandLineRunner {


	//with this we also print in the log, not just at the terminal
	private static final Logger log = LoggerFactory.getLogger(FirstReactiveProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FirstReactiveProjectApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		// this is a publisher that means an observer

		Flux<String> names = Flux.just("Andres", "Pedro", "Diego", "Juan")
						.doOnNext(element -> {

							if (element.isEmpty()) {
								throw new RuntimeException("The name cant be empty");
							}
							System.out.println(element);
						});


		// without this subscription nothing will print
		names.subscribe(log::info,
						error -> log.error(error.getMessage()));
	}
}

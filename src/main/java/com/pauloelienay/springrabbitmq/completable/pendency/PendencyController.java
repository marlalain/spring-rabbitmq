package com.pauloelienay.springrabbitmq.completable.pendency;

import com.pauloelienay.springrabbitmq.completable.task.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("pendencies")
@Slf4j
public class PendencyController {
	private @Autowired
	RabbitTemplate template;

	private Set<Pendency> pendencies = new HashSet<>();

	@PostMapping
	public void create(@RequestBody Pendency pendency) {
		pendencies.add(pendency);
		template.convertAndSend("pendencies", pendency);
	}

	@PostMapping("{id}/task")
	public void create(@PathVariable Long id, @RequestBody Task task) {
		Pendency pendency = get(id);
		pendency.addTask(task);
	}

	@GetMapping("{id}")
	public Pendency get(@PathVariable Long id) {
		AtomicReference<Pendency> pendency = new AtomicReference<>();
		pendencies.parallelStream().forEach( p -> {
			if (Objects.equals(p.id, id)) pendency.set(p);
		});
		return nonNull(pendency.get()) ? pendency.get() : null;
	}

	@RabbitListener(queues = "pendencies")
	public void postMessage(Pendency pendency) {
		log.info(pendency.toString());
	}
}

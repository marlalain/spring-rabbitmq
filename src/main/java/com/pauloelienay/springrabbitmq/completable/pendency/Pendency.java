package com.pauloelienay.springrabbitmq.completable.pendency;

import com.pauloelienay.springrabbitmq.completable.Completable;
import com.pauloelienay.springrabbitmq.completable.task.Task;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
public class Pendency extends Completable implements Serializable {
	public Long id;
	public String title;
	public String description;

	private final Set<Task> tasks = new HashSet<>();

	/**
	 * @return Unmodifiable tasks set
	 */
	public Set<Task> getTasks() {
		return Collections.unmodifiableSet(tasks);
	}

	public void addTask(Task task) {
		tasks.add(task);
	}

	public String toString() {
		return "[Pendency]: " + title + ", " + description;
	}
}

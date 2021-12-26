package com.pauloelienay.springrabbitmq.completable.task;

import com.pauloelienay.springrabbitmq.completable.Completable;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
public class Task extends Completable implements Serializable {
	public String title;
	public String description;
}

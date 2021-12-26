package com.pauloelienay.springrabbitmq.completable;

public class Completable {
	private boolean isDone = false;

	public void complete() {
		isDone = true;
	}
	public boolean status() {
		return isDone;
	}
}

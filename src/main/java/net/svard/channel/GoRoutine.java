package net.svard.channel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.svard.interfaces.Go;

public class GoRoutine implements Go {
	private final ExecutorService pool;
	
	public GoRoutine() {
		pool = Executors.newCachedThreadPool();
	}
	
	public void go(Runnable task) {
		pool.execute(task);
	}

}

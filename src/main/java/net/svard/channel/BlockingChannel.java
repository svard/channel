package net.svard.channel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import net.svard.interfaces.Channel;

public class BlockingChannel<T> implements Channel<T> {
	private final BlockingQueue<T> queue;
	private final AtomicBoolean open = new AtomicBoolean(true);
	private final List<Channel<T>> multiples = new ArrayList<Channel<T>>();
	
	public BlockingChannel() {
		queue = new ArrayBlockingQueue<T>(1);
	}
	
	public BlockingChannel(int buffer) {
		queue = new ArrayBlockingQueue<T>(buffer);
	}

	public void put(T value) {
		try {
			if (open.get()) {
				queue.put(value);
				for(Channel<T> multiple : multiples) {
					multiple.put(value);
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public T take() {
		try {
			if (open.get()) {
				return queue.take();
			} else {
				return null;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public void tap(Channel<T> ch) {
		multiples.add(ch);
	}

	public void untap(Channel<T> ch) {
		multiples.remove(ch);
	}
	
	public void close() {
		open.compareAndSet(true, false);
		multiples.clear();
	}
}

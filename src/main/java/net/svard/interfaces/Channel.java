package net.svard.interfaces;

public interface Channel<T> {
	public void put(T value);
	public T take();
	public void tap(Channel<T> ch);
	public void untap(Channel<T> ch);
	public void close();
}

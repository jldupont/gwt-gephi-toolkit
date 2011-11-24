package _org.gephi.gwt;

public class AtomicInteger {
	
	int value;
	public AtomicInteger() {
		this.value=1;
	}
	public AtomicInteger(int initialValue) {
		this.value=initialValue;
	}
	public int incrementAndGet() {
		this.value++;
		return this.value;
	}
	public int getAndIncrement(){
		int current=value;
		this.value++;
		return current;
	}
	public int get() {
		return value;
	}
	public void set(int newValue) {
		this.value=newValue;
	}
}
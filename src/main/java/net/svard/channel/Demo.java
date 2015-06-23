package net.svard.channel;


public class Demo {

	public static void main(String[] args) {
		final BlockingChannel<Integer> channel = new BlockingChannel<Integer>(3);
		final BlockingChannel<Integer> mult = new BlockingChannel<Integer>(3);
		final GoRoutine producer = new GoRoutine();
		final GoRoutine consumer1 = new GoRoutine();
		final GoRoutine consumer2 = new GoRoutine();
		channel.tap(mult);
		
		consumer1.go(new Runnable() {
			
			public void run() {
				int val = channel.take();
				System.out.println("Took value: " + val);
			}
		});	
		
		consumer2.go(new Runnable() {
			
			public void run() {
				int val = mult.take();
				System.out.println("Took value: " + val);
			}
		});
		
		producer.go(new Runnable() {
			
			public void run() {
				System.out.println("Putting value 42 into channel");
				channel.put(42);
//				try {
//					Thread.sleep(5000);
//					
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}
		});
	}

}

package thread;

public class MyRunnable implements Runnable {
	
	@Override
	public void run(){
		for (int i = 10; i > -1; i--){
			System.out.println(i + " seconds past.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
	}

}

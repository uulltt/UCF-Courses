package thread;

public class threads {
	
	public void setupFrame(){
		
	}

	public static void main(String[] args){
		Thread t = new Thread(new MyRunnable(){
			
			@Override
				public void run(){
					int i = 0;
					while(true){
						System.out.println(i+" seconds passed.");
						i++;
						try {
						Thread.sleep(1000);
						} catch (InterruptedException e){
							e.printStackTrace();
						}
					}
				}
			}
		);
		t.start();
	}
}

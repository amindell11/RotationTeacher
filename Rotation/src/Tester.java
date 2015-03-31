import operation.Timer;


public class Tester {
	public static void main(String[] args){
	Timer timer=new Timer();
	long startTime=timer.start();
	while(1==1){
		System.out.println(timer.getMinuteFormat());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
}

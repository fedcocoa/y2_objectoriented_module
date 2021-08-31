public class TestThread extends Thread {

	private int theValue;

	public TestThread(int aValue) {
		theValue=aValue;
	}

	public void run() {
		Example e = Example.getInstance();
		e.setVal(theValue);
	}
	public static void main(String[] args) throws java.lang.InterruptedException {
		TestThread[] tests=new TestThread[10000];
		for (int i=0; i<10000; i++) { tests[i]=new TestThread(i); }
		for (int i=0; i<10000; i++) { tests[i].start(); }
		for (int i=0; i<10000; i++) { tests[i].join(); }
		Example e = Example.getInstance();
		System.out.println("The Example has value " + e.getVal() + " and has been updated " +
				e.getUpdateCount() + " time(s)");
		}
	}

class Example {
	
	private final static Example myInstance = new Example();
	private static int updateCount=0;
	private static int val=0;

	private static Object syncObj = new Object();

	private Example() {

	}

	public static Example getInstance() {
		return myInstance;
	}

	public void setVal(int aVal) {
		synchronized(syncObj) {
			val=aVal;
			updateCount++;
		}
	}

	public int getVal() {
		return val;
	}

	public int getUpdateCount() {
		return updateCount;
	}
}
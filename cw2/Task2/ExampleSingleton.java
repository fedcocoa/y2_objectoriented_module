public class ExampleSingleton {
    private static final ExampleSingleton mainInstance = new ExampleSingleton();
    private static int accesses = 0;

    private ExampleSingleton() {
        System.out.println("I, the ExampleSingleton, am being created");
    }

    public static ExampleSingleton getInstance() {
        accesses++;
        System.out.println("The sole instance of ExampleSingleton is being retrieved");
        return mainInstance;
    }

    public int accessCount() {
        return accesses;
    }
}
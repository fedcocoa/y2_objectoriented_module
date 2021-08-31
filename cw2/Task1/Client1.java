public class Client1 {
    public static void main(String[] args) {
        Hamster h = new Hamster();
        Pet p = h;
        Vegetarian v = h;
        h.setName("Cookie");
        System.out.println(p.getName() + " eats " + v.food());
    }
}
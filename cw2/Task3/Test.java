public class Test {
  public static void main(String[] theArguments) {
    Book b=new BookAdapter();
    b.setTitleString("Gone with the Wind");
    System.out.println("We have a book with title: " + b.getTitleString());
  }
}

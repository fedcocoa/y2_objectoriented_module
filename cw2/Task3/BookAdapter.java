public class BookAdapter {

    private IncompatibleBook bookReference;

    public BookAdapter() {
        bookReference = new IncompatibleBook();
    }

    public void setTitleString(String aString) {
        bookReference.setTitle(aString);
    }

    public String getTitleString() {
        return bookReference.getTitle();
    }
}
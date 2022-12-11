package decorator;

public class Main {
    public static void main(String[] args) {
        Document document = new SmartDocument("gs://lab12oop_serhii-ivanov/zapovit.png");
       //document = new TimedDocument(document);
        document = new CachedDocument(document);
        System.out.println(document.parse());


    }
}

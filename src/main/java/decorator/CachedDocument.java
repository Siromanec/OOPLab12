package decorator;

import lombok.SneakyThrows;

public class CachedDocument implements Document{
    private final Document document;
    public CachedDocument(Document document) {
        this.document = document;
    }

    @Override
    public String getPath() {
        return document.getPath();
    }

    @Override
    @SneakyThrows
    public String parse() {
        DBConnection connection = DBConnection.getInstance();
        String content = connection.getContent(getPath());
        if (content == null) {
            content = document.parse();
            connection.addContent(document.getPath(), content.replace("'", "''"));
        }
        return content;
    }
}

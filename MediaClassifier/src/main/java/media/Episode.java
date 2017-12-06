package media;

import visitor.MediaVisitor;
import visitor.MediaVisitorAcceptor;

import java.nio.file.Path;

public class Episode extends MediaFile implements MediaVisitorAcceptor {
    private int number;

    public Episode(Path filename, String title, int number) {
        super(filename, title);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void accept(MediaVisitor mediaVisitor) {
        mediaVisitor.visit(this);
    }
}

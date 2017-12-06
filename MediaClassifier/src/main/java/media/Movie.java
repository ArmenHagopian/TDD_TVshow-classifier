package media;

import visitor.MediaVisitor;
import visitor.MediaVisitorAcceptor;

import java.nio.file.Path;

public class Movie extends MediaFile implements MediaVisitorAcceptor {
    public Movie(Path filename, String title) {
        super(filename, title);
    }

    @Override
    public void accept(MediaVisitor mediaVisitor) {
        mediaVisitor.visit(this);
    }
}

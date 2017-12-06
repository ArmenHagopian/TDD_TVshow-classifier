package media;

import java.nio.file.Path;

public abstract class MediaFile {
    private Path filename;
    private String title;

    public MediaFile(Path filename, String title) {
        this.filename = filename;
        this.title = title;
    }

    public Path getFilename() {
        return filename;
    }

    public void setFilename(Path filename) {
        this.filename = filename;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

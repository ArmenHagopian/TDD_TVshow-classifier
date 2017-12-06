import media.Episode;
import media.Season;
import media.TvShow;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import visitor.impl.DirectoryCreationVisitor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateCorrectFilesTest {
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void tvshowDirectoryShouldBeCreated() throws IOException {
        Path dir = temp.newFolder("output").toPath();

        TvShow show = new TvShow("Game Of Thrones");
        DirectoryCreationVisitor visitor = new DirectoryCreationVisitor(dir);
        show.accept(visitor);

        Path showdir = dir.resolve("Game Of Thrones");
        Assert.assertTrue(Files.exists(showdir));
    }

    @Test
    public void seasonDirectoryShouldBeCreated() throws IOException {
        Path dir = temp.newFolder("output").toPath();

        TvShow show = new TvShow("Game Of Thrones");
        Season season = new Season(1);
        show.addSeason(season);

        DirectoryCreationVisitor visitor = new DirectoryCreationVisitor(dir);
        show.accept(visitor);

        Path showdir = dir.resolve("Game Of Thrones");
        Path seasondir = showdir.resolve("Season 1");
        Assert.assertTrue(Files.exists(showdir));
        Assert.assertTrue(Files.exists(seasondir));
    }

    @Test
    public void episodeShouldBeCreated() throws IOException {
        Path dest = temp.newFolder("output").toPath();
        Path src = temp.newFolder("source").toPath();
        Path ep = src.resolve("Mr.Robot.S01E05.HDTV.x264-KILLERS[ettv].mkv");

        Files.createFile(ep);

        TvShow show = new TvShow("Mr Robot");
        Season season = new Season(1);
        show.addSeason(season);
        Episode episode = new Episode(ep, "title", 5);
        show.getSeasons().get(1).addEpisode(episode);

        DirectoryCreationVisitor visitor = new DirectoryCreationVisitor(dest);
        show.accept(visitor);

        Path showDir = dest.resolve("Mr Robot");
        Path seasonDir = showDir.resolve("Season 1");
        Path epFile = seasonDir.resolve("Mr RobotS1E5.mkv");
        Assert.assertTrue(Files.exists(showDir));
        Assert.assertTrue(Files.exists(seasonDir));
        // System.out.println(Files.exists());
        Assert.assertTrue(Files.exists(epFile));
    }
}

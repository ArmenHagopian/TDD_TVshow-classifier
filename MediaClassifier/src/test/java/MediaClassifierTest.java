import media.EpisodeInfo;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class MediaClassifierTest {
    @Rule
    public TemporaryFolder temp = new TemporaryFolder();

    @Test
    public void shouldCapitalize() {
        String s = "DCs legends of Tomorrow";
        String res = FileMatcher.capitalize(s);
        Assert.assertEquals("Dcs Legends Of Tomorrow ", res);
    }

    @Test
    public void createFileInfo() throws IOException {
        Path dir = temp.newFolder("The.Flash.2014.S04E07.PROPER.1080p.HDTV.x264-CRAVERS[rarbg]").toPath();
        EpisodeInfo epi = FileMatcher.newEpisodeInfo(dir);
        EpisodeInfo res = new EpisodeInfo(dir, "The Flash 2014 ", 4, 7);

        Assert.assertEquals(res.getTvShowName(), epi.getTvShowName());
        Assert.assertEquals(res.getFile(), epi.getFile());
        Assert.assertEquals(res.getEpisodeNumber(), epi.getEpisodeNumber());
        Assert.assertEquals(res.getSeasonNumber(), epi.getSeasonNumber());
        Assert.assertTrue(Files.exists(dir));
    }

    @Test
    public void shouldFindEpisode() throws IOException {
        Path dir = temp.newFolder("Torrents").toPath();
        Path file = Files.createDirectory(dir.resolve(String.format("The.Flash.2014.S0%sE%s.PROPER.1080p.HDTV.x264-CRAVERS[rarbg]", 1, 10)));
        EpisodeInfo tmp = new EpisodeInfo(file, "The Flash 2014 ", 1, 10);
        List<EpisodeInfo> episodes = FileMatcher.findEpisodes(dir);
        Assert.assertEquals(tmp.getTvShowName(), episodes.get(0).getTvShowName());
        Assert.assertEquals(tmp.getFile(), episodes.get(0).getFile());
        Assert.assertEquals(tmp.getEpisodeNumber(), episodes.get(0).getEpisodeNumber());
        Assert.assertEquals(tmp.getSeasonNumber(), episodes.get(0).getSeasonNumber());
        Assert.assertTrue(Files.exists(dir));
    }
}

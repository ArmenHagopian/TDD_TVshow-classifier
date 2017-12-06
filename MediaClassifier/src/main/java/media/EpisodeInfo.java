package media;

import java.nio.file.Path;

public class EpisodeInfo {
    public Path file;
    public String tvshowName;
    public int seasonNumber;
    public int episodeNumber;

    public EpisodeInfo(Path filename, String title, int season, int episode) {
        this.file = filename;
        this.tvshowName = title;
        this.seasonNumber = season;
        this.episodeNumber = episode;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getTvShowName() {
        return tvshowName;
    }

    public Path getFile() {
        return file;
    }

    public int getSeasonNumber() { return seasonNumber;}
}

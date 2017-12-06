package media;

import visitor.MediaVisitor;
import visitor.MediaVisitorAcceptor;

import java.util.HashMap;
import java.util.Map;

public class Season implements MediaVisitorAcceptor {
    private int number;
    private Map<Integer, Episode> episodes = new HashMap<>();

    public Season(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void addEpisode(Episode episode) {
        this.episodes.put(episode.getNumber(), episode);
    }

    public void addEpisodeFromEpisodeInfo(EpisodeInfo episodeInfo) {
        Episode episode = new Episode(episodeInfo.getFile(), "title", episodeInfo.getEpisodeNumber());
        this.episodes.put(episodeInfo.getEpisodeNumber(), episode);
    }

    @Override
    public void accept(MediaVisitor mediaVisitor) {
        mediaVisitor.visit(this);

        for (Map.Entry<Integer, Episode> entry : this.episodes.entrySet()) {
            entry.getValue().accept(mediaVisitor);
        }
    }

    public Map<Integer, Episode> getEpisodes() {
        return episodes;
    }
}

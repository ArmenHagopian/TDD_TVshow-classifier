package media;

import visitor.MediaVisitor;
import visitor.MediaVisitorAcceptor;

import java.util.HashMap;
import java.util.Map;


public class TvShow implements MediaVisitorAcceptor {
    private String title;
    private Map<Integer, Season> seasons = new HashMap<>();

    public TvShow(String title) {
        this.title = title;
    }

    public void addEpisode(EpisodeInfo episodeInfo) {
        Season s = this.seasons.get(episodeInfo.getSeasonNumber());
        if (s == null) {
            // Season doesnt exist --> create Season with episode
            s = addSeason(new Season(episodeInfo.getSeasonNumber()));
        }
        s.addEpisodeFromEpisodeInfo(episodeInfo);
    }

    public Season addSeason(Season s) {
        this.seasons.put(s.getNumber(), s);
        return seasons.get(s.getNumber());
    }

    @Override
    public void accept(MediaVisitor mediaVisitor) {
        mediaVisitor.visit(this);

        for (Map.Entry<Integer, Season> s : this.seasons.entrySet()) {
            s.getValue().accept(mediaVisitor);
        }
    }

    public String getTitle() {
        return title;
    }

    public Map<Integer, Season> getSeasons() {
        return seasons;
    }
}

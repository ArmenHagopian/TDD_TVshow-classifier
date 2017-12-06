package visitor.impl;

import media.Episode;
import media.Movie;
import media.Season;
import media.TvShow;
import org.apache.commons.io.FilenameUtils;
import visitor.MediaVisitor;

import java.io.IOException;
import java.nio.file.*;
import java.util.Map;

public class DirectoryCreationVisitor implements MediaVisitor {

    private Path destination;
    private String tvShow = null;
    private int season;
    private int episode;

    public DirectoryCreationVisitor(Path destination) {
        this.destination = destination.toAbsolutePath();
    }

    @Override
    public void visit(Episode episode) {
        this.episode = episode.getNumber();
        String episodeName = this.tvShow + "S" + this.season + "E" + this.episode;
        String episodeExt = FilenameUtils.getExtension(episode.getFilename().toString());

        Path dest = this.destination
                .resolve(tvShow)
                .resolve("Season " + this.season)
                .resolve(episodeName + "." + episodeExt);

        try {
            Files.copy(episode.getFilename(), dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (FileAlreadyExistsException e) {
            System.out.println("The file already exists: " + dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void visit(Season season) {
        this.season = season.getNumber();
        //this.seasonSegment = Paths.get("Season " + seasonNumber);

        // Concatenate path segments
        Path path = this.destination
                .resolve(Paths.get(this.tvShow))
                .resolve(Paths.get("Season " + this.season));

        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<Integer, Episode> episodeEntry : season.getEpisodes().entrySet()) {
            Episode episode = episodeEntry.getValue();
            episode.accept(this);
        }
    }

    @Override
    public void visit(TvShow tvShow) {
        Path path = this.destination.resolve(tvShow.getTitle());

        if (Files.notExists(path)) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Map.Entry<Integer, Season> seasonEntry : tvShow.getSeasons().entrySet()) {
            Season season = seasonEntry.getValue();
            this.tvShow = tvShow.getTitle();
            season.accept(this);
        }
    }

    @Override
    public void visit(Movie movie) {

    }
}

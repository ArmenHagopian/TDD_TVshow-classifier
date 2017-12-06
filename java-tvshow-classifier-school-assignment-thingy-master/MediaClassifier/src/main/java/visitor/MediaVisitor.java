package visitor;

import media.Episode;
import media.Movie;
import media.Season;
import media.TvShow;

public interface MediaVisitor {
    void visit(Episode episode);
    void visit(Season season);
    void visit(TvShow tvShow);
    void visit(Movie movie);
}

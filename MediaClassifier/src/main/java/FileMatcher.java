import media.EpisodeInfo;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileMatcher {

    static final String EPISODE_RE_PATTERN = ".+\\.([S-s])(\\d+)([E-e])(\\d+)(\\.).+";
    static final Pattern EPISODE_RE = Pattern.compile(EPISODE_RE_PATTERN, Pattern.CASE_INSENSITIVE);

    static List<EpisodeInfo> findEpisodes(Path directory) throws IOException {
        if (!Files.isDirectory(directory)) {
            throw new IllegalArgumentException(directory.toString() + " is not a directory.");
        }

        List<EpisodeInfo> episodesFound = new ArrayList<EpisodeInfo>();

        DirectoryStream<Path> dirStream = Files.newDirectoryStream(directory);

        for (Path p : dirStream) {
            if (EPISODE_RE.matcher(p.getFileName().toString()).matches()) {
                episodesFound.add(newEpisodeInfo(p));
            }
        }

        return episodesFound;
    }

    public static EpisodeInfo newEpisodeInfo(Path file) {
        Pattern p = Pattern.compile("(.+\\.)([S-s]\\d+[E-e]\\d+)(\\..+)");
        Matcher m = p.matcher(file.getFileName().toString());
        String name = "";
        Integer season = 0;
        Integer episode = 0;
        if (m.find()) {
            Pattern epi_season = Pattern.compile("([S-s])(\\d+)([E-e])(\\d+)");
            Matcher epi = epi_season.matcher(m.group(2));
            if (epi.find()) {
                season = Integer.parseInt(epi.group(2));
                episode = Integer.parseInt(epi.group(4));
            }
            name = capitalize(m.group(1).replaceAll("\\.", " "));

        }
        return new EpisodeInfo(file, name, season, episode);
    }

    public static String capitalize(String s) {
        if (s.length() == 0) return s;
        String[] words = s.split("\\s");
        String r = "";
        for (String l : words) {
            r += l.substring(0, 1).toUpperCase() + l.substring(1).toLowerCase() + " ";
        }
        return r;
    }
}

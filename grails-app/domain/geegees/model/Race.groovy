package geegees.model

import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

public class Race implements Comparable<Race> {

    String venue
    String time
    Integer numberOfRunners
//    Collection<Horse> horses
    boolean bettable = false

    static hasMany = [horses:Horse]

    static constraints = {
    }

    public Race(){}

    public Race(Document document) {
        venue = document.select("h1 > span").get(0).text();
        time = document.select("h1 > strong").get(0).text();
        for (Element shortInfo : document.select("p.raceShortInfo > span")) {
            if ("Runners:".equals(shortInfo.textNodes().get(0).text().trim())) {
                numberOfRunners = Integer.valueOf(shortInfo.select("strong").get(0).text());
            }
        }
    }

    @Override
    public int compareTo(Race race) {
        if (race.getVenue().equals(getVenue())) {
            return getTime().compareTo(race.getTime());
        } else {
            return getVenue().compareTo(race.getVenue());
        }
    }

    @Override
    String toString() {
        return "$time at $venue"
    }
}

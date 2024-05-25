package person.playerUtils;

import person.Player;

import java.util.Comparator;

/**
 * Class ComparatorByPosition
 * A comparator that sorts by alphabetical order of position,
 * if equal it sorts by quality.
 */
public class ComparatorByPosition implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        if (p1.getPosition() == p2.getPosition()) {
            ComparatorByQuality comparator = new ComparatorByQuality();
            return comparator.compare(p1, p2);
        }
        return String.valueOf(p1.getPosition()).compareTo(String.valueOf(p2.getPosition()));
    }
}

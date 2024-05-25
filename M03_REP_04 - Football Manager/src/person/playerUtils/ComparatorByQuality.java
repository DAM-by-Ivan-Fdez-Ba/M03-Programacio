package person.playerUtils;

import person.Player;

import java.util.Comparator;

/**
 * Class ComparatorByQuality
 * A comparator that sorts by quality order from highest to lowest,
 * if it is the same it does it by motivation from highest to lowest
 * and if it is the same it does it by alphabetical order of the surname.
 */
public class ComparatorByQuality implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        int scoreDifference = (int) (p2.getScore() - p1.getScore());
        if (scoreDifference != 0) {
            return scoreDifference;
        }
        int motivationLevelDifference = (int) (p2.getMotivationLevel() - p1.getMotivationLevel());
        if (motivationLevelDifference != 0) {
            return motivationLevelDifference;
        }
        return p1.getSurame().compareTo(p2.getSurame());
    }
}

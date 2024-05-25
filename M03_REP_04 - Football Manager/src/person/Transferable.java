package person;
import team.Team;

/**
 * Interface Transferable
 * An attribute and a method that will have things transferable.
 */
public interface Transferable {
    boolean isTransferable();
    void transferToTeam(Team t);
}

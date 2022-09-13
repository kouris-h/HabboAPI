package habbo.api.apipublic.groups;

/**
 * Possible types of a group
 */
public enum GroupType {
    /**
     * Everyone can join the group
     */
    NORMAL,
    /**
     * An admin has to accept members into the group
     */
    EXCLUSIVE,
    /**
     * Nobody can join the group
     */
    CLOSED
}

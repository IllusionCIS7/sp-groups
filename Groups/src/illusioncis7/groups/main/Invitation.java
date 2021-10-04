package illusioncis7.groups.main;

public class Invitation {

    Main instance;
    Group group;
    User user;

    public Invitation(Main main)
    {
        this.instance = main;
    }

    public void setGroup(Group newGroup)
    {
        this.group = newGroup;
    }
    public Group getGroup()
    {
        return this.group;
    }

    public void setUser(User newUser)
    {
        this.user = newUser;
    }
    public User getUser()
    {
        return this.user;
    }
}

package illusioncis7.groups.main;

public class Invitation {

    Group group;
    User user;
    ConfigManager gcm;
    ConfigManager ucm;

    public Invitation()
    {
        gcm = new ConfigManager("groups.yml");
        ucm = new ConfigManager("user.yml");
    }

    public Invitation(Group newGroup, User newUser)
    {
        group = newGroup;
        user = newUser;
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

    public void acceptInvitation()
    {
        user.setGroup(group);
        group.setMember(user);
    }
}

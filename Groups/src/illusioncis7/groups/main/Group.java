package illusioncis7.groups.main;

public class Group {

    Main instance;
    String guid;
    String groupName;
    String chatColor;

    public Group(Main main)
    {
        instance = main;
    }

    public Group(Main main, String guid)
    {
        this.guid = guid;
        instance = main;
    }


    public void setGroupName(String newGroupName)
    {
        this.groupName = newGroupName;
    }
    public String getGroupName()
    {
        return this.groupName;
    }

    public void setChatColor(String newChatColor)
    {
        this.chatColor = newChatColor;
    }
    public String getChatColor()
    {
        return this.chatColor;
    }
}

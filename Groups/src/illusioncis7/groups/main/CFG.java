package illusioncis7.groups.main;

public final class CFG {

    public static String GroupName(String guid){
        return guid + ".name";
    }
    public static String GroupBaseWorld(String guid){
        return guid + ".base.world";
    }
    public static String GroupBaseCoordinatesX(String guid){
        return guid + ".base.coordinates.x";
    }
    public static String GroupBaseCoordinatesY(String guid){
        return guid + ".base.coordinates.y";
    }
    public static String GroupBaseCoordinatesZ(String guid){
        return guid + ".base.coordinates.z";
    }
    public static String GroupChatColor(String guid){
        return guid + ".chatColor";
    }
    public static String GroupOwner(String guid){
        return guid + ".owner";
    }
    public static String GroupMember(String guid){
        return guid + ".member";
    }
    public static String UserChatColor(String uuid){
        return uuid + ".chatColor";
    }
    public static String UserGroup(String uuid){
        return uuid + ".group";
    }
    public static String UserClanChatActive(String uuid){
        return uuid + ".clanChatActive";
    }
    public static String UserInvitations(String uuid){
        return uuid + ".invitations";
    }
    public static String ConfigDefaultUserChatColor(){
        return "defaultUserChatColor";
    }
}

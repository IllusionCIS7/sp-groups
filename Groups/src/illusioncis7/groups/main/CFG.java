package illusioncis7.groups.main;

/*
Diese Klasse ist nur dafür da, um auf einfache Weise die Pfade der verschiedenen Parameter in den (YAML-)Konfigurationen
zurückgeben zu können
 */

public final class CFG {

    /*
    Der Parameter guid dient in diesem Fall dazu, dass die Gruppe in der Konfiguration auch gefunden wird, da so
    gespeichert wird:
    GruppenUniqueID.name = Gruppenname
    837bb98d-83730d9.name = Gruppenname
    In der Config-Datei sieht das dann ungefähr so aus:
    837bb98d-83730d9:
        name: 'Owner'
        base:
            world: 'world'
            x: 1234.000
            y: 121.000
            z: etc....
     */
    public static String GroupName(String guid){
        return guid + ".name";
    }
    public static String GroupBaseWorld(String guid){
        return guid + ".base.world";
    }
    public static String GroupBaseCoordinatesX(String guid){
        return guid + ".base.x";
    }
    public static String GroupBaseCoordinatesY(String guid){
        return guid + ".base.y";
    }
    public static String GroupBaseCoordinatesZ(String guid){
        return guid + ".base.z";
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

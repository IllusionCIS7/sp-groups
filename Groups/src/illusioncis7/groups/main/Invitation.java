package illusioncis7.groups.main;

/*

Diese Klasse stellt eine Einladung zu einer Gruppe dar

 */

public class Invitation {

    Group group; // Die Gruppe zu der eingeladen wird
    User user;   // Der Spieler der zur Gruppe eingeladen wird
    ConfigManager gcm;
    ConfigManager ucm;

    // Der Konstruktor erstellt die Einladung
    public Invitation(Group newGroup, User newUser)
    {
        // Abrufen der nötigen Konfigurationen
        gcm = new ConfigManager("groups.yml");
        ucm = new ConfigManager("user.yml");

        // Übergabeparameter (Gruppe und eingeladener Spieler) werden an die Klassenvariablen übergeben
        group = newGroup;
        user = newUser;
    }

    // Gibt die Gruppe zurück
    public Group getGroup()
    {
        return this.group;
    }

    // Gibt den eingeladenen Spieler zurück
    public User getUser()
    {
        return this.user;
    }

    // Akzeptiert die Einladung und fügt den Spieler der Gruppe hinzu, sowie die Gruppe dem Spieler
    public void acceptInvitation()
    {
        group.setMember(user);
        user.setGroup(group);
    }
}

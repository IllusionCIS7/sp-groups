package illusioncis7.groups.Listener;

import illusioncis7.groups.main.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private String userName;
    private String groupName;
    private String message;
    private String chatColor;

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e)
    {
        User chatUser = new User(e.getPlayer());
        if (chatUser.hasClan())
        {

        }
    }
}

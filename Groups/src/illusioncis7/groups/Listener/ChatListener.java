package illusioncis7.groups.Listener;

import illusioncis7.groups.main.Group;
import illusioncis7.groups.main.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private User userSender;
    private Group group;
    private String message;

    @EventHandler
    public void onChatEvent(AsyncPlayerChatEvent e)
    {
        userSender = new User(e.getPlayer());
        if (userSender.hasGroup())
        {
            userSender.getUuid();
        }
        else
        {
            userSender.getPlayer();
        }
        e.setCancelled(true);
    }

    private void sendGroupMessage()
    {

    }

    private void sendGlobalMessage()
    {

    }
}

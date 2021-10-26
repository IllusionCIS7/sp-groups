package illusioncis7.groups.Listener;

import illusioncis7.groups.main.User;
import illusioncis7.utils.Checker;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e)
    {
        if (Checker.checkUserAvailability(e.getPlayer().getUniqueId().toString()) == null)
        {
            new User(e.getPlayer().getUniqueId().toString(), true);
        }
    }
}

package illusioncis7.groups.Listener;

import illusioncis7.groups.main.Group;
import illusioncis7.groups.main.Invitation;
import illusioncis7.groups.main.MSG;
import illusioncis7.groups.main.User;
import illusioncis7.utils.Checker;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.List;

// Command Executor fpr den '/groups' Command
public class CLgroups implements CommandExecutor {

    private final String standardResponse = "Etwas ist schief gelaufen.";

    @Override
    public boolean onCommand(@NotNull CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player playerSender = (Player) sender;
            User user = new User(playerSender);
            if (args.length == 0)
            {
                List<String> commands = MSG.Commands();
                for (String s : commands)
                {
                    playerSender.sendMessage(s);
                }
            }
            else
            {
                if (args[0].equalsIgnoreCase("create") && args.length == 2)
                {
                    playerSender.sendMessage(CreateGroup(user, args[1]));
                }
                else if (args[0].equalsIgnoreCase("setbase") && args.length == 1)
                {
                    playerSender.sendMessage(SetBase(user, playerSender));
                }
                else if (args[0].equalsIgnoreCase("tpbase") && args.length == 1)
                {

                }
            }
        }
        return false;
    }

    private String CreateGroup(User user, String groupName)
    {
        String response = standardResponse;

        if (user.hasGroup())
        {
            response = MSG.AlreadyInGroup();
        }
        else
        {
            Group checkGroup = Checker.checkGroupNameAvailability(groupName);
            if (checkGroup.equals(new Group(null)))
            {
                if (Checker.HasIllegalGroupName(groupName))
                {
                    response = MSG.IllegalGroupName();
                }
                else
                {
                    Group group = new Group(groupName, user);
                    response = MSG.CreateGroupSuccess(group);
                }
            }
            else
            {
                response = MSG.CreateGroupAlreadyExists(checkGroup);
            }
        }
        return response;
    }

    private String SetBase(User user, Player player)
    {
        String response = standardResponse;
        if (user.hasGroup())
        {
            Group group = user.getGroup();
            if (group.getOwner().equals(user))
            {
                group.setBase(player.getLocation());
                response = MSG.BaseSpawnCreated();
            }
            else
            {
                response = MSG.NotOwner();
            }
        }
        else
        {
            response = MSG.HasNoGroup();
        }
        return response;
    }

    private String TpBase(User user, Player player)
    {
        String response = standardResponse;

        if (user.hasGroup())
        {
            Group group = user.getGroup();
            if (group.getBase() == null)
            {
                 response = MSG.BaseSpawnNotExists();
            }
            else
            {
                player.teleport(group.getBase());
                response = MSG.BaseSpawnTeleported();
            }
        }
        else
        {
            response = MSG.HasNoGroup();
        }
        return response;
    }

    private String AddMember(User user, String newMember)
    {
        String response = standardResponse;

        if (user.hasGroup())
        {
            Group group = user.getGroup();
            if (group.getOwner().equals(user))
            {
                if (Bukkit.getPlayer(newMember) != null)
                {
                    Player newPlayer = Bukkit.getPlayer(newMember);
                    User newUser = new User(newPlayer.getUniqueId().toString());
                    if (newUser.hasGroup())
                    {
                        response = MSG.InviteAlreadyInGroup();
                    }
                    else
                    {
                        Invitation newInvitation = new Invitation(user.getGroup(), newUser);
                        newUser.setInvitation(newInvitation);
                        response = MSG.InviteSuccessfull(newUser);
                    }
                }
                else
                {
                    response = MSG.NotOnline();
                }
            }
            else
            {
                response = MSG.NotOwner();
            }
        }
        else
        {
            response = MSG.HasNoGroup();
        }
        return response;
    }
}

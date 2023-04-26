package in;

import net.md_5.bungee.Util;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Glist extends Command {
    public Glist() {
        super("glist", "in.glist", "list");

    }

    @Override
    public void execute(CommandSender sender, String[] args)
    {
        for ( ServerInfo server : ProxyServer.getInstance().getServers().values() )
        {
            if ( !server.canAccess( sender ) )
            {
                continue;
            }

            List<String> players = new ArrayList<>();
            for ( ProxiedPlayer player : server.getPlayers() )
            {
                players.add( player.getDisplayName() );
            }
            Collections.sort( players, String.CASE_INSENSITIVE_ORDER );

            sender.sendMessage(ChatColor.GOLD + server.getName().substring(0, 1).toUpperCase() + server.getName().substring(1) + ChatColor.DARK_GRAY +" (" + ChatColor.GREEN + server.getPlayers().size() + ChatColor.DARK_GRAY + ") "+ ChatColor.GRAY+ "» " + Util.format( players, ChatColor.GRAY + ", " ) );
        }

        sender.sendMessage(ChatColor.GRAY + ProxyServer.getInstance().getTranslation( "total_players", ProxyServer.getInstance().getOnlineCount() ) );
    }
}

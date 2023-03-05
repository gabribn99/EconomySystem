package plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.core.Main;

public class Balance implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            double amount = Main.mapBalances.get(player.getName()).getAmount();
            player.sendMessage(ChatColor.GOLD + "Tu saldo actual es: " + ChatColor.WHITE + amount + "₱");
        }
        return false;
    }
}

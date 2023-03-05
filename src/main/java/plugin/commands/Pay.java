package plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import plugin.core.Main;
import plugin.entities.BalanceBean;

public class Pay implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player playerSender = (Player) sender;
            Player playerReceiver = (args != null) ? Bukkit.getPlayer(args[0]) : null;
            double amount = (args != null) ? Double.parseDouble(args[1]) : 0;
            if (playerReceiver == null) {
                sender.sendMessage(ChatColor.RED + "El comando esta incompleto [Falta el destinatario]");
                return false;
            }
            if (amount == 0) {
                sender.sendMessage(ChatColor.RED + "El comando esta incompleto [Falta la cantidad] o cantidad inferior a 1");
                return false;
            }
            BalanceBean senderAccount = Main.mapBalances.get(playerSender.getName());
            BalanceBean receiverAccount = Main.mapBalances.get(playerReceiver.getName());
            if (amount <= senderAccount.getAmount()) {
                senderAccount.quitAmmount(amount);
                playerSender.sendMessage(ChatColor.BLUE + "Se han enviado " + amount + " ₱ a " + playerReceiver.getName());
                receiverAccount.addAmount(amount);
                playerReceiver.sendMessage(ChatColor.BLUE + "Has recibido " + amount + " ₱ de " + playerSender.getName());
            } else playerSender.sendMessage(ChatColor.RED + "No tienes esta cantidad de dinero");

        }
        return false;
    }
}

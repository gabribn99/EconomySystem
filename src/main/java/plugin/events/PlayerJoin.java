package plugin.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import plugin.core.Main;
import plugin.entities.BalanceBean;

public class PlayerJoin implements Listener {

    public static final double DEFAULT_AMOUNT = 10;

    @EventHandler
    public void Entrada(PlayerJoinEvent event) {

        BalanceBean wallet = Main.mapBalances.get(event.getPlayer().getName());
        if (wallet == null) {
            Main.mapBalances.put(event.getPlayer().getName(), new BalanceBean(event.getPlayer().getName(), DEFAULT_AMOUNT));
            event.getPlayer().sendMessage(ChatColor.GOLD + "Cartera creada con saldo inicial\nSaldo actual: " + ChatColor.WHITE + DEFAULT_AMOUNT + "₱");
        } else {
            event.getPlayer().sendMessage(ChatColor.GOLD + "Saldo actual: " + ChatColor.WHITE + wallet.getAmount() + "₱");
        }
    }
}

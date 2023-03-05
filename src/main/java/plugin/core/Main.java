package plugin.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import plugin.adapters.BalanceAdapter;
import plugin.commands.Balance;
import plugin.commands.Pay;
import plugin.entities.BalanceBean;
import plugin.events.PlayerJoin;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    public static final String PATH = "economySystem/";
    public static final String FILENAME = "economySystem.json";
    public static Map<String, BalanceBean> mapBalances = new HashMap<>();

    @Override
    public void onEnable() {
        loadBalances();
        setCommands();
        setEvents();
        Bukkit.getConsoleSender().sendMessage("Economy System está activado");
    }

    @Override
    public void onDisable() {
        saveBalances();
        Bukkit.getConsoleSender().sendMessage("Economy System está desactivado");
    }

    private void setCommands() {
        getCommand("balance").setExecutor(new Balance());
        getCommand("pay").setExecutor(new Pay());
    }
    private void setEvents() {
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
    }

    private void saveBalances() {
        File file = new File(PATH);
        if (!file.exists()) {
            file.mkdir();
        }
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BalanceBean.class, new BalanceAdapter());
        Gson gson = builder.create();
        try {
            PrintWriter pw = new PrintWriter(new FileWriter(PATH + FILENAME));
            mapBalances.forEach((playerName, balance) -> {
                BalanceBean balanceBean = new BalanceBean(balance.getPlayerName(), balance.getAmount());
                pw.println(gson.toJson(balanceBean));
            });
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBalances() {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(BalanceBean.class, new BalanceAdapter());
        Gson gson = builder.create();
        List<BalanceBean> balanceBeanList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH + FILENAME));
            String jsonString;
            while ((jsonString = br.readLine()) != null) {
                balanceBeanList.add(gson.fromJson(jsonString, BalanceBean.class));
            }
            balanceBeanList.forEach(balanceBean -> {
                mapBalances.put(balanceBean.getPlayerName(), balanceBean);
            });
        } catch (IOException e) {
            System.out.println("Aun no existe el fichero \"" + FILENAME + "\"");
        }
    }
}

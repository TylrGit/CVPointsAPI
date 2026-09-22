package org.cubeville.CVPointsAPI;

import org.bukkit.plugin.java.JavaPlugin;
import org.cubeville.CVPointsAPI.hooks.BetonQuestHook;
import org.cubeville.CVPointsAPI.hooks.VaultHook;
import org.cubeville.CVPointsAPI.rest.RestServer;

import java.io.IOException;

public final class CVPointsAPI extends JavaPlugin {

    private static CVPointsAPI instance;
    private RestServer restServer;

    @Override
    public void onEnable() {
        instance = this;
        // Config file -> port, private-key
        saveDefaultConfig();

        // REST
        restServer = new RestServer(
            getConfig().getInt("api.port"),
            getConfig().getInt("api.threads"),
            getConfig().getString("api.private-key")
        );
        try {
            restServer.start();
        } catch (IOException e) {
            getLogger().severe("Could not start REST server");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }

        // Vault
        if(!VaultHook.hook()) {
            getLogger().severe("Could not hook to Vault's permissions");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        // BetonQuest
        if(!BetonQuestHook.hook()) {
            getLogger().severe("Could not hook to BQ's API");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getLogger().info("CVTokenAPI enabled successfully!");
    }

    @Override
    public void onDisable() {
        restServer.stop();
    }

    public static CVPointsAPI getInstance() {
        return instance;
    }
}

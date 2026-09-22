package org.cubeville.CVPointsAPI.hooks;

import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.cubeville.CVPointsAPI.CVPointsAPI;

public class VaultHook {
    private static Permission perms;

    public static Permission getPermissions() {
        return perms;
    }

    public static boolean hook() {
        RegisteredServiceProvider<Permission> rsp = CVPointsAPI.getInstance()
                .getServer()
                .getServicesManager()
                .getRegistration(Permission.class);

        if (rsp == null) return false;

        perms = rsp.getProvider();
        return true;
    }
}
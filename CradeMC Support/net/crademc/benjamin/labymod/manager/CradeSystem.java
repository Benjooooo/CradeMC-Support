package net.crademc.benjamin.labymod.manager;

import net.crademc.benjamin.labymod.player.PlayerInfo;
import net.crademc.benjamin.labymod.stats.Stats;

public class CradeSystem {

    private String prefix;
    public boolean enabled;
    public boolean showKills;
    public boolean showDeaths;

    private Stats stats;
    private PlayerInfo playerInfo;

    public CradeSystem(String prefix) {
        this.prefix = prefix;
        this.stats = new Stats();
        this.playerInfo = new PlayerInfo();
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public Stats getStats() {
        return stats;
    }

    public PlayerInfo getPlayerInfo() {
        return playerInfo;
    }
}

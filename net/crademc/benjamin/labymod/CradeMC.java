package net.crademc.benjamin.labymod;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.crademc.benjamin.labymod.manager.CradeSystem;
import net.crademc.benjamin.labymod.server.CradeServer;
import net.labymod.api.LabyModAPI;
import net.labymod.api.LabyModAddon;
import net.labymod.api.events.ServerMessageEvent;
import net.labymod.main.LabyMod;
import net.labymod.settings.elements.BooleanElement;
import net.labymod.settings.elements.ControlElement;
import net.labymod.settings.elements.SettingsElement;
import net.labymod.utils.Consumer;
import net.labymod.utils.Material;

import java.util.List;

public class CradeMC extends LabyModAddon {

    private static CradeSystem system;

    @Override
    public void onEnable() {
        system = new CradeSystem("§8» §a§lCradeMod §8» ");
        this.getApi().registerServerSupport(this, new CradeServer());
        register();
    }

    @Override
    public void onDisable() {

    }

    @Override
    public void loadConfig() {
        getSystem().enabled = !getConfig().has("Enabled") || getConfig().get("Enabled").getAsBoolean();
        getSystem().showKills = !getConfig().has("showKills") || getConfig().get("showKills").getAsBoolean();
        getSystem().showDeaths = !getConfig().has("showDeaths") || getConfig().get("showDeaths").getAsBoolean();
    }

    @Override
    protected void fillSettings(List<SettingsElement> list) {
        list.add(new BooleanElement("Enabled", new ControlElement.IconData(Material.LEVER), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                getSystem().enabled = accepted;
                CradeMC.this.getConfig().addProperty("Enabled", accepted);
                CradeMC.this.saveConfig();
                System.out.println("Changed value: " + accepted);
            }
        }, system.enabled));
        list.add(new BooleanElement("Show Kills", new ControlElement.IconData(Material.IRON_SWORD), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                getSystem().showKills = accepted;
                CradeMC.this.getConfig().addProperty("showKills", accepted);
                CradeMC.this.saveConfig();
                System.out.println("Changed value: " + accepted);
            }
        }, system.showKills));
        list.add(new BooleanElement("Show Deaths", new ControlElement.IconData(Material.BONE), new Consumer<Boolean>() {
            @Override
            public void accept(Boolean accepted) {
                getSystem().showDeaths = accepted;
                CradeMC.this.getConfig().addProperty("showDeaths", accepted);
                CradeMC.this.saveConfig();
                System.out.println("Changed value: " + accepted);
            }
        }, system.showDeaths));

    }

    public static CradeSystem getSystem() {
        return system;
    }

    private void register() {
        getApi().getEventManager().register(new ServerMessageEvent() {
            @Override
            public void onServerMessage(String s, JsonElement jsonElement) {
                if(jsonElement.isJsonObject()) {
                    if(s.equalsIgnoreCase("minigame")) {
                        JsonObject object = jsonElement.getAsJsonObject();
                        if(object.has("minigame")) {
                            String handle = object.get("minigame").getAsString();
                            getSystem().getPlayerInfo().setMinigame(handle);
                        }
                    }
                    if(s.equalsIgnoreCase("coins")) {
                        JsonObject object = jsonElement.getAsJsonObject();
                        if(object.has("coins")) {
                            String handle = object.get("coins").getAsString();
                            getSystem().getStats().setCoins(handle);
                        }
                    }
                    if(s.equalsIgnoreCase("kills")) {
                        JsonObject object = jsonElement.getAsJsonObject();
                        if(object.has("kills")) {
                            String handle = object.get("kills").getAsString();
                            getSystem().getStats().setKills(handle);
                        }
                    }
                    if(s.equalsIgnoreCase("deaths")) {
                        JsonObject object = jsonElement.getAsJsonObject();
                        if(object.has("deaths")) {
                            String handle = object.get("deaths").getAsString();
                            getSystem().getStats().setDeaths(handle);
                        }
                    }
                }
            }
        });
    }

}

package net.crademc.benjamin.labymod.server;

import net.crademc.benjamin.labymod.CradeMC;
import net.labymod.api.events.TabListEvent;
import net.labymod.ingamegui.moduletypes.ColoredTextModule;
import net.labymod.main.LabyMod;
import net.labymod.servermanager.ChatDisplayAction;
import net.labymod.servermanager.Server;
import net.labymod.settings.elements.SettingsElement;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.PacketBuffer;

import java.util.Collections;
import java.util.List;

public class CradeServer extends Server {

    public CradeServer() {
        super("CradeMC", "crademc.net");

    }

    @Override
    public void onJoin(ServerData serverData) {
        if(CradeMC.getSystem().isEnabled()) {
            LabyMod.getInstance().displayMessageInChat(CradeMC.getSystem().getPrefix() + "§7Herzlich willkommen, §a" + LabyMod.getInstance().getPlayerName() + " §7, auf §fCradeMC§7.");
        }
    }

    @Override
    public ChatDisplayAction handleChatMessage(String s, String s1) throws Exception {
        return null;
    }

    @Override
    public void handlePluginMessage(String s, PacketBuffer packetBuffer) throws Exception {

    }

    @Override
    public void handleTabInfoMessage(TabListEvent.Type type, String s, String s1) throws Exception {

    }

    @Override
    public void fillSubSettings(List<SettingsElement> list) {

    }

    @Override
    public void addModuleLines( List<DisplayLine> lines ) {
        if(CradeMC.getSystem().isEnabled()) {
            lines.add(new DisplayLine("Coins", Collections.singletonList(ColoredTextModule.Text.getText("" + CradeMC.getSystem().getStats().getCoins()))));
            if(CradeMC.getSystem().getPlayerInfo().getMinigame() != "") {
                if(CradeMC.getSystem().showKills) {
                    lines.add(new DisplayLine("Kills", Collections.singletonList(ColoredTextModule.Text.getText("" + CradeMC.getSystem().getStats().getKills()))));
                }
                if(CradeMC.getSystem().showDeaths) {
                    lines.add(new DisplayLine("Deaths", Collections.singletonList(ColoredTextModule.Text.getText("" + CradeMC.getSystem().getStats().getDeaths()))));
                }
            }
        }
        super.addModuleLines(lines);
    }

}

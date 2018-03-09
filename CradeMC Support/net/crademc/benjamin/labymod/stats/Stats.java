package net.crademc.benjamin.labymod.stats;

public class Stats {

    private String coins = "NOTFOUND";
    private String kills = "NOTFOUND";
    private String deaths = "NOTFOUND";

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        if(this.coins.equalsIgnoreCase("NOTFOUND")) {
            this.coins = "0";
        }
        this.coins = coins;
    }

    public String getKills() {
        if(this.kills.equalsIgnoreCase("NOTFOUND")) {
            this.kills = "0";
        }
        return kills;
    }

    public void setKills(String kills) {
        this.kills = kills;
    }

    public String getDeaths() {
        if(this.deaths.equalsIgnoreCase("NOTFOUND")) {
            this.deaths = "0";
        }
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }
}

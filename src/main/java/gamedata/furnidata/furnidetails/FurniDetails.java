package gamedata.furnidata.furnidetails;

import fetch.Fetcher;
import org.json.JSONObject;

import java.awt.image.BufferedImage;

public abstract class FurniDetails {
    public final String className, category, name, description, furniline, adUrl, enviroment;
    public final int id, revision, offerId, rentOfferId;
    public final boolean isBC, isRare, isBuyOut, isRentBuyOut, isExcludedDynamic;

    public FurniDetails(JSONObject jsonObject) {
        this.className = jsonObject.has("classname") ? jsonObject.getString("classname") : null;
        this.category = jsonObject.has("category") ? jsonObject.getString("category") : null;
        this.name = jsonObject.has("name") ? jsonObject.getString("name") : null;
        this.description = jsonObject.has("description") ? jsonObject.getString("description") : null;
        this.furniline = jsonObject.has("furniline") ? jsonObject.getString("furniline") : null;

        this.adUrl = jsonObject.has("adurl") ? jsonObject.getString("adurl") : null;
        this.enviroment = jsonObject.has("enviroment") ? jsonObject.getString("enviroment") : null;

        this.id = jsonObject.getInt("id");
        this.revision = jsonObject.getInt("revision");
        this.offerId = jsonObject.getInt("offerid");
        this.rentOfferId = jsonObject.getInt("rentofferid");

        this.isBC = jsonObject.getBoolean("bc");
        this.isRare = jsonObject.getBoolean("rare");
        this.isBuyOut = jsonObject.getBoolean("buyout");
        this.isRentBuyOut = jsonObject.getBoolean("rentbuyout");
        this.isExcludedDynamic = jsonObject.getBoolean("excludeddynamic");
    }

    public BufferedImage getIcon() {
        return Fetcher.fetchImage(String.format("https://images.habbo.com/dcr/hof_furni/%d/%s_icon.png", revision, className));
    }

    @Override
    public String toString() {
        return "FurniDetails{" +
                "className='" + className + '\'' +
                ", category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", furniline='" + furniline + '\'' +
                ", adUrl='" + adUrl + '\'' +
                ", enviroment='" + enviroment + '\'' +
                ", id=" + id +
                ", revision=" + revision +
                ", offerId=" + offerId +
                ", rentOfferId=" + rentOfferId +
                ", isBC=" + isBC +
                ", isRare=" + isRare +
                ", isBuyOut=" + isBuyOut +
                ", isRentBuyOut=" + isRentBuyOut +
                ", isExcludedDynamic=" + isExcludedDynamic +
                '}';
    }
}

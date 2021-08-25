package gamedata.furnidata.furnidetails;

import fetch.Fetcher;
import org.json.JSONObject;

import java.awt.image.BufferedImage;

public abstract class FurniDetails {
    public final String className, category, name, description, furniline, adUrl, environment;
    public final int id, revision, offerId, rentOfferId;
    public final boolean isBC, isRare, isBuyOut, isRentBuyOut, isExcludedDynamic;

    public FurniDetails(JSONObject jsonObject) {
        this.className = jsonObject.optString("classname",null);
        this.category = jsonObject.optString("category",null);
        this.name = jsonObject.optString("name",null);
        this.description = jsonObject.optString("description",null);
        this.furniline = jsonObject.optString("furniline",null);

        this.adUrl = jsonObject.optString("adurl",null);
        this.environment = jsonObject.optString("environment",null);

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
                ", enviroment='" + environment + '\'' +
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

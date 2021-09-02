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
        return "\tFurniDetails {" +
                "\n\t\tclassName='" + className + '\'' +
                ",\n\t\tcategory='" + category + '\'' +
                ",\n\t\tname='" + name + '\'' +
                ",\n\t\tdescription='" + description + '\'' +
                ",\n\t\tfurniline='" + furniline + '\'' +
                ",\n\t\tadUrl='" + adUrl + '\'' +
                ",\n\t\tenviroment='" + environment + '\'' +
                ",\n\t\tid=" + id +
                ",\n\t\trevision=" + revision +
                ",\n\t\tofferId=" + offerId +
                ",\n\t\trentOfferId=" + rentOfferId +
                ",\n\t\tisBC=" + isBC +
                ",\n\t\tisRare=" + isRare +
                ",\n\t\tisBuyOut=" + isBuyOut +
                ",\n\t\tisRentBuyOut=" + isRentBuyOut +
                ",\n\t\tisExcludedDynamic=" + isExcludedDynamic +
                "\n\t}";
    }
}

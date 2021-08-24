package gamedata.productdata;

import fetch.Fetcher;
import gamedata.Gamedata;
import hotel.Habbo;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductData extends Gamedata {
    protected Map<String, ProductDetails> productDetailsByCode = new HashMap<>();

    public ProductData(Habbo selectedHabbo) throws IOException {
        super(selectedHabbo);
        this.parseData(this.getJSONObject());
        System.out.println(productDetailsByCode);
    }

    @Override
    protected JSONObject getJSONObject() throws IOException {
        return Fetcher.fetchJSONObject(String.format("%s/gamedata/productdata_json/1", selectedHabbo.domain));
    }

    @Override
    protected void parseData(JSONObject productdataJson) {
        this.productDetailsByCode =
                productdataJson
                        .getJSONObject("productdata")
                        .getJSONArray("product")
                        .toList()
                        .parallelStream()
                        .map(o -> (HashMap<String, Object>) o)
                        .map(JSONObject::new)
                        .map(ProductDetails::new)
                        .collect(Collectors.toMap(ProductDetails::getCode, ProductDetails::getInstance));
    }

    public Map<String, ProductDetails> getProductDetailsByCode() {
        return productDetailsByCode;
    }

    private static class ProductDetails {
        public final String code, name, description;

        public ProductDetails(JSONObject jsonObject) {
            this.code = "" + jsonObject.get("code");
            this.name = jsonObject.has("name") ? jsonObject.getString("name") : null;
            this.description = jsonObject.has("description") ? jsonObject.getString("description") : null;
        }

        private String getCode() {
            return code;
        }

        private ProductDetails getInstance() {
            return this;
        }
    }
}

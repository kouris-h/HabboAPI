package gamedata.productdata;

import fetch.Fetcher;
import gamedata.Gamedata;
import hotel.Hotel;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductData extends Gamedata {
    protected Map<String, ProductDetails> productDetailsByCode = new HashMap<>();

    public ProductData(Hotel selectedHotel) throws IOException {
        super(selectedHotel);
        this.parseData(this.getJSONObject());
    }

    @Override
    protected JSONObject getJSONObject() throws IOException {
        return Fetcher.fetchJSONObject(String.format("%sgamedata/productdata_json/1", selectedHotel.domain));
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
            this.name = jsonObject.optString("name",null);
            this.description = jsonObject.optString("description", null);
        }

        private String getCode() {
            return code;
        }

        private ProductDetails getInstance() {
            return this;
        }
    }
}

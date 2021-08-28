import fetch.Fetcher;
import gamedata.furnidata.FurniData;
import gamedata.furnidata.furnidetails.FloorItemDetails;
import gordon.EffectMap;
import hotel.Hotel;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        // System.out.println(EffectMap.getEffectById(5).name);

        FurniData fd = new FurniData(Hotel.NL);
        String packet = "{out:FigureSetIds}";
        List<FloorItemDetails> clothing = fd
                .getAllFloorItems()
                .parallelStream()
                .filter(i -> i.specialType == 23)
                .collect(Collectors.toList());
        StringBuilder ids = new StringBuilder();
        StringBuilder names = new StringBuilder();
        int count = 0;
        for(FloorItemDetails item : clothing) {
            String[] customParams = item.customParams.split(",");
            for(String param : customParams) {
                count++;
                ids.append(String.format("{i:%s}", param.trim()));
                names.append(String.format("{s:\"%s\"}", item.className));
            }
        }
        packet += String.format("{i:%s}", count) + ids.toString() + String.format("{i:%s}", count) + names.toString();
        System.out.println(packet);
    }
}

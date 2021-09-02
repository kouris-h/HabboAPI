import gamedata.furnidata.FurniData;
import gamedata.furnidata.furnidetails.FloorItemDetails;
import hotel.Hotel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        FurniData fd = new FurniData(Hotel.NL);
        FloorItemDetails details = fd.getFloorItemDetailsByTypeID(3000);
        System.out.println(details.toString());
    }
}

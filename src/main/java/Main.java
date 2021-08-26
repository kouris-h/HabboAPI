import apipublic.Rooms;
import apipublic.Users;
import imager.HabboImager;
import hotel.Hotel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        Users.PublicUser user = Users.getUserByName(Hotel.NL, "Kouris");
    }
}

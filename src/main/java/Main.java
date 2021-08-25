import apipublic.Rooms;
import apipublic.Users;
import imager.HabboImager;
import hotel.Hotel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedImage img = HabboImager
                .getAvatarImageBuilder(Hotel.NL, "Kouris")
                .setDrinking(HabboImager.AvatarBuilder.HandItem.NOTHING)
                .setExpression(HabboImager.AvatarBuilder.AvatarExpression.SLEEPING)
                .buildImage();
        System.out.println(img);
        ImageIO.write(img, "png", new File("src/main/resources/kouris.png"));
    }
}

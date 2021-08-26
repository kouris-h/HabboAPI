package imager;

import fetch.Fetcher;
import hotel.Hotel;

import java.awt.image.BufferedImage;

public class HabboImager {

    /**
     * Get a group/guild badge as an image
     * @param badgeCode Group/guild badge code
     * @return Group/guild badge image
     */
    public static BufferedImage getGuildBadge(String badgeCode) {
        return Fetcher.fetchImage(String.format("https://www.habbo.com/habbo-imaging/badge/%s.gif", badgeCode));
    }

    public static AvatarBuilder getAvatarImageBuilder(Hotel hotel, String username) {
        return new AvatarBuilder().setUser(hotel, username);
    }

    public static AvatarBuilder getAvatarImageBuilder(String figure) {
        return new AvatarBuilder().setFigure(figure);
    }

    public static class AvatarBuilder {
        private String user, figure;
        private Hotel hotel = Hotel.COM;
        private AvatarSize size = AvatarSize.MEDIUM;
        private AvatarAction action = AvatarAction.NOTHING;
        private AvatarExpression expression = AvatarExpression.NORMAL;
        private HandItem handItem = HandItem.NOTHING;
        private AvatarDirection bodyDirection = AvatarDirection.SOUTHWEST, headDirection = AvatarDirection.SOUTHWEST;

        private AvatarBuilder() {}

        private AvatarBuilder setUser(Hotel hotel, String user) {
            this.figure = null;
            this.hotel = hotel;
            this.user = user;
            return this;
        }

        private AvatarBuilder setFigure(String figure) {
            this.figure = figure;
            this.hotel = Hotel.COM;
            this.user = null;
            return this;
        }

        public AvatarBuilder setSize(AvatarSize size) {
            this.size = size;
            return this;
        }

        public AvatarBuilder setBodyDirection(AvatarDirection direction) {
            this.bodyDirection = direction;
            return this;
        }

        public AvatarBuilder setHeadDirection(AvatarDirection direction) {
            this.headDirection = direction;
            return this;
        }

        public AvatarBuilder setAction(AvatarAction action) {
            this.action = action;
            this.handItem = HandItem.NOTHING;
            return this;
        }

        public AvatarBuilder setExpression(AvatarExpression expression) {
            this.expression = expression;
            return this;
        }

        public AvatarBuilder setDrinking(HandItem handItem) {
            setAction(AvatarAction.DRINKING);
            this.handItem = handItem;
            return this;
        }

        public AvatarBuilder setHolding(HandItem handItem) {
            setAction(AvatarAction.HOLDING);
            this.handItem = handItem;
            return this;
        }

        public BufferedImage buildImage() {
            String url = String.format("%shabbo-imaging/avatarimage?", hotel) + (user != null ? String.format("user=%s", user) : String.format("figure=%s", figure)) +
                    String.format("&size=%s", size) +
                    String.format("&direction=%s", bodyDirection) +
                    String.format("&head_direction=%s", headDirection) +
                    String.format("&action=%s", action + (handItem != HandItem.NOTHING ? String.format("=%s", handItem) : "")) +
                    String.format("&gesture=%s", expression);
            System.out.println(url);
            return Fetcher.fetchImage(url);
        }

        public enum AvatarExpression {
            NORMAL ("nrm"),
            HAPPY ("sml"),
            SAD ("sad"),
            ANGRY ("agr"),
            SURPRISED ("srp"),
            SLEEPING ("eyb"),
            SPEAKING ("spk");

            private final String expression;

            AvatarExpression(String expression) {
                this.expression = expression;
            }

            @Override
            public String toString() {
                return expression;
            }
        }

        public enum AvatarAction {
            NOTHING (""),
            WALKING ("wlk"),
            LAYING ("lay"),
            SITING ("sit"),
            WAVING ("wav"),
            HOLDING ("crr"),
            DRINKING ("drk");

            private final String action;

            AvatarAction(String action) {
                this.action = action;
            }

            @Override
            public String toString() {
                return action;
            }
        }

        public enum HandItem {
            NOTHING (0),
            WATER (1),
            MUG (6),
            CARROT (2),
            ICE_CREAM (3),
            HABBO_COLA (5),
            COCKTAIL (667),
            LOVE_POTION (9),
            ICE_CREAM_RADIOACTIVE (33),
            JAPANESE_TEA (42),
            TOMATO_JUICE (43),
            WATER_RADIOACTIVE (44);

            private final int value;

            HandItem(int value) {
                this.value = value;
            }

            @Override
            public String toString() {
                return String.valueOf(value);
            }
        }

        public enum AvatarSize {
            SMALL ("s"),
            MEDIUM ("m"),
            LARGE ("l");

            private final String size;

            AvatarSize(String size) {
                this.size = size;
            }

            @Override
            public String toString() {
                return size;
            }
        }

        public enum AvatarDirection {
            WEST (1),
            SOUTHWEST (2),
            SOUTH (3),
            SOUTHEAST (4),
            EAST (5),
            NORTHEAST (6),
            NORTH (7),
            NORTHWEST (8);

            private final int dir;

            AvatarDirection(int dir) {
                this.dir = dir;
            }

            @Override
            public String toString() {
                return String.valueOf(dir);
            }
        }
    }

    // TODO avatar using builder
}

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

    public static AvatarBuilder getAvatarImageBuilder() {
        return new AvatarBuilder();
    }

    private static class AvatarBuilder {
        private String user, figure;
        private Hotel hotel = Hotel.COM;
        private AvatarSize size = AvatarSize.MEDIUM;
        private AvatarAction action = AvatarAction.NOTHING;
        private HandItem handItem = HandItem.NOTHING;
        private AvatarDirection bodyDirection = AvatarDirection.SOUTHWEST, headDirection = AvatarDirection.SOUTHWEST;

        public AvatarBuilder setUser(Hotel hotel, String user) {
            this.figure = null;
            this.hotel = hotel;
            this.user = user;
            return this;
        }

        public AvatarBuilder setFigure(String figure) {
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

        //TODO build/getImage

        public enum Expression {
            NORMAL ("nrm"),
            HAPPY ("sml"),
            SAD ("sad"),
            ANGRY ("agr"),
            SURPRISED ("srp"),
            SLEEPING ("eyb"),
            SPEAKING ("spk");

            private final String expression;

            Expression(String expression) {
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
            NOTHING
            // TODO
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

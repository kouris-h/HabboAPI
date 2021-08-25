package gamedata.figuredata;

import fetch.Fetcher;
import gamedata.Gamedata;
import hotel.Hotel;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class FigureData extends Gamedata {
    private Map<String, SetType> setTypesByType;
    private Map<Integer, ColorPalette> colorPalettesById;

    public FigureData() throws IOException {
        super(Hotel.SANDBOX);
        this.parseData(this.getJSONObject());
    }

    @Override
    protected JSONObject getJSONObject() throws IOException {
        return Fetcher.fetchXMLAsJSONObject(String.format("%sgamedata/figuredata/1", selectedHotel.domain));
    }

    @Override
    protected void parseData(JSONObject figuredataJson) {
        this.setTypesByType = figuredataJson
                .getJSONObject("figuredata")
                .getJSONObject("sets")
                .getJSONArray("settype") // settypes array
                .toList()
                .parallelStream()
                .map(o -> (HashMap<String, Object>) o)
                .map(JSONObject::new)
                .map(SetType::new)
                .collect(Collectors.toMap(SetType::getType, SetType::getInstance));

        this.colorPalettesById = figuredataJson
                .getJSONObject("figuredata")
                .getJSONObject("colors")
                .getJSONArray("palette")
                .toList()// colorpalette array
                .parallelStream()
                .map(o -> (HashMap<String, Object>) o)
                .map(JSONObject::new)
                .map(ColorPalette::new)
                .collect(Collectors.toMap(ColorPalette::getId, ColorPalette::getInstance));
    }

    public List<SetType> getAllSetTypes() {
        return Collections.unmodifiableList(new ArrayList<>(setTypesByType.values()));
    }

    public SetType getSetType(String type) {
        return setTypesByType.getOrDefault(type, null);
    }

    public List<ColorPalette> getAllColorPalettes() {
        return Collections.unmodifiableList(new ArrayList<>(colorPalettesById.values()));
    }

    public ColorPalette getColorPaletteById(int id) {
        return colorPalettesById.getOrDefault(id, null);
    }

    public static class SetType {
        public final String type;
        public final int paletteId;
        public final boolean isMandatoryM0, isMandatoryM1, isMandatoryF0, isMandatoryF1;
        private final Map<Integer, Set> setsById;

        private SetType(JSONObject jsonObject) {
            this.type = jsonObject.getString("type");

            this.paletteId = jsonObject.getInt("paletteid");

            this.isMandatoryM0 = jsonObject.getInt("mand_m_0") == 1;
            this.isMandatoryM1 = jsonObject.getInt("mand_m_1") == 1;
            this.isMandatoryF0 = jsonObject.getInt("mand_f_0") == 1;
            this.isMandatoryF1 = jsonObject.getInt("mand_f_1") == 1;

            this.setsById = Set.parse(jsonObject);
        }

        public List<Set> getAllSets() {
            return Collections.unmodifiableList(new ArrayList<>(setsById.values()));
        }

        public Set getSetById(int id) {
            return setsById.getOrDefault(id, null);
        }

        private String getType() {
            return type;
        }

        private SetType getInstance() {
            return this;
        }
    }

    public static class Set {
        public final int id, club;
        public final char gender;
        public final boolean isColorable, isSelectable, isPreselectable;
        private final List <Part> parts;

        private Set(JSONObject jsonObject) {
            this.id = jsonObject.getInt("id");
            this.club = jsonObject.getInt("club");

            this.gender = jsonObject.getString("gender").charAt(0);

            this.isColorable = jsonObject.getInt("colorable") == 1;
            this.isSelectable = jsonObject.getInt("selectable") == 1;
            this.isPreselectable = jsonObject.getInt("preselectable") == 1;

            this.parts = Part.parse(jsonObject);
        }

        public List<Part> getParts() {
            return Collections.unmodifiableList(this.parts);
        }

        private int getId() {
            return id;
        }

        private Set getInstance() {
            return this;
        }

        private static Map<Integer, Set> parse(JSONObject jsonObject) {
            return jsonObject
                    .getJSONArray("set")
                    .toList()
                    .parallelStream()
                    .map(o -> (HashMap<String, Object>) o)
                    .map(JSONObject::new)
                    .map(Set::new)
                    .collect(Collectors.toMap(Set::getId, Set::getInstance));
        }
    }

    public static class Part {
        public final int id, index, colorIndex;
        public final String type;
        public final boolean isColorable;

        private Part(JSONObject jsonObject) {
            this.id = jsonObject.getInt("id");
            this.index = jsonObject.getInt("index");
            this.colorIndex = jsonObject.getInt("colorindex");
            this.type =  jsonObject.getString("type");
            this.isColorable = jsonObject.getInt("colorable") == 1;
        }

        private static List<Part> parse(JSONObject jsonObject) {
            if(jsonObject.has("part")) {
                try {
                    return jsonObject
                            .getJSONArray("part")
                            .toList()
                            .parallelStream()
                            .map(o -> (HashMap<String, Object>) o)
                            .map(JSONObject::new)
                            .map(Part::new)
                            .collect(Collectors.toList());
                } catch (Exception e) {
                    return Collections.singletonList(new Part(jsonObject.getJSONObject("part")));
                }
            } else {
                return null;
            }
        }
    }

    public static class ColorPalette {
        public final int id;
        private final Map<Integer, Color> colorsById;

        private ColorPalette(JSONObject jsonObject) {
            this.id = jsonObject.getInt("id");
            this.colorsById = Color.parse(jsonObject);
        }

        public List<Color> getAllColors() {
            return Collections.unmodifiableList(new ArrayList<>(colorsById.values()));
        }

        public Color getColorById(int id) {
            return colorsById.getOrDefault(id, null);
        }

        private int getId() {
            return id;
        }

        private ColorPalette getInstance() {
            return this;
        }
    }

    public static class Color {
        public final String hex;
        public final int id, index, club;
        public final boolean isSelectable;

        private Color(JSONObject jsonObject) {
            this.hex = "" + jsonObject.get("content");
            this.id = jsonObject.getInt("id");
            this.index = jsonObject.getInt("index");
            this.club = jsonObject.getInt("club");
            this.isSelectable = jsonObject.getInt("selectable") == 1;
        }

        private int getId() {
            return id;
        }

        private Color getInstance() {
            return this;
        }

        private static Map<Integer, Color> parse(JSONObject jsonObject) {
            return jsonObject.getJSONArray("color")
                    .toList()
                    .parallelStream()
                    .map(o -> (Map<String, Object>) o)
                    .map(JSONObject::new)
                    .map(Color::new)
                    .collect(Collectors.toMap(Color::getId, Color::getInstance));
        }
    }
}

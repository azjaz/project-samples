package models.api.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class AddToCartModel {
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<CompositionModel> composition;
    private String lang;
    private String platform;
    @JsonProperty("city_id")
    private String cityId;

    public AddToCartModel(String lang, String platform, List<CompositionModel> composition, String cityId) {
        this.composition = composition;
        this.lang = lang;
        this.platform = platform;
        this.cityId = cityId;
    }

    public List<CompositionModel> getComposition() {
        return composition;
    }

    public String getLang() {
        return lang;
    }

    public String getPlatform() {
        return platform;
    }

    public String getCityId() {
        return cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddToCartModel that = (AddToCartModel) o;
        return Objects.equals(composition, that.composition) &&
                Objects.equals(lang, that.lang) &&
                Objects.equals(platform, that.platform) &&
                Objects.equals(cityId, that.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(composition, lang, platform, cityId);
    }

    @Override
    public String toString() {
        return "AddToCartModel{" +
                "composition=" + composition +
                ", lang='" + lang + '\'' +
                ", platform='" + platform + '\'' +
                ", cityId='" + cityId + '\'' +
                '}';
    }
}

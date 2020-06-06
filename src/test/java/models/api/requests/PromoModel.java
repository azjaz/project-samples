package models.api.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class PromoModel {
    @JsonProperty("city_id")
    private String cityId;
    private List<String> gifts;
    private String lang;
    private String platform;
    @JsonProperty("stock_code")
    private String stockCode;
    @JsonProperty("unauthorized_token")
    private String unauthorizedToken;

    public PromoModel(String cityId, List<String> gifts, String lang, String platform, String stockCode, String unauthorizedToken) {
        this.cityId = cityId;
        this.gifts = gifts;
        this.lang = lang;
        this.platform = platform;
        this.stockCode = stockCode;
        this.unauthorizedToken = unauthorizedToken;
    }

    public String getCityId() {
        return cityId;
    }

    public List<String> getGifts() {
        return gifts;
    }

    public String getLang() {
        return lang;
    }

    public String getPlatform() {
        return platform;
    }

    public String getStockCode() {
        return stockCode;
    }

    public String getUnauthorizedToken() {
        return unauthorizedToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoModel that = (PromoModel) o;
        return Objects.equals(cityId, that.cityId) &&
                Objects.equals(gifts, that.gifts) &&
                Objects.equals(lang, that.lang) &&
                Objects.equals(platform, that.platform) &&
                Objects.equals(stockCode, that.stockCode) &&
                Objects.equals(unauthorizedToken, that.unauthorizedToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityId, gifts, lang, platform, stockCode, unauthorizedToken);
    }

    @Override
    public String toString() {
        return "PromoModel{" +
                "cityId='" + cityId + '\'' +
                ", gifts=" + gifts +
                ", lang='" + lang + '\'' +
                ", platform='" + platform + '\'' +
                ", stockCode='" + stockCode + '\'' +
                ", unauthorizedToken='" + unauthorizedToken + '\'' +
                '}';
    }
}

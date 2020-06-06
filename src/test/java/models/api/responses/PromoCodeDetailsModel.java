package models.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

public class PromoCodeDetailsModel {
    private String minOrderSum;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<PromoCodeConditionsModel> conditions;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<RewardsModel> rewards;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Object> selected;

    @JsonProperty("min_order_sum")
    public String getMinOrderSum() {
        return minOrderSum;
    }

    public List<PromoCodeConditionsModel> getConditions() {
        return conditions;
    }

    public List<RewardsModel> getRewards() {
        return rewards;
    }

    public List<Object> getSelected() {
        return selected;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PromoCodeDetailsModel that = (PromoCodeDetailsModel) o;
        return Objects.equals(minOrderSum, that.minOrderSum) &&
                Objects.equals(conditions, that.conditions) &&
                Objects.equals(rewards, that.rewards) &&
                Objects.equals(selected, that.selected);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minOrderSum, conditions, rewards, selected);
    }

    @Override
    public String toString() {
        return "PromoCodeDetailsModel{" +
                "minOrderSum='" + minOrderSum + '\'' +
                ", conditions=" + conditions +
                ", rewards=" + rewards +
                ", selected=" + selected +
                '}';
    }

}

package models.api.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class RewardsModel {
    private String hash = null;
    private String reward;
    private String type;
    @JsonProperty("reward_value")
    private String rewardValue;
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<FreePizzaModel> goods;
    private boolean applied;
    private String message;

    public String getRewardValue() {
        return rewardValue;
    }

    public String getHash() {
        return hash;
    }

    public String getReward() {
        return reward;
    }

    public String getType() {
        return type;
    }

    public List<FreePizzaModel> getGoods() {
        return goods;
    }

    public boolean isApplied() {
        return applied;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RewardsModel that = (RewardsModel) o;
        return applied == that.applied &&
                Objects.equals(hash, that.hash) &&
                Objects.equals(reward, that.reward) &&
                Objects.equals(type, that.type) &&
                Objects.equals(rewardValue, that.rewardValue) &&
                Objects.equals(goods, that.goods) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hash, reward, type, rewardValue, goods, applied, message);
    }

    @Override
    public String toString() {
        return "RewardsModel{" +
                "hash='" + hash + '\'' +
                ", reward='" + reward + '\'' +
                ", type='" + type + '\'' +
                ", rewardValue='" + rewardValue + '\'' +
                ", goods=" + goods +
                ", applied=" + applied +
                ", message='" + message + '\'' +
                '}';
    }
}

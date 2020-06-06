package models.api.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Sailplay {
        @JsonProperty("statuses")
        private List<Status> statuses = new ArrayList<>();
    @JsonProperty("type")
    private String type;


    @JsonProperty("statuses")
    public List<Status> getStatuses() {
        return statuses;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sailplay sailplay = (Sailplay) o;
        return Objects.equals(statuses, sailplay.statuses) &&
                Objects.equals(type, sailplay.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(statuses, type);
    }

    @Override
    public String toString() {
        return "Sailplay{" +
                "statuses=" + statuses +
                ", type='" + type + '\'' +
                '}';
    }
}

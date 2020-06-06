package models.api.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LinkModel {

    private String name;
    private String link;
    private Integer position;

    @JsonProperty("name")
    public String getName() {
            return name;
    }

    public String getLink() {
        return link;
    }

    public Integer getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkModel linkModel = (LinkModel) o;
        return Objects.equals(name, linkModel.name) &&
                Objects.equals(link, linkModel.link) &&
                Objects.equals(position, linkModel.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link, position);
    }

    @Override
    public String toString() {
        return "LinkModel{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", position=" + position +
                '}';
    }
}

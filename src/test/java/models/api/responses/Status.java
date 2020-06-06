package models.api.responses;

import java.util.Objects;

public class Status {
        private String name;
        private String description;
        private int percent;

        public String getName() {
                return name;
        }

        public String getDescription() {
                return description;
        }

        public int getPercent() {
                return percent;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Status status = (Status) o;
                return percent == status.percent &&
                        Objects.equals(name, status.name) &&
                        Objects.equals(description, status.description);
        }

        @Override
        public int hashCode() {
                return Objects.hash(name, description, percent);
        }

        @Override
        public String toString() {
                return "Status{" +
                        "name='" + name + '\'' +
                        ", description='" + description + '\'' +
                        ", percent=" + percent +
                        '}';
        }
}

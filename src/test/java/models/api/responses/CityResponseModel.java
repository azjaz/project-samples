package models.api.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityResponseModel {

        @JsonProperty("id")
        private int id;
        @JsonProperty("_supertag")
        private String supertag;
        @JsonProperty("name")
        private String name;
        @JsonProperty("parent_id")
        private int parentId;
        @JsonProperty("cv_email")
        private String cvEmail;
        private String deliveryBalloon;
        @JsonProperty("service_phone")
        private Object servicePhone;
        @JsonProperty("contact_email")
        private Object contactEmail;
        @JsonProperty("help_number")
        private String helpNumber;
        private String yandexMetrika;
        private List<String> coordinates = null;
        @JsonProperty("seo_links")
        private String seoLinks;
        @JsonProperty("declension")
        private String declension;
        @JsonProperty("points_burning_period")
        private int pointsBurningPeriod;
        @JsonProperty("hide_burning_text")
        private int hideBurningText;
        @JsonProperty("suburb_kladr")
        private String suburbKladr;
        @JsonProperty("zoom")
        private int zoom;
        @JsonProperty("apple_pay_extended")
        private int applePayExtended;
        @JsonProperty("title")
        private String title;
        @JsonProperty("content")
        private String content;
        @JsonProperty("sailplay")
        private Sailplay sailplay;
        @JsonProperty("country_id")
        private String countryId;
        @JsonProperty("country_code")
        private String countryCode;
        @JsonProperty("suggest_service")
        private String suggestService;
        @JsonProperty("currency")
        private Currency currency = null;
        @JsonProperty("links")
        private List<Link> links = null;
        @JsonProperty("pages")
        private List<String> pages = new ArrayList<>();

        @JsonProperty("id")
        public int getId() {
            return id;
        }

        @JsonProperty("_supertag")
        public String getSupertag() {
            return supertag;
        }

        @JsonProperty("name")
        public String getName() {
            return name;
        }

        @JsonProperty("parent_id")
        public int getParentId() {
            return parentId;
        }

        @JsonProperty("cv_email")
        public String getCvEmail() {
            return cvEmail;
        }

        @JsonProperty("delivery_balloon")
        public String getDeliveryBalloon() {
            return deliveryBalloon;
        }

        @JsonProperty("service_phone")
        public Object getServicePhone() {
            return servicePhone;
        }

        @JsonProperty("contact_email")
        public Object getContactEmail() {
            return contactEmail;
        }

        @JsonProperty("help_number")
        public String getHelpNumber() {
            return helpNumber;
        }

        @JsonProperty("yandex_metrika")
        public String getYandexMetrika() {
            return yandexMetrika;
        }

        @JsonProperty("coordinates")
        public List<String> getCoordinates() {
            return coordinates;
        }

        @JsonProperty("seo_links")
        public String getSeoLinks() {
            return seoLinks;
        }

        @JsonProperty("declension")
        public String getDeclension() {
            return declension;
        }

        @JsonProperty("points_burning_period")
        public int getPointsBurningPeriod() {
            return pointsBurningPeriod;
        }

        @JsonProperty("hide_burning_text")
        public int getHideBurningText() {
            return hideBurningText;
        }

        @JsonProperty("suburb_kladr")
        public String getSuburbKladr() {
            return suburbKladr;
        }

        @JsonProperty("zoom")
        public int getZoom() {
            return zoom;
        }

        @JsonProperty("apple_pay_extended")
        public int getApplePayExtended() {
            return applePayExtended;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("content")
        public String getContent() {
            return content;
        }

        @JsonProperty("sailplay")
        public Sailplay getSailplay() {
            return sailplay;
        }

        @JsonProperty("country_id")
        public String getCountryId() {
            return countryId;
        }

        @JsonProperty("country_code")
        public String getCountryCode() {
            return countryCode;
        }

        @JsonProperty("suggest_service")
        public String getSuggestService() {
            return suggestService;
        }

        @JsonProperty("currency")
        public Currency getCurrency() {
            return currency;
        }

        @JsonProperty("links")
        public List<Link> getLinks() {
                return links;
        }

        @JsonProperty("pages")
        public List<String> getPages() {
                return pages;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                CityResponseModel model = (CityResponseModel) o;
                return id == model.id &&
                        parentId == model.parentId &&
                        pointsBurningPeriod == model.pointsBurningPeriod &&
                        hideBurningText == model.hideBurningText &&
                        zoom == model.zoom &&
                        applePayExtended == model.applePayExtended &&
                        Objects.equals(supertag, model.supertag) &&
                        Objects.equals(name, model.name) &&
                        Objects.equals(cvEmail, model.cvEmail) &&
                        Objects.equals(deliveryBalloon, model.deliveryBalloon) &&
                        Objects.equals(servicePhone, model.servicePhone) &&
                        Objects.equals(contactEmail, model.contactEmail) &&
                        Objects.equals(helpNumber, model.helpNumber) &&
                        Objects.equals(yandexMetrika, model.yandexMetrika) &&
                        Objects.equals(coordinates, model.coordinates) &&
                        Objects.equals(seoLinks, model.seoLinks) &&
                        Objects.equals(declension, model.declension) &&
                        Objects.equals(suburbKladr, model.suburbKladr) &&
                        Objects.equals(title, model.title) &&
                        Objects.equals(content, model.content) &&
                        Objects.equals(sailplay, model.sailplay) &&
                        Objects.equals(countryId, model.countryId) &&
                        Objects.equals(countryCode, model.countryCode) &&
                        Objects.equals(suggestService, model.suggestService) &&
                        Objects.equals(currency, model.currency) &&
                        Objects.equals(links, model.links) &&
                        Objects.equals(pages, model.pages);
        }

        @Override
        public int hashCode() {
                return Objects.hash(id, supertag, name, parentId, cvEmail, deliveryBalloon, servicePhone, contactEmail, helpNumber, yandexMetrika, coordinates, seoLinks, declension, pointsBurningPeriod, hideBurningText, suburbKladr, zoom, applePayExtended, title, content, sailplay, countryId, countryCode, suggestService, currency, links, pages);
        }

        @Override
        public String toString() {
                return "CityResponseModel{" +
                        "id=" + id +
                        ", supertag='" + supertag + '\'' +
                        ", name='" + name + '\'' +
                        ", parentId=" + parentId +
                        ", cvEmail='" + cvEmail + '\'' +
                        ", deliveryBalloon='" + deliveryBalloon + '\'' +
                        ", servicePhone=" + servicePhone +
                        ", contactEmail=" + contactEmail +
                        ", helpNumber='" + helpNumber + '\'' +
                        ", yandexMetrika='" + yandexMetrika + '\'' +
                        ", coordinates=" + coordinates +
                        ", seoLinks='" + seoLinks + '\'' +
                        ", declension='" + declension + '\'' +
                        ", pointsBurningPeriod=" + pointsBurningPeriod +
                        ", hideBurningText=" + hideBurningText +
                        ", suburbKladr='" + suburbKladr + '\'' +
                        ", zoom=" + zoom +
                        ", applePayExtended=" + applePayExtended +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", sailplay=" + sailplay +
                        ", countryId='" + countryId + '\'' +
                        ", countryCode='" + countryCode + '\'' +
                        ", suggestService='" + suggestService + '\'' +
                        ", currency=" + currency +
                        ", links=" + links +
                        ", pages=" + pages +
                        '}';
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Currency {
                public Currency() {
                }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Link {
                public Link() {
                }
        }
}

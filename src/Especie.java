public class Especie {

    String commonName;
    String scientificName;
    String description;
    String location;
    String moreInfo;
    String behaviour;
    String minLength;


    public Especie(String commonName, String scientificName, String description, String location, String moreInfo, String behaviour, String minLength) {
        this.commonName = commonName;
        this.scientificName = scientificName;
        this.description = description;
        this.location = location;
        this.moreInfo = moreInfo;
        this.behaviour = behaviour;
        this.minLength = minLength;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMoreInfo() {
        return moreInfo;
    }

    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    public String getBehaviour() {
        return behaviour;
    }

    public void setBehaviour(String behaviour) {
        this.behaviour = behaviour;
    }

    public String getMinLength() {
        return minLength;
    }

    public void setMinLength(String minLength) {
        this.minLength = minLength;
    }
}

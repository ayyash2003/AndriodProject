package com.example.project;

public class Trips {
    private int id ;
    private String title ;
    private String type ;
    private String destination ;
    private String image ;
    private String startingpoint ;
    private String duration ;
    private String startdate ;
    private String enddate ;

    private double price  ;
    private String risk;
    private String description ;
    private String checkbox ;

    public Trips(int id ,String title, String type, String destination, String image, String startingpoint,String duration,String startdate,String enddate
, double price, String risk, String description, String checkbox) {
        this.id=id ;
        this.title = title;
        this.type = type;
        this.destination = destination;
        this.image = image;
        this.startingpoint = startingpoint;
        this.duration= duration ;
        this.startdate = startdate;
        this.enddate=enddate ;
        this.price = price;
        this.risk = risk;
        this.description = description;
        this.checkbox = checkbox;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStartingpoint() {
        return startingpoint;
    }

    public void setStartingpoint(String startingpoint) {
        this.startingpoint = startingpoint;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public java.lang.String getEnddate() {
        return enddate;
    }

    public void setEnddate(java.lang.String enddate) {
        this.enddate = enddate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRisk() {
        return risk;
    }

    public void setRisk(String risk) {
        this.risk = risk;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(String checkbox) {
        this.checkbox = checkbox;
    }
}

package com.example.egfootballtracker.Model;
import java.io.Serializable;

public class PlayerDetails implements Serializable {

    private String id;

    private String mImageUrl;

    private String playerName;

    private String currentAge;

    private String born;

    private String country;

    private String height;

    private String position;

    private String Apps;

    private String minutes;

    private String goals;

    private String Assist;

    private String yelCard;

    private String redCard;

    private String spg;

    private String pss;

    private String arialWon;

    private String motM;

    private String playerPerformance;

    public PlayerDetails(String id, String mImageUrl, String playerName, String currentAge,
                         String born, String country, String height, String position, String apps,
                         String minutes, String goals, String assist, String yelCard, String redCard,
                         String spg, String pss, String arialWon, String motM) {
        this.id = id;
        this.mImageUrl = mImageUrl;
        this.playerName = playerName;
        this.currentAge = currentAge;
        this.born = born;
        this.country = country;
        this.height = height;
        this.position = position;
        Apps = apps;
        this.minutes = minutes;
        this.goals = goals;
        Assist = assist;
        this.yelCard = yelCard;
        this.redCard = redCard;
        this.spg = spg;
        this.pss = pss;
        this.arialWon = arialWon;
        this.motM = motM;


    }

    public PlayerDetails(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getCurrentAge() {
        return currentAge;
    }

    public void setCurrentAge(String currentAge) {
        this.currentAge = currentAge;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getApps() {
        return Apps;
    }

    public void setApps(String apps) {
        Apps = apps;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getAssist() {
        return Assist;
    }

    public void setAssist(String assist) {
        Assist = assist;
    }

    public String getYelCard() {
        return yelCard;
    }

    public void setYelCard(String yelCard) {
        this.yelCard = yelCard;
    }

    public String getRedCard() {
        return redCard;
    }

    public void setRedCard(String redCard) {
        this.redCard = redCard;
    }

    public String getSpg() {
        return spg;
    }

    public void setSpg(String spg) {
        this.spg = spg;
    }

    public String getPss() {
        return pss;
    }

    public void setPss(String pss) {
        this.pss = pss;
    }

    public String getArialWon() {
        return arialWon;
    }

    public void setArialWon(String arialWon) {
        this.arialWon = arialWon;
    }

    public String getMotM() {
        return motM;
    }

    public void setMotM(String motM) {
        this.motM = motM;
    }


}

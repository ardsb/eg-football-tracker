package com.example.egfootballtracker.Model;

import java.io.Serializable;

public class PlayerDetails implements Serializable {
    private int id;

    private String playerName;

    private String playerAge;

    private String playerBorn;

    private String playerCountry;

    private String playerHeight;

    private String playerPosition;

    private String playerApps;

    private String playerPlayedMinutes;

    private String playerGoals;

    private String playerAssist;

    private String playerYellowCard;

    private String playerRedCard;

    private String playerSpg;

    private String playerPs;

    private String playerArialWon;

    private String playerMom;

    private String playerPerformance;


    public PlayerDetails(String playerName, String playerAge, String playerBorn, String playerCountry,
                         String playerHeight, String playerPosition, String playerApps,
                         String playerPlayedMinutes, String playerGoals, String playerAssist,
                         String playerYellowCard, String playerRedCard,
                         String playerSpg, String playerPs, String playerArialWon, String playerMom,
                         String playerPerformance) {
        this.playerName = playerName;
        this.playerAge = playerAge;
        this.playerBorn = playerBorn;
        this.playerCountry = playerCountry;
        this.playerHeight = playerHeight;
        this.playerPosition = playerPosition;
        this.playerApps = playerApps;
        this.playerPlayedMinutes = playerPlayedMinutes;
        this.playerGoals = playerGoals;
        this.playerAssist = playerAssist;
        this.playerYellowCard = playerYellowCard;
        this.playerRedCard = playerRedCard;
        this.playerSpg = playerSpg;
        this.playerPs = playerPs;
        this.playerArialWon = playerArialWon;
        this.playerMom = playerMom;
        this.playerPerformance = playerPerformance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerAge() {
        return playerAge;
    }

    public void setPlayerAge(String playerAge) {
        this.playerAge = playerAge;
    }

    public String getPlayerBorn() {
        return playerBorn;
    }

    public void setPlayerBorn(String playerBorn) {
        this.playerBorn = playerBorn;
    }

    public String getPlayerCountry() {
        return playerCountry;
    }

    public void setPlayerCountry(String playerCountry) {
        this.playerCountry = playerCountry;
    }

    public String getPlayerHeight() {
        return playerHeight;
    }

    public void setPlayerHeight(String playerHeight) {
        this.playerHeight = playerHeight;
    }

    public String getPlayerPosition() {
        return playerPosition;
    }

    public void setPlayerPosition(String playerPosition) {
        this.playerPosition = playerPosition;
    }

    public String getPlayerApps() {
        return playerApps;
    }

    public void setPlayerApps(String playerApps) {
        this.playerApps = playerApps;
    }

    public String getPlayerPlayedMinutes() {
        return playerPlayedMinutes;
    }

    public void setPlayerPlayedMinutes(String playerPlayedMinutes) {
        this.playerPlayedMinutes = playerPlayedMinutes;
    }

    public String getPlayerGoals() {
        return playerGoals;
    }

    public void setPlayerGoals(String playerGoals) {
        this.playerGoals = playerGoals;
    }

    public String getPlayerAssist() {
        return playerAssist;
    }

    public void setPlayerAssist(String playerAssist) {
        this.playerAssist = playerAssist;
    }

    public String getPlayerYellowCard() {
        return playerYellowCard;
    }

    public void setPlayerYellowCard(String playerYellowCard) {
        this.playerYellowCard = playerYellowCard;
    }

    public String getPlayerRedCard() {
        return playerRedCard;
    }

    public void setPlayerRedCard(String playerRedCard) {
        this.playerRedCard = playerRedCard;
    }

    public String getPlayerSpg() {
        return playerSpg;
    }

    public void setPlayerSpg(String playerSpg) {
        this.playerSpg = playerSpg;
    }

    public String getPlayerPs() {
        return playerPs;
    }

    public void setPlayerPs(String playerPs) {
        this.playerPs = playerPs;
    }

    public String getPlayerArialWon() {
        return playerArialWon;
    }

    public void setPlayerArialWon(String playerArialWon) {
        this.playerArialWon = playerArialWon;
    }

    public String getPlayerMom() {
        return playerMom;
    }

    public void setPlayerMom(String playerMom) {
        this.playerMom = playerMom;
    }

    public String getPlayerPerformance() {
        return playerPerformance;
    }

    public void setPlayerPerformance(String playerPerformance) {
        this.playerPerformance = playerPerformance;
    }
}

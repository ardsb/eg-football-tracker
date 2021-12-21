package com.example.egfootballtracker.Model;

import java.io.Serializable;

public class PlayerDetails implements Serializable {

    public String id;

    public String mImageUrl;

    public String playerName;

    public String currentAge;

    public String born;

    public String country;

    public String playingRole;

    public String majorTeams;

    public String battingStyle;

    public String bowlingStyle;

    private String Matches;

    private String Innings;

    private String RunsBatting;

    private String HS;

    private String Ave;

    private String SR;

    private String halfCentury;

    private String century;

    private String Sixes;

    private String fours;

    private String MatchesBowling;

    private String InningsBowling;

    private String Balls;

    private String Wkts;

    private String fourWicketsHaul;

    private String fiveWicketsHaul;


    private String AveBowling;

    private String Econ;

    public PlayerDetails(String id, String mImageUrl, String playerName, String currentAge,
                         String born, String country, String playingRole, String majorTeams,
                         String battingStyle, String bowlingStyle, String matches, String innings,
                         String runsBatting, String HS, String ave, String SR, String halfCentury,
                         String century, String sixes, String fours , String matchesBowling,
                         String inningsBowling, String balls, String wkts
            , String fourWicketsHaul, String fiveWicketsHaul, String aveBowling, String econ)  {
        this.id = id;
        this.mImageUrl = mImageUrl;
        this.playerName = playerName;
        this.currentAge = currentAge;
        this.born = born;
        this.country = country;
        this.playingRole = playingRole;
        this.majorTeams = majorTeams;
        this.battingStyle = battingStyle;
        this.bowlingStyle = bowlingStyle;
        Matches = matches;
        Innings = innings;
        RunsBatting = runsBatting;
        this.HS = HS;
        Ave = ave;
        this.SR = SR;
        this.halfCentury = halfCentury;
        this.century = century;
        Sixes = sixes;
        this.fours = fours;

        MatchesBowling = matchesBowling;
        InningsBowling = inningsBowling;
        Balls = balls;
        Wkts = wkts;
        this.fourWicketsHaul = fourWicketsHaul;
        this.fiveWicketsHaul = fiveWicketsHaul;
        AveBowling = aveBowling;
        Econ = econ;
    }

    public PlayerDetails(){

    }

    public String getId() {
        return id;
    }


    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPlayingRole() {
        return playingRole;
    }

    public void setPlayingRole(String playingRole) {
        this.playingRole = playingRole;
    }

    public String getMajorTeams() {
        return majorTeams;
    }

    public void setMajorTeams(String majorTeams) {
        this.majorTeams = majorTeams;
    }

    public String getBattingStyle() {
        return battingStyle;
    }

    public void setBattingStyle(String battingStyle) {
        this.battingStyle = battingStyle;
    }

    public String getBowlingStyle() {
        return bowlingStyle;
    }

    public void setBowlingStyle(String bowlingStyle) {
        this.bowlingStyle = bowlingStyle;
    }

    public String getMatches() {
        return Matches;
    }

    public void setMatches(String matches) {
        Matches = matches;
    }

    public String getInnings() {
        return Innings;
    }

    public void setInnings(String innings) {
        Innings = innings;
    }

    public String getRunsBatting() {
        return RunsBatting;
    }

    public void setRunsBatting(String runsBatting) {
        RunsBatting = runsBatting;
    }

    public String getHS() {
        return HS;
    }

    public void setHS(String HS) {
        this.HS = HS;
    }

    public String getAve() {
        return Ave;
    }

    public void setAve(String ave) {
        Ave = ave;
    }

    public String getSR() {
        return SR;
    }

    public void setSR(String SR) {
        this.SR = SR;
    }

    public String getHalfCentury() {
        return halfCentury;
    }

    public void setHalfCentury(String halfCentury) {
        this.halfCentury = halfCentury;
    }

    public String getCentury() {
        return century;
    }

    public void setCentury(String century) {
        this.century = century;
    }

    public String getSixes() {
        return Sixes;
    }

    public void setSixes(String sixes) {
        Sixes = sixes;
    }

    public String getFours() {
        return fours;
    }

    public void setFours(String fours) {
        this.fours = fours;
    }

    public String getMatchesBowling() {
        return MatchesBowling;
    }

    public void setMatchesBowling(String matchesBowling) {
        MatchesBowling = matchesBowling;
    }

    public String getInningsBowling() {
        return InningsBowling;
    }

    public void setInningsBowling(String inningsBowling) {
        InningsBowling = inningsBowling;
    }

    public String getBalls() {
        return Balls;
    }

    public void setBalls(String balls) {
        Balls = balls;
    }

    public String getWkts() {
        return Wkts;
    }

    public void setWkts(String wkts) {
        Wkts = wkts;
    }

    public String getFourWicketsHaul() {
        return fourWicketsHaul;
    }

    public void setFourWicketsHaul(String fourWicketsHaul) {
        this.fourWicketsHaul = fourWicketsHaul;
    }

    public String getFiveWicketsHaul() {
        return fiveWicketsHaul;
    }

    public void setFiveWicketsHaul(String fiveWicketsHaul) {
        this.fiveWicketsHaul = fiveWicketsHaul;
    }

    public String getAveBowling() {
        return AveBowling;
    }

    public void setAveBowling(String aveBowling) {
        AveBowling = aveBowling;
    }

    public String getEcon() {
        return Econ;
    }

    public void setEcon(String econ) {
        Econ = econ;
    }
}

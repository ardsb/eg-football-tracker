package com.example.egfootballtracker.Model;

import java.io.Serializable;

public class Matches implements Serializable {

    private String id;
    private String teamNameA,teamNameB;
    private String teamNameAScores,teamNameBScores,matchTime;



    public Matches(String id, String teamNameA, String teamNameB, String teamNameAScores
            , String teamNameBScores,String matchTime) {
        this.id = id;
        this.teamNameA = teamNameA;
        this.teamNameB = teamNameB;
        this.teamNameAScores = teamNameAScores;
        this.teamNameBScores = teamNameBScores;
        this.matchTime = matchTime;
    }

    public Matches(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTeamNameA() {
        return teamNameA;
    }

    public void setTeamNameA(String teamNameA) {
        this.teamNameA = teamNameA;
    }

    public String getTeamNameB() {
        return teamNameB;
    }

    public void setTeamNameB(String teamNameB) {
        this.teamNameB = teamNameB;
    }

    public String getTeamNameAScores() {
        return teamNameAScores;
    }

    public void setTeamNameAScores(String teamNameAScores) {
        this.teamNameAScores = teamNameAScores;
    }

    public String getTeamNameBScores() {
        return teamNameBScores;
    }

    public void setTeamNameBScores(String teamNameBScores) {
        this.teamNameBScores = teamNameBScores;
    }

    public String getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(String matchTime) {
        this.matchTime = matchTime;
    }
}

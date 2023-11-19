package entity;

import java.text.DecimalFormat;

import static java.lang.Math.round;

public class Player {
    private int id; // API id
    // Personal information
    private int id;
    private String firstName;
    private String lastName;
    private String birthDate; // YYYY-MM-DD
    private String country;
    private double height; // In meters
    private double weight; // In kg


    // Player information
    private String team; // Name of the player's current team
    private String position;
    private int jerseyNumber;
    private boolean active; // Whether or not the player is currently active


    // Statistic metrics
    private int gamesPlayed;
    private int points;
    private int assists;
    private int timePlayed; // How much time the player has played (In seconds)
    private int fieldGoalsMade;
    private int fieldGoalsAttempted;
    private int freeThrowsMade;
    private int freeThrowsAttempted;
    private int threePointsMade;
    private int threePointsAttempted;
    private int offensiveRebounds;
    private int defensiveRebounds;
    private int personalFouls;
    private int steals;
    private int turnovers;
    private int blocks;
    private int plusMinus;

    public Player(int id,
                  String firstName,
                  String lastName,
                  String birthDate,
                  String country,
                  double height,
                  double weight,
                  String team,
                  String position,
                  int jerseyNumber,
                  boolean active,
                  int gamesPlayed,
                  int points,
                  int assists,
                  int timePlayed,
                  int fieldGoalsMade,
                  int fieldGoalsAttempted,
                  int freeThrowsMade,
                  int freeThrowsAttempted,
                  int threePointsMade,
                  int threePointsAttempted,
                  int offensiveRebounds,
                  int defensiveRebounds,
                  int personalFouls,
                  int steals,
                  int turnovers,
                  int blocks,
                  int plusMinus
    ){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.country = country;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.active = active;
        this.gamesPlayed = gamesPlayed;
        this.points = points;
        this.assists = assists;
        this.timePlayed = timePlayed;
        this.fieldGoalsMade = fieldGoalsMade;
        this.fieldGoalsAttempted = fieldGoalsAttempted;
        this.freeThrowsMade = freeThrowsMade;
        this.freeThrowsAttempted = freeThrowsAttempted;
        this.threePointsMade = threePointsMade;
        this.threePointsAttempted = threePointsAttempted;
        this.offensiveRebounds = offensiveRebounds;
        this.defensiveRebounds = defensiveRebounds;
        this.personalFouls = personalFouls;
        this.steals = steals;
        this.turnovers = turnovers;
        this.blocks = blocks;
        this.plusMinus = plusMinus;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", country='" + country + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", team='" + team + '\'' +
                ", position='" + position + '\'' +
                ", jerseyNumber=" + jerseyNumber +
                ", active=" + active +
                ", gamesPlayed=" + gamesPlayed +
                ", points=" + points +
                ", assists=" + assists +
                ", timePlayed=" + timePlayed +
                ", fieldGoalsMade=" + fieldGoalsMade +
                ", fieldGoalsAttempted=" + fieldGoalsAttempted +
                ", freeThrowsMade=" + freeThrowsMade +
                ", freeThrowsAttempted=" + freeThrowsAttempted +
                ", threePointsMade=" + threePointsMade +
                ", threePointsAttempted=" + threePointsAttempted +
                ", offensiveRebounds=" + offensiveRebounds +
                ", defensiveRebounds=" + defensiveRebounds +
                ", personalFouls=" + personalFouls +
                ", steals=" + steals +
                ", turnovers=" + turnovers +
                ", blocks=" + blocks +
                ", plusMinus=" + plusMinus +
                '}';
    }

    // TODO: Use facade here??
    // Per game methods
    private double statPerGame(int stat){
        if (gamesPlayed != 0) {
            double perGame = (double) stat / (double) gamesPlayed;
            DecimalFormat df = new DecimalFormat("#.##"); // Round double to 2 decimal places
            return Double.valueOf(df.format(perGame));
        } else {
            return 0.0;
        }
    }

    public double pointsPerGame() { return statPerGame(points);}
    public double assistsPerGame(){ return statPerGame(assists);}

    public double fieldGoalsMadePerGame() {return statPerGame(fieldGoalsMade); }
    public double fieldGoalsAttemptedPerGame() {return statPerGame(fieldGoalsAttempted); }

    public double freeThrowsMadePerGame() {return statPerGame(freeThrowsMade); }
    public double freeThrowsAttemptedPerGame() {return statPerGame(freeThrowsAttempted); }

    public double threePointsMadePerGame() {return statPerGame(threePointsMade); }
    public double threePointsAttemptedPerGame() {return statPerGame(threePointsAttempted); }

    public double defensiveReboundsPerGame() {return statPerGame(defensiveRebounds); }

    public double offensiveReboundsPerGame() {return statPerGame(offensiveRebounds); }

    public double reboundsPerGame(){ return statPerGame(totalRebounds());}

    public double personalFoulsPerGame(){ return statPerGame(personalFouls); }

    public double stealsPerGame(){ return statPerGame(steals); }

    public double turnoversPerGame() {return statPerGame(turnovers); }

    public double blocksPerGame(){ return statPerGame(blocks);}

    public double timePlayedPerGame() {return statPerGame(timePlayed);}

    // Percentage methods
    public double fieldGoalPercentage(){
        // Return 0% if the player has not attempted any field goals
        if (fieldGoalsAttempted != 0){
            return (double)fieldGoalsMade/(double)fieldGoalsAttempted * 100;
        } else {
            return 0.0;
        }
    }

    public double freeThrowPercentage(){
        // Return 0% if the player has not attempted any free throws
        if (freeThrowsAttempted != 0){
            return (double)freeThrowsMade/(double)freeThrowsAttempted * 100;
        } else {
            return 0.0;
        }
    }

    public int id(){
        return this.id;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public double threePointPercentage(){
        // Return 0% if the player has not attempted any field goals
        if (threePointsAttempted != 0){
            return (double)threePointsMade/(double)threePointsAttempted * 100;
        } else {
            return 0.0;
        }
    }

    public int totalRebounds(){
        return defensiveRebounds + offensiveRebounds;
    }


    // All getter and setter methods
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public double getTimePlayed() {
        return timePlayed;
    }

    public void setTimePlayed(int timePlayed) {
        this.timePlayed = timePlayed;
    }

    public int getFieldGoalsMade() {
        return fieldGoalsMade;
    }

    public void setFieldGoalsMade(int fieldGoalsMade) {
        this.fieldGoalsMade = fieldGoalsMade;
    }

    public int getFieldGoalsAttempted() {
        return fieldGoalsAttempted;
    }

    public void setFieldGoalsAttempted(int fieldGoalsAttempted) {
        this.fieldGoalsAttempted = fieldGoalsAttempted;
    }

    public int getFreeThrowsMade() {
        return freeThrowsMade;
    }

    public void setFreeThrowsMade(int freeThrowsMade) {
        this.freeThrowsMade = freeThrowsMade;
    }

    public int getFreeThrowsAttempted() {
        return freeThrowsAttempted;
    }

    public void setFreeThrowsAttempted(int freeThrowsAttempted) {
        this.freeThrowsAttempted = freeThrowsAttempted;
    }

    public int getThreePointsMade() {
        return threePointsMade;
    }

    public void setThreePointsMade(int threePointsMade) {
        this.threePointsMade = threePointsMade;
    }

    public int getThreePointsAttempted() {
        return threePointsAttempted;
    }

    public void setThreePointsAttempted(int threePointsAttempted) {
        this.threePointsAttempted = threePointsAttempted;
    }

    public int getOffensiveRebounds() {
        return offensiveRebounds;
    }

    public void setOffensiveRebounds(int offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }

    public int getDefensiveRebounds() {
        return defensiveRebounds;
    }

    public void setDefensiveRebounds(int defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }

    public int getPersonalFouls() {
        return personalFouls;
    }

    public void setPersonalFouls(int personalFouls) {
        this.personalFouls = personalFouls;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getBlocks() {
        return blocks;
    }

    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }

    public int getPlusMinus() {
        return plusMinus;
    }

    public void setPlusMinus(int plusMinus) {
        this.plusMinus = plusMinus;
    }
}

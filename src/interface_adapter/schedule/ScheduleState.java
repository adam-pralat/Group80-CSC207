package interface_adapter.schedule;

import entity.Game;

import java.time.LocalDate;
import java.util.Map;

public class ScheduleState {
    private LocalDate date;
    private Map<Integer, Game> games;
    private String error;

    public ScheduleState(ScheduleState copy) { date = copy.date; games = copy.games; this.error = copy.error;}
    public ScheduleState() {}

    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {this.date = date;}

    public Map<Integer, Game> getGames() {
        return games;
    }
    public void setGames(Map<Integer, Game> games) {
        this.games = games;
    }

    public String getError() {
        return error;
    }
    public void setError(String error) { this.error = error; }
}

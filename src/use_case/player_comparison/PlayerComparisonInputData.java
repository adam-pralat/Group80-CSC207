package use_case.player_comparison;

import java.util.ArrayList;

public class PlayerComparisonInputData {
    final private int id1;
    final private int id2;
    final private int season;

    public PlayerComparisonInputData(int id1, int id2, int season) {
        if (id1 <= 0 || id2 <= 0) {
            throw new IllegalArgumentException("id cant be negative.");
        }
        this.id1 = id1;
        this.id2 = id2;
        this.season = season;
    }

    int getId1() {return id1;}

    int getId2() {return id2;}

    int getSeason() {return season;}

    ArrayList<Integer> getBothId() {
        ArrayList<Integer> Ids = new ArrayList<>();
        Ids.add(id1);
        Ids.add(id2);
        return Ids;
    }
}

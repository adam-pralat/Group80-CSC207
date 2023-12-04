package view;

import interface_adapter.home_page.HomePageController;
import interface_adapter.team_stats.TeamStatsController;
import interface_adapter.team_stats.TeamStatsViewModel;
import interface_adapter.team_stats.TeamStatsState;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

public class TeamView extends JPanel implements ActionListener, PropertyChangeListener {

    // Choose a team first and then view the stats for the team.
    public final String viewName = "team stats";
    private Map<String, Integer> teamNameToIdMap;
    public static final String[] TEAM_NAMES = {
            "Atlanta Hawks", "Boston Celtics", "Brooklyn Nets",
            "Charlotte Hornets", "Chicago Bulls", "Cleveland Cavaliers", "Dallas Mavericks",
            "Denver Nuggets", "Detroit Pistons", "Golden State Warriors", "Houston Rockets",
            "Indiana Pacers", "LA Clippers", "Los Angeles Lakers", "Memphis Grizzlies",
            "Miami Heat", "Milwaukee Bucks", "Minnesota Timberwolves", "New Orleans Pelicans",
            "New York Knicks", "Oklahoma City Thunder", "Orlando Magic", "Philadelphia 76ers",
            "Phoenix Suns", "Portland Trail Blazers", "Sacramento Kings", "San Antonio Spurs",
            "Toronto Raptors", "Utah Jazz", "Washington Wizards"
    };

    private final TeamStatsViewModel teamStatsViewModel;
    private final TeamStatsController teamStatsController;
    private final HomePageController homePageController;
    private final JComboBox<String> teamDropdown = new JComboBox<>(TEAM_NAMES);;
    private final JPanel teams = new JPanel();
    private final JButton viewStatsButton;
    private final JButton exit;

    public TeamView(TeamStatsController controller, TeamStatsViewModel teamStatsViewModel, HomePageController homePageController) {
        this.teamStatsController = controller;
        this.teamStatsViewModel = teamStatsViewModel;
        this.homePageController = homePageController;
        teamNameToIdMap = initialiseTeamNameIdMap();
        teamStatsViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Team Statistics");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel teamLabel = new JLabel("Select Team: ");
        teams.add(teamLabel);
        teams.add(teamDropdown);

        JPanel buttons = new JPanel();
        exit = new JButton(teamStatsViewModel.EXIT_BUTTON_LABEL);
        exit.addActionListener(this);
        exit.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource().equals(exit)) {
                            homePageController.execute();
                        }
                    }
                }
        );
        buttons.add(exit);

        viewStatsButton = new JButton(TeamStatsViewModel.VIEW_STATS_LABEL);
        buttons.add(viewStatsButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(teams);
        this.add(buttons);

        viewStatsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(viewStatsButton)) {
                    String teamName = teamDropdown.getSelectedItem().toString();
                    int teamId = teamNameToIdMap.get(teamName);
                    controller.execute(teamId);
                    TeamStatsState state = teamStatsViewModel.getState();
                    String[] columnNames = {"Name", "Nickname", "Code", "City", "Logo", "Conference", "Players", "Wins", "Losses",
                            "WinsPastTen", "LossesPastTen", "ConferencePlace", "Games", "FastBreakPoints",
                            "PointsInPaint", "SecondChancePoints", "PointsOffTurnovers", "Points", "FieldGoalsMade",
                            "FieldGoalsAttempted", "FreeThrowsMade", "FreeThrowsAttempted", "ThreePointsMade",
                            "ThreePointsAttempted", "OffReb", "DefReb", "Assists", "PFouls", "Steals", "Turnovers",
                            "Blocks", "PlusMinus", "PointsPerGame", "AssistsPerGame", "FieldGoalsMadePerGame",
                            "FieldGoalsAttemptedPerGame", "FreeThrowsMadePerGame", "FreeThrowsAttemptedPerGame",
                            "ThreePointsMadePerGame", "ThreePointsAttemptedPerGame", "OffensiveReboundsPerGame",
                            "DefensiveReboundsPerGame", "ReboundsPerGame", "PersonalFoulsPerGame", "StealsPerGame",
                            "TurnoversPerGame", "BlocksPerGame", "PlusMinusPerGame", "FieldGoalPercentage",
                            "FreeThrowPercentage", "ThreePointPercentage"};

                    Object[] data =
                            {
                                    state.getName(), state.getNickname(), state.getCode(), state.getCity(), state.getLogo(),
                                    state.getConference(), state.getPlayers(), state.getWins(), state.getLosses(),
                                    state.getWinsPastTen(), state.getLossesPastTen(), state.getConferencePlace(), state.getGames(),
                                    state.getFastBreakPoints(), state.getPointsInPaint(), state.getSecondChancePoints(),
                                    state.getPointsOffTurnovers(), state.getPoints(), state.getFieldGoalsMade(),
                                    state.getFieldGoalsAttempted(), state.getFreeThrowsMade(), state.getFreeThrowsAttempted(),
                                    state.getThreePointsMade(), state.getThreePointsAttempted(), state.getOffReb(),
                                    state.getDefReb(), state.getAssists(), state.getpFouls(), state.getSteals(),
                                    state.getTurnovers(), state.getBlocks(), state.getPlusMinus(), state.getPointsPerGame(),
                                    state.getAssistsPerGame(), state.getFieldGoalsMadePerGame(), state.getFieldGoalsAttemptedPerGame(),
                                    state.getFreeThrowsMadePerGame(), state.getFreeThrowsAttemptedPerGame(),
                                    state.getThreePointsMadePerGame(), state.getThreePointsAttemptedPerGame(),
                                    state.getOffensiveReboundsPerGame(), state.getDefensiveReboundsPerGame(),
                                    state.getReboundsPerGame(), state.getPersonalFoulsPerGame(), state.getStealsPerGame(),
                                    state.getTurnoversPerGame(), state.getBlocksPerGame(), state.getPlusMinusPerGame(),
                                    state.getFieldGoalPercentage() + "%", state.getFreeThrowPercentage() + "%", state.getThreePointPercentage() + "%"
                            };
                    showPopup(data, columnNames);
                }
            }
        });
    }

    public Map<String, Integer> initialiseTeamNameIdMap() {
        Map<String, Integer> initial = new HashMap<>();
        Integer[] possibleTeamIDs = {1, 2, 4, 5, 6, 7, 8, 9, 10, 11, 14, 15, 16, 17, 19, 20, 21, 22, 23, 24,
                25, 26, 27, 28, 29, 30, 31, 38, 40, 41};
        for (int i = 0; i < TEAM_NAMES.length; i++) {
            initial.put(TEAM_NAMES[i], possibleTeamIDs[i]);
        }
        // You must ensure that TEAM_NAMES and possibleTeamIDs are the same length
        // and correspond correctly before running this code.
        return initial;
    }

    //Those are the team names , and each team should have a unique id, and you could make a hashmap of teams so their ids within teamview and
    //then use that in dropdowns, then there is a method that gets the things selected in dropdown and use that to exectue team stats.



    private void showPopup(Object[] data, String[] columnNames) {
        JDialog popupDialog = new JDialog(JOptionPane.getFrameForComponent(this),
                "Team Stats for " + data[0] + " - " + data[1], true);
        popupDialog.setSize(new Dimension(1200, 400));

        JPanel contentPanel = new JPanel(new GridLayout(2, columnNames.length));
        Object[][] n = {columnNames, data};
        for (Object[] rowData : n) {
            for (Object cellData : rowData) {
                if (cellData == null || ((String) cellData).equals("null%")) {
                    JLabel label = new JLabel("N/A");
                    label.setHorizontalAlignment(JLabel.HORIZONTAL);
                    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    contentPanel.add(label);
                } else {
                    JLabel label = getjLabel((String) cellData);
                    contentPanel.add(label);
                }
            }
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        popupDialog.getContentPane().setLayout(new BorderLayout());
        popupDialog.getContentPane().add(scrollPane, BorderLayout.CENTER);

        popupDialog.setLocationRelativeTo(this);
        popupDialog.setVisible(true);
    }

    @NotNull
    private static JLabel getjLabel(String cellData) {
        JLabel label = new JLabel();

        // If logo, convert to icon
        if(cellData.contains(".png")) {
            try {
                ImageIcon teamIcon = new ImageIcon(new URL(cellData));
                Image originalImage = teamIcon.getImage();
                Image scaledImage = originalImage.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                ImageIcon teamLogo = new ImageIcon(scaledImage);

                label.setIcon(teamLogo);
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        } else{
            label.setText(cellData);
        }
        label.setHorizontalAlignment(JLabel.HORIZONTAL);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        return label;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}

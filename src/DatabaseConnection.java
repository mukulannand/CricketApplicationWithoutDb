import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {
    private  static Connection con;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cricket", "root", "Hello@1234");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    static void printAndReturnListOfTeamAndID()
    {
        String listOfTeams = "SELECT TeamID, TeamName FROM Cricket.Team ORDER BY TeamID";

        try{
            PreparedStatement stmt = con.prepareStatement(listOfTeams);
            ResultSet rs = stmt.executeQuery();

            System.out.printf("%-10s %s %n", "TeamID","TeamName");

            while(rs.next())
            {
                System.out.printf("%-10d %-10s %n",rs.getInt(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("From printAndReturnListOfTeamAndID");
            e.printStackTrace();
        }
    }

    static List<Player>getPlayingTeamDetails (int teamID)
    {
        int numberOfBatsman = 0;
        int numberOfBowler = 0;
        List<Player> playersArrayList = new ArrayList<Player>();
        String getDetails = "SELECT playerName, Role , playerID FROM Cricket.Player WHERE TeamID = ?";

        try {
            PreparedStatement stmt =  con.prepareStatement(getDetails);
            stmt.setInt(1,teamID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Player p1 = new Player(rs.getString(1), rs.getString(2),rs.getInt(3));

                if(p1.getPlayerType().equals("Batsman") && numberOfBatsman == 6);
                else if(p1.getPlayerType().equals("Bowler") && numberOfBowler == 5);
                else
                    playersArrayList.add(p1);

                if( p1.getPlayerType().equals("Batsman") ) numberOfBatsman++;
                if(p1.getPlayerType().equals("Bowler")) numberOfBowler++;
            }
        }catch (SQLException e) {
            System.out.println("From getPlayingTeamDetails");
            e.printStackTrace();
        }
        return playersArrayList;
    }

    static void insertBattingDetailsInTable_Match_Batting(List<Player> playerList, int matchID)
    {
        for(Player p1 : playerList) {

            String batsmanMatchDetails = "INSERT INTO Cricket.Match_Batting values(?,?,?,?,?,?)";

            try {
                PreparedStatement stmt = con.prepareStatement(batsmanMatchDetails);
                stmt.setInt(1,matchID);
                stmt.setInt(2, p1.getPlayerID());
                stmt.setInt(3,p1.getTotalRunScored());
                stmt.setInt(4,p1.getTotalBallPlayed());
                stmt.setInt(5,p1.getTotalFours());
                stmt.setInt(6,p1.getTotalSixes());
                stmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("From insertBattingDetailsInTable_Match_Batting");
                e.printStackTrace();
            }
        }
    }


    static void insertBowlingDetailsInTable_Match_Bowling(List<Player> playerList, int matchID)
    {
        for(Player p1 : playerList) {
            if(p1.getPlayerType().equals("Bowler"))
            {
                String bowlerMatchDetails = "INSERT INTO Cricket.Match_Bowling values(?,?,?,?,?,?)";

                try{
                    PreparedStatement stmt = con.prepareStatement(bowlerMatchDetails);
                    stmt.setInt(1, matchID);
                    stmt.setInt(2, p1.getPlayerID());
                    stmt.setDouble(3,p1.getTotalOversBowled());
                    stmt.setInt(4, p1.getTotalWicketsTaken());
                    stmt.setInt(5, p1.getTotalRunsGiven());
                    stmt.setInt(6, p1.getTotalMaidenOversBowled());
                    stmt.executeUpdate();
                } catch (SQLException e){
                    System.out.println("From insertBowlingDetailsInTable_Match_Bowling");
                    e.printStackTrace();
                }
            }
        }
    }


    static void updatePlayerBattingDetails(List<Player> playerArrayList, int teamID)
    {
        int fifty;
        int hundred;

        for(Player p1: playerArrayList){
            if(p1.getTotalRunScored()>99)
            {
                fifty = 0;
                hundred = 1;
            }
            else if(p1.getTotalRunScored()>49)
            {
                fifty = 1;
                hundred = 0;
            }
            else
            {
                fifty =0;
                hundred = 0;
            }

            String updateBatsmanDetails = "UPDATE Cricket.Player_Batting SET Matches = Matches+1, Runs = Runs +?, Fours = Fours+?, Sixes = Sixes+?, Hundreds = Hundreds+?, Fifties = Fifties+? WHERE playerID= ?";

            try {
                PreparedStatement stmt = con.prepareStatement(updateBatsmanDetails);
                stmt.setInt(1,p1.getTotalRunScored());
                stmt.setInt(2,p1.getTotalFours());
                stmt.setInt(3, p1.getTotalSixes());
                stmt.setInt(4,hundred);
                stmt.setInt(5,fifty);
                stmt.setInt(6,p1.getPlayerID());
                stmt.executeUpdate();
            } catch (SQLException e)
            {
                System.out.println("From updatePlayerBattingDetails");
                e.printStackTrace();
            }
        }
    }

    static void updatePlayerBowlingDetails(List<Player>playerArrayList, int teamID)
    {
        for (Player p1 : playerArrayList)
        {
            if(p1.getPlayerType().equals("Bowler")) {
                String updateBowlerDetails = "UPDATE Cricket.Player_Bowling SET Matches = Matches+1, Overs=Overs+?, Wickets=Wickets+?, Runs=Runs+?, Maidens=Maidens+? WHERE playerID=?";

                try {
                    PreparedStatement stmt = con.prepareStatement(updateBowlerDetails);
                    stmt.setDouble(1, p1.getTotalOversBowled());
                    stmt.setInt(2, p1.getTotalWicketsTaken());
                    stmt.setInt(3, p1.getTotalRunsGiven());
                    stmt.setInt(4, p1.getTotalMaidenOversBowled());
                    stmt.setInt(5, p1.getPlayerID());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    System.out.println("From updatePlayerBowlingDetails");
                    e.printStackTrace();
                }
            }
        }
    }

    static void updateMatchTable(Match match, int seriesID, int matchOver)
    {
        String updateMatchTable = "INSERT INTO Cricket.Match VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = con.prepareStatement(updateMatchTable);
            stmt.setInt(1,match.getMatchID());
            stmt.setInt(2,seriesID);
            stmt.setInt(3, match.getTossWon());
            stmt.setInt(4, match.getMatchWon());
            stmt.setInt(5,matchOver);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static void updateTeamDetails(int team1Id, int team2Id, int matchWon)
    {
//        System.out.println("Team1ID  "+team1Id);
//        System.out.println("Team2ID  "+team2Id);
//        System.out.println("MatchWonBy "+matchWon);

        String updateDetails = "UPDATE Cricket.Team SET MatchPlayed = MatchPlayed+1 WHERE TeamID = ?";
        String updateWon = "UPDATE Cricket.Team SET MatchWon = MatchWon+1 WHERE TeamID = ?";
        try {
            PreparedStatement stmt = con.prepareStatement(updateDetails);
            stmt.setInt(1,team1Id);
            stmt.executeUpdate();
            stmt.setInt(1, team2Id);
            stmt.executeUpdate();
            stmt = con.prepareStatement(updateWon);
            stmt.setInt(1, matchWon);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public static int getMatchID() {

        int matchID = 0;
        String getMaxMatchID = " SELECT MAX(MatchID) FROM Cricket.Match";

        try {
            PreparedStatement stmt = con.prepareStatement(getMaxMatchID);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            matchID = rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchID;
    }
}

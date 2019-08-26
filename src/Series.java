import java.util.List;
import java.util.Scanner;

public class Series {

    private static int seriesID = 0;
    private static int team1Won = 0;
    private static int team2Won = 0;
    private int noOfMatches;
    private Team team1;
    private Team team2;
    private String winnerTeam;


    public Series(int noOfMatches, Team team1, Team  team2) {
        seriesID++;
        this.noOfMatches = noOfMatches;
        this.team1 = team1;
        this.team2 = team2;
    }

    public static int getSeriesID() {
        return seriesID;
    }

    public static void setSeriesID(int seriesID) {
        Series.seriesID = seriesID;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    void playSeries()
    {

        int inningsOver = 20;
        int winnerTeamID;

        for(int i =0;i<noOfMatches;i++)
        {
            System.out.println("# # # # # #         Welcome to the "+(i+1)+"th match            # # # # # #");
            Match matchObject = new Match(team1, team2, inningsOver);
            //System.out.println(" Match Details "+ matchObject.toString());
            matchObject.resetAllMatchObjectDetails();
            matchObject.doTheTossAndSetTheTossResult(team1, team2);
            if ( team1.isTossWon() == true && team1.getTossResult().equals("Batting"))
            {
                    System.out.println("# # # # # #     Team "+team1.getTeamID() + " won the toss and elect to Bat      # # # # # #");
                    matchObject.firstInningsScoreGenerator(team1, team2);
                    matchObject.secondInningsScoreGenerator(team2,team1, matchObject.getFirstInningsScore()+1);
                    matchObject.inningsMatchScoreBoard(team1,team2, "First");
                    matchObject.inningsMatchScoreBoard(team2,team1, "Second");

                    if(matchObject.getFirstInningsScore()>matchObject.getSecondInningsScore()){
                        System.out.println("Congratulations Team " + team1.getTeamID() + " won the match!!!");
                        matchObject.setMatchWon(team1.getTeamID());
                        System.out.println("\n\n");
                        team1Won++;
                    }
                    else
                    {
                        System.out.println("Congratulations Team " + team2.getTeamID() + " won the match!!!");
                        matchObject.setMatchWon(team2.getTeamID());
                        System.out.println("\n\n");
                        team2Won++;

                    }
                    }
            else if( team1.isTossWon() == true && team1.getTossResult().equals("Bowling"))
            {
                System.out.println("# # # # # #     Team "+team1.getTeamID() + " won the toss and elect to Bowl     # # # # # #");
                matchObject.firstInningsScoreGenerator(team2, team1);
                matchObject.secondInningsScoreGenerator(team1,team2, matchObject.getFirstInningsScore()+1);
                matchObject.inningsMatchScoreBoard(team2,team1, "First");
                matchObject.inningsMatchScoreBoard(team1,team2, "Second");

                if(matchObject.getFirstInningsScore()>matchObject.getSecondInningsScore()){
                    System.out.println("Congratulations Team " + team2.getTeamID() + " won the match!!!");
                    matchObject.setMatchWon(team2.getTeamID());
                    System.out.println("\n\n");
                    team2Won++;
                }
                else
                {
                    System.out.println("Congratulations Team " + team1.getTeamID() + " won the match!!!");
                    matchObject.setMatchWon(team1.getTeamID());
                    System.out.println("\n\n");
                    team1Won++;
                }
            }
            else if ( team2.isTossWon() == true && team2.getTossResult().equals("Batting"))
            {
                System.out.println("# # # # # #     Team "+team2.getTeamID() + " won the toss and elect to bat first        # # # # # #");
                matchObject.firstInningsScoreGenerator(team2, team1);
                matchObject.secondInningsScoreGenerator(team1,team2, matchObject.getFirstInningsScore()+1);
                matchObject.inningsMatchScoreBoard(team2,team1, "First");
                matchObject.inningsMatchScoreBoard(team1,team2, "Second");

                if(matchObject.getFirstInningsScore()>matchObject.getSecondInningsScore()){
                    System.out.println("Congratulations Team " + team2.getTeamID() + " won the match!!!");
                    matchObject.setMatchWon(team2.getTeamID());
                    System.out.println("\n\n");
                    team2Won++;
                }
                else
                {
                    System.out.println("Congratulations Team " + team1.getTeamID() + " won the match!!!");
                    matchObject.setMatchWon(team1.getTeamID());
                    System.out.println("\n\n");
                    team1Won++;

                }
            }
            else if ( team2.isTossWon() == true && team2.getTossResult().equals("Bowling"))
            {
                System.out.println("# # # # # #     Team "+team2.getTeamID() + " won the toss and elect to ball first       # # # # # #");
                matchObject.firstInningsScoreGenerator(team1, team2);
                matchObject.secondInningsScoreGenerator(team2,team1, matchObject.getFirstInningsScore()+1);
                matchObject.inningsMatchScoreBoard(team1,team2, "First");
                matchObject.inningsMatchScoreBoard(team2,team1, "Second");

                if(matchObject.getFirstInningsScore()>matchObject.getSecondInningsScore()){
                    System.out.println("Congratulations Team " + team1.getTeamID() + " won the match!!!");
                    matchObject.setMatchWon(team1.getTeamID());
                    System.out.println("\n\n");
                    team1Won++;
                }
                else
                {
                    System.out.println("Congratulations Team " + team2.getTeamID() + " won the match!!!");
                    matchObject.setMatchWon(team2.getTeamID());
                    System.out.println("\n\n");
                    team2Won++;

                }
            }
            DatabaseConnection.updateMatchTable(matchObject, Series.getSeriesID(), inningsOver);
            DatabaseConnection.updateTeamDetails(team1.getTeamID(), team2.getTeamID(), matchObject.getMatchWon());
            updateDatabase(matchObject.getMatchID());

        }
        if(team1Won> team2Won)
        {
            System.out.println("\n\n\n");
            System.out.println("Congratluations to Team 1, to winning the series by: "+team1Won+"-"+team2Won);
        }
        else{
            System.out.println("\n\n\n");
            System.out.println("Congratluations to Team 2, winning the series by:"+team2Won+"-"+team1Won);
        }
        
    }



    void updateDatabase(int matchID)
    {
        DatabaseConnection.insertBattingDetailsInTable_Match_Batting(team1.getPlayersArrayList(), matchID );
        DatabaseConnection.insertBattingDetailsInTable_Match_Batting(team2.getPlayersArrayList(), matchID);

        DatabaseConnection.insertBowlingDetailsInTable_Match_Bowling(team1.getPlayersArrayList(), matchID);
        DatabaseConnection.insertBowlingDetailsInTable_Match_Bowling(team2.getPlayersArrayList(), matchID);

        DatabaseConnection.updatePlayerBattingDetails(team1.getPlayersArrayList(),matchID);
        DatabaseConnection.updatePlayerBattingDetails(team2.getPlayersArrayList(),matchID);

        DatabaseConnection.updatePlayerBowlingDetails(team1.getPlayersArrayList(),matchID);
        DatabaseConnection.updatePlayerBowlingDetails(team2.getPlayersArrayList(),matchID);


    }

    public static void main(String[] args) {

        DatabaseConnection.printAndReturnListOfTeamAndID();

        System.out.println("Enter two team ID from given list");

        System.out.println("Enter first team ID");
        Scanner scan = new Scanner(System.in);
        int team1ID = scan.nextInt();
        System.out.println("Enter second team ID");
        int team2ID = scan.nextInt();
        System.out.println("Enter number of matches in series");
        int noOfMatches = scan.nextInt();

        List<Player> playersTeam1 = DatabaseConnection.getPlayingTeamDetails(team1ID);
        Team team1 = new Team (team1ID, playersTeam1);

        List<Player> playersTeam2 = DatabaseConnection.getPlayingTeamDetails(team2ID);
        Team team2 = new Team (team2ID, playersTeam2);

        Series series1 = new Series(noOfMatches, team1, team2);
        series1.playSeries();
        
    }
}

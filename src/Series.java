public class Series {

    private static  int seriesID = 0;
    private int noOfMatches;
    private Team team1;
    private Team team2;
    private int inningsOver=0;
    private String winnerTeam;

    public Series(int noOfMatches, Team team1, Team team2, int inningsOver) {
       incrementSeriesIDByOne();
       setNoOfMatches(noOfMatches);
       setTeam1(team1);
       setTeam2(team2);
       setInningsOver(inningsOver);
    }



    String playSeries()
    {
        int team1Won = 0;
        int team2Won = 0;

        for(int i =0;i<noOfMatches;i++)
        {
            Match matchObject = new Match(team1, team2, inningsOver);
            //System.out.println(" Match Details "+ matchObject.toString());
            matchObject.resetAllMatchObjectDetails();
            matchObject.doTheTossAndSetTheTossResult(team1, team2);
            if ( team1.isTossWon() == true && team1.getTossResult().equals("Batting"))
            {
                    System.out.println(team1.getTeamName() + " won the toss and elect to Bat");
                    matchObject.firstInningsScoreGenerator(team1, team2);
                    matchObject.secondInningsScoreGenerator(team2,team1, matchObject.getFirstInningsScore()+1);
                    matchObject.inningsMatchScoreBoard(getTeam1(),getTeam2(), "First");
                    matchObject.inningsMatchScoreBoard(getTeam2(),getTeam1(), "Second");

                    if(matchObject.getFirstInningsScore()>matchObject.getSecondInningsScore()){
                        System.out.println("Congratulations " + team1.getTeamName() + " won the match!!!");
                        System.out.println("\n\n");
                        team1Won++;
                    }
                    else
                    {
                        System.out.println("Congratulations " + team2.getTeamName() + " won the match!!!");
                        System.out.println("\n\n");
                        team2Won++;

                    }
                    }
            else if( team1.isTossWon() == true && team1.getTossResult().equals("Bowling"))
            {
                System.out.println(team1.getTeamName() + " won the toss and elect to Bowl");
                matchObject.firstInningsScoreGenerator(team2, team1);
                matchObject.secondInningsScoreGenerator(team1,team2, matchObject.getFirstInningsScore()+1);
                matchObject.inningsMatchScoreBoard(getTeam2(),getTeam1(), "First");
                matchObject.inningsMatchScoreBoard(getTeam1(),getTeam2(), "Second");

                if(matchObject.getFirstInningsScore()>matchObject.getSecondInningsScore()){
                    System.out.println("Congratulations " + team2.getTeamName() + " won the match!!!");
                    System.out.println("\n\n");
                    team2Won++;
                }
                else
                {
                    System.out.println("Congratulations " + team1.getTeamName() + " won the match!!!");
                    System.out.println("\n\n");
                    team1Won++;
                }
            }
            else if ( team2.isTossWon() == true && team2.getTossResult().equals("Batting"))
            {
                System.out.println(team2.getTeamName() + " won the toss and elect to bat first");
                matchObject.firstInningsScoreGenerator(team2, team1);
                matchObject.secondInningsScoreGenerator(team1,team2, matchObject.getFirstInningsScore()+1);
                matchObject.inningsMatchScoreBoard(getTeam2(),getTeam1(), "First");
                matchObject.inningsMatchScoreBoard(getTeam1(),getTeam2(), "Second");

                if(matchObject.getFirstInningsScore()>matchObject.getSecondInningsScore()){
                    System.out.println("Congratulations " + team2.getTeamName() + " won the match!!!");
                    System.out.println("\n\n");
                    team2Won++;
                }
                else
                {
                    System.out.println("Congratulations " + team1.getTeamName() + " won the match!!!");
                    System.out.println("\n\n");
                    team1Won++;

                }
            }
            else if ( team2.isTossWon() == true && team2.getTossResult().equals("Bowling"))
            {
                System.out.println(team2.getTeamName() + " won the toss and elect to ball first");
                matchObject.firstInningsScoreGenerator(team1, team2);
                matchObject.secondInningsScoreGenerator(team2,team1, matchObject.getFirstInningsScore()+1);
                matchObject.inningsMatchScoreBoard(getTeam1(),getTeam2(), "First");
                matchObject.inningsMatchScoreBoard(getTeam2(),getTeam1(), "Second");

                if(matchObject.getFirstInningsScore()>matchObject.getSecondInningsScore()){
                    System.out.println("Congratulations " + team1.getTeamName() + " won the match!!!");
                    System.out.println("\n\n");
                    team1Won++;
                }
                else
                {
                    System.out.println("Congratulations " + team2.getTeamName() + " won the match!!!");
                    System.out.println("\n\n");
                    team2Won++;

                }
            }

            //updateDB
        }
        if(team1Won > team2Won) {
            return team1.getTeamName();
        }


        return team2.getTeamName();
    }

    public static int getSeriesID() {
        return seriesID;
    }

    public static void incrementSeriesIDByOne() {
        seriesID++;
    }

    public int getNoOfMatches() {
        return noOfMatches;
    }

    public void setNoOfMatches(int noOfMatches) {
        this.noOfMatches = noOfMatches;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getInningsOver() {
        return inningsOver;
    }

    public void setInningsOver(int inningsOver) {
        this.inningsOver = inningsOver;
    }

    public String getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(String winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    @Override
    public String toString() {
        return "Series{ " +
                "noOfMatches=" + noOfMatches +
                ", team1=" + team1 +
                ", team2=" + team2 +
                ", inningsOver=" + inningsOver +
                ", winnerTeam=' " + winnerTeam + '\'' +
                '}';
    }
}

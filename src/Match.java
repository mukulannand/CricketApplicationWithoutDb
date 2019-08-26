import java.util.concurrent.ThreadLocalRandom;

public class Match {

    private  static int matchID = 0;
    private static int MAX_OVER_PER_BOWLER = 4;
    private Team team1;
    private Team team2;
    private int overs;
    private int firstInningsScore = 0;
    private int firstInningsWicket = 0;
    private double firstInningsOverBowled = 0.0;
    private int secondInningsScore = 0;
    private int secondInningsWicket = 0;
    private double secondInningsOverBowled = 0.0;
    private int tossWon;
    private int matchWon;

    public Match(Team team1, Team team2, int overs ) {
        matchID = DatabaseConnection.getMatchID()+1;
        this.team1 = team1;
        this.team2 = team2;
        this.overs = overs;
    }

    @Override
    public String toString() {
        return "Match{" +
                "team1=" + team1 +
                ", team2=" + team2 +
                ", overs=" + overs +
                ", firstInningsScore=" + firstInningsScore +
                ", firstInningsWicket=" + firstInningsWicket +
                ", firstInningsOverBowled=" + firstInningsOverBowled +
                ", secondInningsScore=" + secondInningsScore +
                ", secondInningsWicket=" + secondInningsWicket +
                ", secondInningsOverBowled=" + secondInningsOverBowled +
                '}';
    }

    public int getTossWon() {
        return tossWon;
    }

    public void setTossWon(int tossWon) {
        this.tossWon = tossWon;
    }

    public int getMatchWon() {
        return matchWon;
    }

    public void setMatchWon(int matchWon) {
        this.matchWon = matchWon;
    }

    public int getMatchID() {
        return matchID;
    }


    public static int getMaxOverPerBowler() {
        return MAX_OVER_PER_BOWLER;
    }

    public static void setMaxOverPerBowler(int maxOverPerBowler) {
        MAX_OVER_PER_BOWLER = maxOverPerBowler;
    }

    public int getFirstInningsScore() {
        return firstInningsScore;
    }

    public void incrementFirstInningsScoreBy(int firstInningsScore) {
        this.firstInningsScore += firstInningsScore;
    }

    public int getFirstInningsWicket() {
        return firstInningsWicket;
    }

    public void incrementFirstInningsWicketByOne() {
        this.firstInningsWicket++;
    }

    public double getFirstInningsOverBowled() {
        return firstInningsOverBowled;
    }

    public void incrementFirstInningsOverBowledBy(double firstInningsOverBowled) {
        this.firstInningsOverBowled += firstInningsOverBowled;
    }

    public int getSecondInningsScore() {
        return secondInningsScore;
    }

    public void incrementSecondInningsScoreBy(int secondInningsScore) {
        this.secondInningsScore += secondInningsScore;
    }

    public int getSecondInningsWicket() {
        return secondInningsWicket;
    }

    public void incrementSecondInningsWicketByOne() {
        this.secondInningsWicket += secondInningsWicket;
    }

    public double getSecondInningsOverBowled() {
        return secondInningsOverBowled;
    }

    public void incrementSecondInningsOverBowledBy(double secondInningsOverBowled) {
        this.secondInningsOverBowled += secondInningsOverBowled;
    }

    void doTheTossAndSetTheTossResult(Team team1, Team team2)
    {
        //System.out.println("Before toss"+team1.isTossWon());
        //System.out.println("Before toss"+team2.isTossWon());
        int tossWinner = ThreadLocalRandom.current().nextInt(2);
        if(tossWinner == 0)
        {

            setTossWon(team1.getTeamID());
            team1.setTossWon(true);
            int tossResult = ThreadLocalRandom.current().nextInt(2);
            if(tossResult == 0)
                team1.setTossResult("Batting");
            else
                team1.setTossResult("Bowling");

        }
        else{
            setTossWon(team2.getTeamID());
            team2.setTossWon(true);
            int tossResult = ThreadLocalRandom.current().nextInt(2);
            if(tossResult == 0)
                team2.setTossResult("Batting");
            else
                team2.setTossResult("Bowling");
        }

        //System.out.println("After toss"+team1.isTossWon());
        //System.out.println("After toss"+team2.isTossWon());

    }



    void firstInningsScoreGenerator(Team battingTeam, Team bowlingTeam)
    {
        //System.out.println("First Innings Started");

        Player striker = battingTeam.getBatsman();
        Player nonStrikerEnd = battingTeam.getBatsman();

        for (int oversBowled = 0; oversBowled< overs && firstInningsWicket<10; oversBowled++)
        {
            Player currentBowler = bowlingTeam.getBowler();
            int scoreThisOver = 0;
            int wicketsTakenThisOver = 0;
            int runOnThisBall;

            for(int ball = 0; ball<6 && firstInningsWicket!=10; ball++)
            {


                if(striker.getPlayerType().equals("Batsman") == true)
                {
                    runOnThisBall = generateRandomScoreOnThisBallByBatsman();
                }
                else {
                    runOnThisBall = generateRandomScoreOnThisBallByBowler();
                }

                striker.increaseTotalBallPlayedByOne();

                switch (runOnThisBall)
                {
                    case 0:
                        break;
                    case 1:
                        scoreThisOver++;
                        incrementFirstInningsScoreBy(1);
                        striker.increaseTotalRunScoredBy(1);
                        Player temp = striker;
                        striker = nonStrikerEnd;
                        nonStrikerEnd = temp;
                        break;
                    case 2:
                        scoreThisOver+= 2;
                        incrementFirstInningsScoreBy(2);
                        striker.increaseTotalRunScoredBy(2);
                        break;
                    case 3:
                        scoreThisOver+= 3;
                        incrementFirstInningsScoreBy(3);
                        striker.increaseTotalRunScoredBy(3);
                        temp = striker;
                        striker = nonStrikerEnd;
                        nonStrikerEnd = temp;
                        break;
                    case 4:
                        scoreThisOver+= 4;
                        incrementFirstInningsScoreBy(4);
                        striker.increaseTotalFoursByOne();
                        striker.increaseTotalRunScoredBy(4);
                        break;
                    case 5:
                        scoreThisOver+= 5;
                        incrementFirstInningsScoreBy(5);
                        striker.increaseTotalRunScoredBy(5);
                        temp = striker;
                        striker = nonStrikerEnd;
                        nonStrikerEnd = temp;
                        break;
                    case 6:
                        scoreThisOver+= 6;
                        incrementFirstInningsScoreBy(6);
                        striker.increaseTotalRunScoredBy(6);
                        striker.increaseTotalSixesByOne();
                        break;
                    case 7:
                        wicketsTakenThisOver++;
                        firstInningsWicket++;
                        striker.setOut(true);

                        if(firstInningsWicket == 10 )
                        {
                            if( scoreThisOver > 0 )
                                currentBowler.setTotalRunsGiven(scoreThisOver);
                            else if(scoreThisOver == 0 && ball == 5)
                                currentBowler.increaseTotalMaidenOversBowledByOne();

                            currentBowler.increaseTotalWicketsTakenBy(wicketsTakenThisOver);
                            double over = (double) ball/10.0;
                            currentBowler.increaseTotalOversBowledBy(over);
                            incrementFirstInningsOverBowledBy(over);
                        }
                        else if (striker.getIsOut() == true)
                            striker = battingTeam.getBatsman();
                        break;
                }
                if(ball == 5 && firstInningsWicket != 10)
                {
                    Player temp = striker;
                    striker = nonStrikerEnd;
                    nonStrikerEnd = temp;

                    if(scoreThisOver > 0)
                        currentBowler.setTotalRunsGiven(scoreThisOver);
                    else
                        currentBowler.increaseTotalMaidenOversBowledByOne();

                    if(wicketsTakenThisOver > 0)
                        currentBowler.increaseTotalWicketsTakenBy(wicketsTakenThisOver);
                    currentBowler.increaseTotalOversBowledBy(1.0);
                    incrementFirstInningsOverBowledBy(1.0);
                }
            }

        }

//        System.out.println("First Innings Over");
//        System.out.println("\n");

    }


    void secondInningsScoreGenerator(Team battingTeam, Team bowlingTeam, int targetScore)
    {
        //System.out.println("Second Innings Started");

        Player striker = battingTeam.getBatsman();
        Player nonStrikerEnd = battingTeam.getBatsman();

        for ( int oversBowled = 0; oversBowled < overs && secondInningsWicket<10 && targetScore>secondInningsScore; oversBowled++)
        {
            Player currentBowler = bowlingTeam.getBowler();

            int scoreThisOver = 0;
            int wicketsTakenThisOver = 0;
            int runOnThisBall;

            for(int ball = 0; ball<6 && secondInningsWicket!=10 && targetScore>secondInningsScore; ball++)
            {


                if(striker.getPlayerType().equals("Batsman") == true)
                {
                    runOnThisBall = generateRandomScoreOnThisBallByBatsman();
                }
                else {
                    runOnThisBall = generateRandomScoreOnThisBallByBowler();
                }

                striker.increaseTotalBallPlayedByOne();

                switch (runOnThisBall) {
                    case 0:
                        break;
                    case 1:
                        scoreThisOver++;
                        incrementSecondInningsScoreBy(1);
                        striker.increaseTotalRunScoredBy(1);
                        Player temp = striker;
                        striker = nonStrikerEnd;
                        nonStrikerEnd = temp;
                        break;
                    case 2:
                        scoreThisOver+= 2;
                        incrementSecondInningsScoreBy(2);
                        striker.increaseTotalRunScoredBy(2);
                        break;
                    case 3:
                        scoreThisOver+= 3;
                        incrementSecondInningsScoreBy(3);
                        striker.increaseTotalRunScoredBy(3);
                        temp = striker;
                        striker = nonStrikerEnd;
                        nonStrikerEnd = temp;
                        break;
                    case 4:
                        scoreThisOver+= 4;
                        incrementSecondInningsScoreBy(4);
                        striker.increaseTotalFoursByOne();
                        striker.increaseTotalRunScoredBy(4);
                        break;
                    case 5:
                        scoreThisOver+= 5;
                        incrementSecondInningsScoreBy(5);
                        striker.increaseTotalRunScoredBy(5);
                        temp = striker;
                        striker = nonStrikerEnd;
                        nonStrikerEnd = temp;
                        break;
                    case 6:
                        scoreThisOver+= 6;
                        incrementSecondInningsScoreBy(6);
                        striker.increaseTotalRunScoredBy(6);
                        striker.increaseTotalSixesByOne();
                        break;
                    case 7:
                        wicketsTakenThisOver++;
                        secondInningsWicket++;
                        striker.setOut(true);
                        if( secondInningsWicket == 10 )
                        {
                            if( scoreThisOver > 0 )
                                currentBowler.setTotalRunsGiven(scoreThisOver);
                            else if(scoreThisOver == 0 && ball == 5)
                                currentBowler.increaseTotalMaidenOversBowledByOne();

                            currentBowler.increaseTotalWicketsTakenBy(wicketsTakenThisOver);
                            double over = (double) ball/10.0;
                            currentBowler.increaseTotalOversBowledBy(over);
                            incrementSecondInningsOverBowledBy(over);
                        }
                        else if (striker.getIsOut() == true)
                            striker = battingTeam.getBatsman();
                        break;
                }



                if (ball == 5 && secondInningsWicket != 10)
                {
                    Player temp = striker;
                    striker = nonStrikerEnd;
                    nonStrikerEnd = temp;

                    if(scoreThisOver > 0)
                        currentBowler.setTotalRunsGiven(scoreThisOver);
                    else
                        currentBowler.increaseTotalMaidenOversBowledByOne();

                    if(wicketsTakenThisOver>0)
                        currentBowler.increaseTotalWicketsTakenBy(wicketsTakenThisOver);
                    currentBowler.increaseTotalOversBowledBy(1.0);
                    incrementSecondInningsOverBowledBy(1.0);
                }
                //System.out.println("Current Bowler "+currentBowler.getPlayerName()+" This over run "+ scoreThisOver+" Wicket this over " + wicketsTakenThisOver);
                if(secondInningsScore>=targetScore)
                {
                    double over = (double) ball/10.0;
                    currentBowler.increaseTotalOversBowledBy(over);
                    currentBowler.setTotalRunsGiven(scoreThisOver);
                    incrementSecondInningsOverBowledBy(over);

                }
            }
            //System.out.println("\n");

        }
//        System.out.println("Second Innings Over");
//        System.out.println("\n\n");

    }

    void inningsMatchScoreBoard(Team batting, Team bowling, String innings) {

        System.out.println(" # # # # # #  This is " + innings + " innings scoreboard # # # # # # ");
        if (innings.equals("First"))
        {
            System.out.println("               Score of " + innings + " inning is " + getFirstInningsScore());
            System.out.println("               Over Bowled in first innings:      " + getFirstInningsOverBowled());
            System.out.println("               Wickets fallen in first innings:   " + getFirstInningsWicket());
        }
        else{
        System.out.println("               Score of " + innings + " inning is " + getSecondInningsScore());
        System.out.println("               Over Bowled in second innings:      " + getSecondInningsOverBowled());
        System.out.println("               Wickets fallen in second innings:   " + getSecondInningsWicket());
        }

        System.out.println("\n");
        System.out.printf("%-18s %15s %10s %6s %8s %n"," Batsman Name " , " Runs Scored " , " Balls Played " , " Fours " , " Sixes ");

        for(Player p1 : batting.getPlayersArrayList())
        {
                System.out.printf("%-20s %10d %10d %10d %10d %n", p1.getPlayerName(), p1.getTotalRunScored(), p1.getTotalBallPlayed(), p1.getTotalFours(), p1.getTotalSixes());

        }
        System.out.println("\n");
        System.out.printf("%-10s %25s %10s %8s %12s %n","Bowler Name", "OversBowled","Maidens", "Runs" , "Wickets");

        for(Player p1: bowling.getPlayersArrayList())
        {
            if(p1.getPlayerType().equals("Bowler")) {
                System.out.printf("%-20s %13.1f %10d %10d %10d %n", p1.getPlayerName(), p1.getTotalOversBowled(), p1.getTotalMaidenOversBowled(), p1.getTotalRunsGiven(), p1.getTotalWicketsTaken());
            }
        }
        System.out.println("\n");
    }


    private int generateRandomScoreOnThisBallByBatsman() {
        int randomNumber = ThreadLocalRandom.current().nextInt(100);
        if(randomNumber >= 0 && randomNumber<25)
            return 0;
        else if(randomNumber>=25 && randomNumber<50)
            return  1;
        else if(randomNumber>=50 && randomNumber<65)
            return  2;
        else if(randomNumber>=65 && randomNumber<69)
            return  3;
        else if(randomNumber>=69 && randomNumber<81)
            return  4;
        else if(randomNumber>=81 && randomNumber<85)
            return  5;
        else if(randomNumber>=85 && randomNumber<95)
            return  6;
        else
            return 7;
    }

    private int generateRandomScoreOnThisBallByBowler() {
        int randomNumber = ThreadLocalRandom.current().nextInt(100);
        if(randomNumber >= 0 && randomNumber<15)
            return 0;
        else if(randomNumber>=15 && randomNumber<25)
            return  1;
        else if(randomNumber>=25 && randomNumber<30)
            return  2;
        else if(randomNumber>=30 && randomNumber<35)
            return  3;
        else if(randomNumber>=35 && randomNumber<46)
            return  4;
        else if(randomNumber>=46 && randomNumber<50)
            return  5;
        else if(randomNumber>=50 && randomNumber<55)
            return  6;
        else
            return 7;
    }

    public void resetAllMatchObjectDetails() {
        team1.resetTeamObjectDetails();
        team2.resetTeamObjectDetails();
        firstInningsScore = 0;
        firstInningsWicket = 0;
        firstInningsOverBowled = 0.0;
        secondInningsScore = 0;
        secondInningsWicket = 0;
        secondInningsOverBowled = 0.0;

    }
}

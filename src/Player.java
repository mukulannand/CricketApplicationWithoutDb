class Player {

    private int playerID;
    private String playerName;
    private String playerType;
    private int totalRunScored = 0;
    private int totalBallPlayed = 0;
    private int totalFours = 0;
    private int totalSixes = 0;
    private boolean isOut = false;
    private int totalRunsGiven = 0;
    private double totalOversBowled = 0.0;
    private int totalWicketsTaken = 0;
    private int totalMaidenOversBowled = 0;




    public Player(String playerName, String playerType, int playerID) {
        setPlayerName(playerName);
        setPlayerType(playerType);
        setPlayerID(playerID);
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", playerType='" + playerType + '\'' +
                ", totalRunScored=" + totalRunScored +
                ", totalBallPlayed=" + totalBallPlayed +
                ", totalFours=" + totalFours +
                ", totalSixes=" + totalSixes +
                ", isOut=" + isOut +
                ", totalRunsGiven=" + totalRunsGiven +
                ", totalOversBowled=" + totalOversBowled +
                ", totalWicketsTaken=" + totalWicketsTaken +
                ", totalMaidenOversBowled=" + totalMaidenOversBowled +
                '}';
    }

    public int getPlayerID() {
        return playerID;
    }

    private void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName =  playerName;
    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public int getTotalRunScored() {
        return totalRunScored;
    }


    public void increaseTotalRunScoredBy(int totalRunScored) {
        this.totalRunScored += totalRunScored;
    }

    public int getTotalBallPlayed() {
        return totalBallPlayed;
    }

    public void increaseTotalBallPlayedByOne() {
        totalBallPlayed++;
    }

    public int getTotalFours() {
        return totalFours;
    }

    public void increaseTotalFoursByOne() {
        totalFours++;
    }

    public int getTotalSixes() {
        return totalSixes;
    }

    public void increaseTotalSixesByOne() {
        totalSixes++;
    }

    public boolean getIsOut() {
        return isOut;
    }

    public void setOut(boolean out) {
        isOut = out;
    }

    public int getTotalRunsGiven() {
        return totalRunsGiven;
    }

    public void setTotalRunsGiven(int runsGiven) {
        this.totalRunsGiven += runsGiven;
    }

    public double getTotalOversBowled() {
        return totalOversBowled;
    }

    public void increaseTotalOversBowledBy(double oversBowled) {
        this.totalOversBowled += oversBowled;
    }

    public int getTotalWicketsTaken() {
        return totalWicketsTaken;
    }

    public void increaseTotalWicketsTakenBy(int wicketsTakenThisOver) {
        totalWicketsTaken += wicketsTakenThisOver;
    }

    public int getTotalMaidenOversBowled() {
        return totalMaidenOversBowled;
    }

    public void increaseTotalMaidenOversBowledByOne() {
        totalMaidenOversBowled++;
    }

    public void resetPlayerObjectDetails() {
        totalRunScored = 0;
        totalBallPlayed = 0;
        totalFours = 0;
        totalSixes = 0;
        isOut = false;
        totalRunsGiven = 0;
        totalOversBowled = 0.0;
        totalWicketsTaken = 0;
        totalMaidenOversBowled = 0;
    }
}

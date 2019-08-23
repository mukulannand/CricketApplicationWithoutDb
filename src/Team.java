import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Team {

    private String teamName;
    private List <Player> PlayersArrayList = new ArrayList<Player>();
    private boolean isMatchWon = false;
    private int allBatsmanOut = 0;
    private boolean tossWon = false;
    private String tossResult = null;


    public Team ( String teamName, List<Player> PlayersArrayList ) {
        setTeamName ( teamName );
        setPlayersArrayList(PlayersArrayList);
    }

    @Override
    public String toString() {
        return "Team { " +
                "teamName='" + teamName + '\'' +
                ", PlayersArrayList = " + PlayersArrayList +
                ", isMatchWon = " + isMatchWon +
                " }";
    }

    public boolean isTossWon() {
        return tossWon;
    }

    public void setTossWon(boolean tossWon) {
        this.tossWon = tossWon;
    }

    public String getTossResult() {
        return tossResult;
    }

    public void setTossResult(String tossResult) {
        this.tossResult = tossResult;
    }

    public Player getBowler()
    {
        while(true)
        {
            int bowlerIndex = ThreadLocalRandom.current().nextInt(PlayersArrayList.size());
            //System.out.println("Bowler index:  " + bowlerIndex);
            //System.out.println("Over Bowled " + (int) PlayersArrayList.get(bowlerIndex).getTotalOversBowled());
            //System.out.println("Player type " + PlayersArrayList.get(bowlerIndex).getPlayerType());
            //System.out.println((int) PlayersArrayList.get(bowlerIndex).getTotalOversBowled() == Match.getMaxOverPerBowler());
            if (PlayersArrayList.get(bowlerIndex).getTotalOversBowled() == Match.getMaxOverPerBowler()) ;
            else if (PlayersArrayList.get(bowlerIndex).getPlayerType().equals("Batsman")) ;
            else {
                //System.out.println("Returned playerIndex  "+ bowlerIndex+" PLayerName "+ PlayersArrayList.get(bowlerIndex).getPlayerName());
                return PlayersArrayList.get(bowlerIndex);
            }
        }
    }

    public  Player getBatsman(){

        while(true)
        {
            int batsmanIndex = ThreadLocalRandom.current().nextInt(PlayersArrayList.size());
            if(PlayersArrayList.get(batsmanIndex).getIsOut() == true );
                //System.out.println("Batsman loop main hu");
            else {
                return PlayersArrayList.get(batsmanIndex);
            }
        }

    }



    public String getTeamName() {
        return teamName;
    }

    public int getAllBatsmanOut() {
        return allBatsmanOut;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<Player> getPlayersArrayList() {
        return PlayersArrayList;
    }

    public void setPlayersArrayList(List<Player> PlayersArrayList) {
        this.PlayersArrayList = PlayersArrayList;
    }

    public boolean isMatchWon() {
        return isMatchWon;
    }

    public void setMatchWon(boolean matchWon) {
        isMatchWon = matchWon;
    }

    public void resetTeamObjectDetails() {
        allBatsmanOut = 0;
        setTossWon(false);
        setTossResult(null);
        for(Player p1 : getPlayersArrayList()){
            //System.out.println(p1.getPlayerName());
            //System.out.println("\n");
            p1.resetPlayerObjectDetails();
        }

    }
}

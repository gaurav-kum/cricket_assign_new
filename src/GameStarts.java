import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameStarts {
    public static void main(String[] args) {
        Batsman sachin = new Batsman("Sachin Tendulkar", 32);
        Batsman gautam = new Batsman("Gautam Gambhir", 24);
        Batsman anil = new Batsman("Anil Kumbley", 25);
        Batsman sehwag = new Batsman("Virendra Sehwag", 30);

        Bowler praveen = new Bowler("Praveen Kumar", 22);
        Bowler irfan = new Bowler("Irfan Pathan", 27);
        Bowler harbhajan = new Bowler("Harbhajan Singh", 21);
        Bowler ishan = new Bowler("Ishan Sharma", 19);

        List<Player> deccanCharges = new ArrayList<>(Arrays.asList(sachin, gautam, praveen, irfan));
        List<Player> rajasthanRoyals = new ArrayList<>(Arrays.asList(anil, sehwag, harbhajan, ishan));

        Team dc = new Team("Deccan Charges", deccanCharges);
        Team rr = new Team("Rajasthan Royals", rajasthanRoyals);

        Match match = new Match(dc, rr, 10);
        match.startMatch();
        match.displayResult();
    }

}

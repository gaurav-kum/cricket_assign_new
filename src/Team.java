import java.util.List;

public class Team {
    private final String name;
    private final List<Player> players;
    private int teamScore;
    private int ballsPlayed;

    Team(String name, List<Player> players) {
        this.name = name;
        this.players = players;
    }

    // ?
    public void firstInning(int totalBalls) {
        play(totalBalls, Integer.MAX_VALUE);
    }

    public void secondInning(int totalBalls, int target) {
        play(totalBalls, target);
    }

    // private?
    private void play(int totalBalls, int target) {
        for(Player p: players) {
            while(ballsPlayed < totalBalls && teamScore < target) {
                ballsPlayed++;
                int run = p.playTheBall();
                // return -1 if out
                if(run == -1) break;
                else teamScore += run;
            }
            p.batsmanScore();
        }
    }

    public String getName() {
        return name;
    }
    public int getTeamScore() {
        return teamScore;
    }
    public int getBallsPlayed() {
        return ballsPlayed;
    }

    // ?
    private String overPlayed() {
        return String.format("%d.%d", ballsPlayed/6, ballsPlayed%6);
    }

    public void teamDisplay() {
        System.out.printf("Team: %s\tScore: %d (%s)\n", name, teamScore, overPlayed());
    }

}

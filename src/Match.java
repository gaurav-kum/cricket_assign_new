public class Match {
    private final Team team1;
    private final Team team2;
    private final int overs;

    public Match(Team team1, Team team2, int overs) {
        this.team1 = team1;
        this.team2 = team2;
        this.overs = overs;
    }

    private int toBalls(int overs) {
        return 6 * overs;
    }
    public void startMatch() {
        // half probability
        int toss = (int)(Math.random() * 2);
        System.out.printf("Team %s won the toss and decided to bat\n", (toss == 0 ? team1.getName() : team2.getName()));
        if(toss == 0) {
            // team1 bat first
            playMatch(team1, team2);
        } else {
            playMatch(team2, team1);
        }
    }
    private void playMatch(Team t1, Team t2) {
        System.out.println("Batting Team: " + t1.getName());
        t1.firstInning(toBalls(overs));
        t1.teamDisplay();
        System.out.println();

        System.out.println("Batting Team: " + t2.getName());
        t2.secondInning(toBalls(overs), t1.getTeamScore() + 1);
        t2.teamDisplay();
        System.out.println();
    }


    public void displayResult() {
        if(team1.getTeamScore() > team2.getTeamScore()) {
            System.out.printf("%s won!\n", team1.getName());
        } else if(team1.getTeamScore() < team2.getTeamScore()) {
            System.out.printf("%s won!\n", team2.getName());
        } else {
            System.out.printf("Draw.\n");
        }
    }
}

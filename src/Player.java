public abstract class Player {
    private final String name;
    private final int age;
    private int totalRuns;
    private int ballsPlayed;
    private String type;

//    constructor
    Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.totalRuns = 0;
        this.ballsPlayed = 0;
    }

//    getters
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public int getTotalRuns() {
        return totalRuns;
    }
    public int getBallsPlayed() {
        return ballsPlayed;
    }
    public String getType() {
        return type;
    }

//    setters
    public void setType(String type) {
        this.type = type;
    }
    public void setTotalRuns(int totalRuns) {
        this.totalRuns = totalRuns;
    }
    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

    public void batsmanScore() {
//        using printf function for better format
        System.out.printf("%15s (%s) : %3d (%d) \n ", getName(), getType(), getTotalRuns(), getBallsPlayed());
    }

//    implemented in batsman and bowler
    abstract public boolean isOut();

    private int scoreRun() {
//        using random function
        return (int)(Math.random()*7);
    }

//    bat a bowl
    public int playTheBall() {
        ballsPlayed += 1;
        if(isOut())
            return -1;
        // using scoreRun method
        int run = scoreRun();
        totalRuns += run;
        return run;
    }
}

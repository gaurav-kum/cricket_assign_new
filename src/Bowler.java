public class Bowler extends Player {
    Bowler(String name, int age) {
        super(name, age);
        setType("Bowler");
    }

    @Override
    public boolean isOut() {
        // less chances
        int chances = 10;
        return (int)(Math.random()*chances)==0;
    }
}

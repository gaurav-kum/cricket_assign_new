public class Batsman extends Player {
    Batsman(String name, int age) {
        super(name,age);
        setType("batsman");
    }

    @Override
    public boolean isOut() {
        // more chance than bowler
        int chances = 20;
        return (int)(Math.random()*chances)==0;
    }

}

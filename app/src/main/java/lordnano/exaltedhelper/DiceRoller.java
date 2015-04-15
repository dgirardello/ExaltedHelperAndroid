package lordnano.exaltedhelper;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * Created by damian.girardello on 15/04/2015.
 */
public class DiceRoller {


    public DiceRoller() {
    }

    public int[] rollDice(int qty){
        int results[] =  {0,0,0,0,0,0,0,0,0,0};
        Random rng = new Random();
        if(qty < 0) qty = 0;
        for (int i=0; i<qty; i++){
            results[rng.nextInt(10)] ++;
        }
        return results;
    }
}

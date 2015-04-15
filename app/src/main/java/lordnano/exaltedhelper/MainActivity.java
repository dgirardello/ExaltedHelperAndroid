package lordnano.exaltedhelper;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

public class MainActivity extends FragmentActivity {
    private FragmentTabHost mTabHost;
    private DiceRoller diceRoller = new DiceRoller();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        mTabHost.addTab(
                mTabHost.newTabSpec("essenceTab").setIndicator("Essence", null),
                FragmentTabEssence.class, null);
        mTabHost.addTab(
                mTabHost.newTabSpec("diceTab").setIndicator("Dice", null),
                FragmentTabDice.class, null);

    }
}
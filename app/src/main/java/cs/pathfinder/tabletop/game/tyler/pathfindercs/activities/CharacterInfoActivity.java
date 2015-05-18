package cs.pathfinder.tabletop.game.tyler.pathfindercs.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.CharacterHelper;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.CharacterInfo;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.R;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.expandable_list_view_stuff.CharacterArrayAdapter;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.expandable_list_view_stuff.CharacterListView;


public class CharacterInfoActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    TextView name;
    CharacterInfo characterInfo;
    int selectedCharacter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info_activity2);
        name = (TextView) findViewById(R.id.name_info_text);

        Intent intent = getIntent();
        int message = intent.getIntExtra(CharacterSelect.EXTRA_MESSAGE, 0);
        characterInfo = CharacterHelper.getCharacter(message);
        selectedCharacter = message;

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();
        TabHost.TabSpec tabSpec = tabHost.newTabSpec("details_tab");
        tabSpec.setContent(R.id.tab_details);
        tabSpec.setIndicator("Details");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("stats_tab");
        tabSpec.setContent(R.id.tab_stats);
        tabSpec.setIndicator("Stats");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("items_tab");
        tabSpec.setContent(R.id.tab_items);
        tabSpec.setIndicator("Items");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("spells_tab");
        tabSpec.setContent(R.id.tab_spells);
        tabSpec.setIndicator("Spells");
        tabHost.addTab(tabSpec);

        ListView detailsList = (ListView) findViewById(R.id.tab_details_list);
        detailsList.setOnItemClickListener(this);
        CharacterArrayAdapter array = new CharacterArrayAdapter(this,
                characterInfo.getDetailsArray(false), CharacterInfo.detailTitles);
        array.setCharacter(selectedCharacter);
        array.setListType(CharacterListView.ListType.DETAILS);
        detailsList.setAdapter(array);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_character_info_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}

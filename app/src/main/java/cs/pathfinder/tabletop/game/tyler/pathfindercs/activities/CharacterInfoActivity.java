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

import org.w3c.dom.Text;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.CharacterHelper;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.dialogs.BasicEditDialog;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.CharacterInfo;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.R;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.expandable_list_view_stuff.CharacterArrayAdapter;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.expandable_list_view_stuff.CharacterListView;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.Stats;


public class CharacterInfoActivity
        extends ActionBarActivity
        implements AdapterView.OnItemClickListener,
            BasicEditDialog.Communicator {

    private CharacterInfo characterInfo;
    private int selectedCharacter;
    private Stats clickedStat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_info_activity2);

        Intent intent = getIntent();
        selectedCharacter = intent.getIntExtra(CharacterSelect.EXTRA_MESSAGE, 0);
        characterInfo = CharacterHelper.getCharacter(selectedCharacter);
        setupTabs();
        initTabs();

//        ListView detailsList = (ListView) findViewById(R.id.tab_details_list);
//        detailsList.setOnItemClickListener(this);
//        CharacterArrayAdapter array = new CharacterArrayAdapter(this,
//                characterInfo.getDetailsArray(false), CharacterInfo.detailTitles);
//        array.setCharacter(selectedCharacter);
//        array.setListType(CharacterListView.ListType.DETAILS);
//        detailsList.setAdapter(array);
    }

    private void initTabs() {
        setTextView(R.id.tab_details_name_value, Stats.NAME);
        setTextView(R.id.tab_details_race_value, Stats.RACE);
        setTextView(R.id.tab_details_class_value, Stats.CLASS);
        setTextView(R.id.tab_details_level_value, Stats.LVL);
        setTextView(R.id.tab_details_xp_value, Stats.XP);
        setTextView(R.id.tab_details_nlevel_value, Stats.NEXT_LVL);
        setTextView(R.id.tab_details_gender_value, Stats.GENDER);
        setTextView(R.id.tab_details_age_value, Stats.AGE);
        setTextView(R.id.tab_details_alli_value, Stats.ALLI);
        setTextView(R.id.tab_details_height_value, Stats.HEIGHT);
        setTextView(R.id.tab_details_weight_value, Stats.WEIGHT);
        setTextView(R.id.tab_details_size_value, Stats.SIZE);
    }

    private void setTextView(int resource, Stats value) {
        TextView view = (TextView) findViewById(resource);
        view.setText(characterInfo.get(value));
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

    @Override
    public void onSave(String value) {
        if (value.isEmpty()) return;
        TextView view;

        switch (clickedStat) {
            case NAME:
                view = (TextView) findViewById(R.id.tab_details_name_value);
                view.setText(value);
                characterInfo.set(Stats.NAME, value);
                break;
            case RACE:
                view = (TextView) findViewById(R.id.tab_details_race_value);
                view.setText(value);
                characterInfo.set(Stats.RACE, value);
                break;
            case CLASS:
                view = (TextView) findViewById(R.id.tab_stats_fort_value);
                view.setText(value);
                characterInfo.set(Stats.FORT, value);
                break;
            case LVL:
                view = (TextView) findViewById(R.id.tab_details_level_value);
                view.setText(value);
                characterInfo.set(Stats.LVL, value);
                break;
            case XP:
                view = (TextView) findViewById(R.id.tab_details_xp_value);
                view.setText(value);
                characterInfo.set(Stats.XP, value);
                break;
            case NEXT_LVL:
                view = (TextView) findViewById(R.id.tab_details_nlevel_value);
                view.setText(value);
                characterInfo.set(Stats.NEXT_LVL, value);
                break;
            case GENDER:
                view = (TextView) findViewById(R.id.tab_details_gender_value);
                view.setText(value);
                characterInfo.set(Stats.GENDER, value);
                break;
            case AGE:
                view = (TextView) findViewById(R.id.tab_details_age_value);
                view.setText(value);
                characterInfo.set(Stats.AGE, value);
                break;
            case ALLI:
                view = (TextView) findViewById(R.id.tab_details_alli_value);
                view.setText(value);
                characterInfo.set(Stats.ALLI, value);
                break;
            case HEIGHT:
                view = (TextView) findViewById(R.id.tab_details_height_value);
                view.setText(value);
                characterInfo.set(Stats.HEIGHT, value);
                break;
            case WEIGHT:
                view = (TextView) findViewById(R.id.tab_details_weight_value);
                view.setText(value);
                characterInfo.set(Stats.WEIGHT, value);
                break;
            case SIZE:
                view = (TextView) findViewById(R.id.tab_details_size_value);
                view.setText(value);
                characterInfo.set(Stats.SIZE, value);
                break;
            case MAX_HP: case CURR_HP:
                view = (TextView) findViewById(R.id.tab_stats_hp_value);
                view.setText(value);
                if (value.contains("/")) {
                    String[] split = value.split("/");
                    characterInfo.set(Stats.CURR_HP, split[0]);
                    characterInfo.set(Stats.MAX_HP, split[1]);
                } else {
                    characterInfo.set(Stats.CURR_HP, value);
                    characterInfo.set(Stats.MAX_HP, value);
                }
                break;
            case INIT:
                int init = Integer.valueOf(value);
                if (init >= 0) value = "+" + value;
                view = (TextView) findViewById(R.id.tab_stats_init_value);
                view.setText(value);
                characterInfo.set(Stats.INIT, value);
                break;
            case FORT:
                view = (TextView) findViewById(R.id.tab_stats_fort_value);
                view.setText(value);
                characterInfo.set(Stats.FORT, value);
                break;
            case REFLEX:
                view = (TextView) findViewById(R.id.tab_stats_reflex_value);
                view.setText(value);
                characterInfo.set(Stats.REFLEX, value);
                break;
            case WILL:
                view = (TextView) findViewById(R.id.tab_stats_will_value);
                view.setText(value);
                characterInfo.set(Stats.WILL, value);
                break;
            case BAB:
                view = (TextView) findViewById(R.id.tab_stats_bab_value);
                view.setText(value);
                characterInfo.set(Stats.BAB, value);
                break;
            case CMD:
                view = (TextView) findViewById(R.id.tab_stats_cmd_value);
                view.setText(value);
                characterInfo.set(Stats.CMD, value);
                break;
            case CMB:
                view = (TextView) findViewById(R.id.tab_stats_cmb_value);
                view.setText(value);
                characterInfo.set(Stats.CMB, value);
                break;
        }
        clickedStat = null;
    }

    public void setupTabs() {
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
    }

    public void onClick(View view) {
        BasicEditDialog dialog = new BasicEditDialog();
        switch (view.getId()) {
            case R.id.tab_details_name_click:
                clickedStat = Stats.NAME;
                dialog.setTitleAndValue("Name", ((TextView) findViewById(R.id.tab_details_name_value)).getText().toString());
                break;
            case R.id.tab_details_race_click:
                clickedStat = Stats.RACE;
                dialog.setTitleAndValue("Race", ((TextView) findViewById(R.id.tab_details_race_value)).getText().toString());
                break;
            case R.id.tab_details_class_click:
                clickedStat = Stats.CLASS;
                dialog.setTitleAndValue("Class", ((TextView) findViewById(R.id.tab_details_class_value)).getText().toString());
                break;
            case R.id.tab_details_level_click:
                clickedStat = Stats.LVL;
                dialog.setTitleAndValue("Level", ((TextView) findViewById(R.id.tab_details_level_value)).getText().toString());
                break;
            case R.id.tab_details_xp_click:
                clickedStat = Stats.XP;
                dialog.setTitleAndValue("Experience", ((TextView) findViewById(R.id.tab_details_xp_value)).getText().toString());
                break;
            case R.id.tab_details_nlevel_click:
                clickedStat = Stats.NEXT_LVL;
                dialog.setTitleAndValue("Level Up", ((TextView) findViewById(R.id.tab_details_nlevel_value)).getText().toString());
                break;
            case R.id.tab_details_gender_click:
                clickedStat = Stats.GENDER;
                dialog.setTitleAndValue("Gender", ((TextView) findViewById(R.id.tab_details_gender_value)).getText().toString());
                break;
            case R.id.tab_details_age_click:
                clickedStat = Stats.AGE;
                dialog.setTitleAndValue("Age", ((TextView) findViewById(R.id.tab_details_age_value)).getText().toString());
                break;
            case R.id.tab_details_alli_click:
                clickedStat = Stats.ALLI;
                dialog.setTitleAndValue("Alliance", ((TextView) findViewById(R.id.tab_details_alli_value)).getText().toString());
                break;
            case R.id.tab_details_height_click:
                clickedStat = Stats.HEIGHT;
                dialog.setTitleAndValue("Height", ((TextView) findViewById(R.id.tab_details_height_value)).getText().toString());
                break;
            case R.id.tab_details_weight_click:
                clickedStat = Stats.WEIGHT;
                dialog.setTitleAndValue("Weight", ((TextView) findViewById(R.id.tab_details_weight_value)).getText().toString());
                break;
            case R.id.tab_details_size_click:
                clickedStat = Stats.SIZE;
                dialog.setTitleAndValue("Size", ((TextView) findViewById(R.id.tab_details_size_value)).getText().toString());
                break;
            case R.id.tab_stats_hp_click:
                clickedStat = Stats.MAX_HP;
                dialog.setTitleAndValue("Health Points", ((TextView) findViewById(R.id.tab_stats_hp_value)).getText().toString());
                break;
            case R.id.tab_stats_init_click:
                clickedStat = Stats.INIT;
                dialog.setTitleAndValue("Initiative", ((TextView) findViewById(R.id.tab_stats_init_value)).getText().toString());
                break;
            case R.id.tab_stats_fort_click:
                clickedStat = Stats.FORT;
                dialog.setTitleAndValue("Fortitude", ((TextView) findViewById(R.id.tab_stats_fort_value)).getText().toString());
                break;
            case R.id.tab_stats_reflex_click:
                clickedStat = Stats.REFLEX;
                dialog.setTitleAndValue("Fortitude", ((TextView) findViewById(R.id.tab_stats_reflex_value)).getText().toString());
                break;
            case R.id.tab_stats_will_click:
                clickedStat = Stats.WILL;
                dialog.setTitleAndValue("Will", ((TextView) findViewById(R.id.tab_stats_will_value)).getText().toString());
                break;
            case R.id.tab_stats_bab_click:
                clickedStat = Stats.BAB;
                dialog.setTitleAndValue("Base Attack Bonus", ((TextView) findViewById(R.id.tab_stats_bab_value)).getText().toString());
                break;
            case R.id.tab_stats_cmd_click:
                clickedStat = Stats.CMD;
                dialog.setTitleAndValue("CMD", ((TextView) findViewById(R.id.tab_stats_cmd_value)).getText().toString());
                break;
            case R.id.tab_stats_cmb_click:
                clickedStat = Stats.CMB;
                dialog.setTitleAndValue("Combat Maneuver Bonus", ((TextView) findViewById(R.id.tab_stats_cmb_value)).getText().toString());
                break;
            case R.id.tab_stats_flat_click:
                clickedStat = Stats.FLAT;
                dialog.setTitleAndValue("Flat Footed", ((TextView) findViewById(R.id.tab_stats_flat_value)).getText().toString());
                break;
            case R.id.tab_stats_touch_click:
                clickedStat = Stats.TOUCH;
                dialog.setTitleAndValue("Touch", ((TextView) findViewById(R.id.tab_stats_touch_value)).getText().toString());
                break;
            default:
                return;
        }
        dialog.show(getSupportFragmentManager(), "edit dialog");
    }
}

package cs.pathfinder.tabletop.game.tyler.pathfindercs.activities;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.CharacterHelper;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.CharacterInfo;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.R;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.components.CharacterCard;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.dialogs.ConfirmDeleteDialog;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.dialogs.CreateCharacterDialog;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.XML_Helper;

/**
 * The main/launch activity. This is where
 * the user will select, create, and manage
 * the characters they have.
 */
public class CharacterSelect
        extends ActionBarActivity
        implements ConfirmDeleteDialog.Communicator,
            CreateCharacterDialog.CreateDialogCommunicator,
            AdapterView.OnItemClickListener,
            AdapterView.OnItemLongClickListener {

    // Message for the intent when starting the CharacterInfo activity.
    public final static String EXTRA_MESSAGE = "cs.pathfinder.tabletop.game.tyler.pathfindercs.CHARACTER";
    private XML_Helper xml_helper;

    // #################  Overridden Methods  #################

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);
        if (CharacterHelper.isEmpty()) {
            loadEmptyView();
        } else {
            loadListView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (CharacterHelper.isEmpty()) {
            loadEmptyView();
        } else {
            loadListView();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_character_select, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.create_new_character) {
            CreateCharacterDialog createCharacterDialog = new CreateCharacterDialog();
            createCharacterDialog.show(getSupportFragmentManager(), "create_character");
        } else if (id == R.id.menu_select_races) {
            Intent intent = new Intent(this, ActivityRaces.class);
            startActivity(intent);
        } else if (id == R.id.menu_select_classes) {
            Intent intent = new Intent(this, ActivityClases.class);
            startActivity(intent);
        } else if (id == R.id.menu_select_about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);
        } else if (id == R.id.action_settings) {
            Intent intent = new Intent(this, Settings.class);
            startActivity(intent);
        }

            return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateCharacter(CharacterInfo characterInfo) {
        createNewCharacter(characterInfo);
        int loc = CharacterHelper.getCharacterCount() - 1;

        openCharacterInfo(loc);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        openCharacterInfo(position);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        ConfirmDeleteDialog dialog = new ConfirmDeleteDialog();
        dialog.setCharacter(position);
        dialog.show(getSupportFragmentManager(), "delete character");
        return true;
    }

    @Override
    public void onConfirmDelete(int character) {
        CharacterInfo charInfo = CharacterHelper.getCharacter(character);
        String name = charInfo.getName().trim();
        CharacterHelper.removeCharacter(character);

        if (CharacterHelper.isEmpty()) {
            loadEmptyView();
        } else {
            updateListView();
        }
        if (name.equals(""))
            name = "Character";

        Toast.makeText(this, name + " has sucessfully been removed.", Toast.LENGTH_SHORT).show();
    }

    // Custom Methods...

    public void loadEmptyView() {
        TextView textView = (TextView) findViewById(R.id.emptyTextView);
        textView.setVisibility(View.VISIBLE);
        ListView listView = (ListView) findViewById(R.id.character_select_list);
        listView.setVisibility(View.GONE);
    }

    public void loadListView() {
        TextView emptyView = (TextView) findViewById(R.id.emptyTextView);
        emptyView.setVisibility(View.GONE);

        ListView characterList = listView();
        characterList.setVisibility(View.VISIBLE);
        characterList.setOnItemClickListener(this);
        characterList.setOnItemLongClickListener(this);

        updateListView();
    }

    public void openCharacterInfo(int loc) {
        Intent intent = new Intent(this, CharacterInfoActivity.class);
        intent.putExtra(EXTRA_MESSAGE, loc);
        startActivity(intent);
    }

    public void createNewCharacter(CharacterInfo newCharacter) {
        CharacterHelper.addCharacter(newCharacter);
        updateListView();
    }

    private void updateListView() {
        ArrayAdapter<String> array = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, CharacterHelper.getStringArray());
        listView().setAdapter(array);
    }

    private ListView listView() {
        return (ListView) findViewById(R.id.character_select_list);
    }
}

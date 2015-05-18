package cs.pathfinder.tabletop.game.tyler.pathfindercs.expandable_list_view_stuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import cs.pathfinder.tabletop.game.tyler.pathfindercs.CharacterHelper;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.utils.CharacterInfo;
import cs.pathfinder.tabletop.game.tyler.pathfindercs.R;


/**
 * Created by Tyler on 5/4/2015.
 */
public class CharacterArrayAdapter extends ArrayAdapter<String> {

    private int selectedCharacter;
    private CharacterListView.ListType listType;
    private String[] titleValues;

    public CharacterArrayAdapter(Context context, String[] values, String[] titleValues) {
        super(context, R.layout.list_level_layout, values);
        this.titleValues = titleValues;
        selectedCharacter = 0;
        listType = CharacterListView.ListType.DETAILS;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater theInflater = LayoutInflater.from(getContext());
        CharacterInfo characterInfo = CharacterHelper.getCharacter(selectedCharacter);
        View theView;
        if (position == 3) {
            theView = theInflater.inflate(R.layout.list_level_layout, parent, false);

            TextView textView = (TextView) theView.findViewById(R.id.list_level_value);
            textView.setText(titleValues[3] + ": " + getItem(3));
            textView = (TextView) theView.findViewById(R.id.list_experience_value);
            textView.setText(getItem(4));
        } else if (position == 4) {
            theView = theInflater.inflate(R.layout.list_level_layout, parent, false);
            theView.setVisibility(View.GONE);
        } else {
            theView = theInflater.inflate(R.layout.list_level_layout, parent, false);
            TextView textView = (TextView) theView.findViewById(R.id.list_experience_value);
            textView.setVisibility(View.GONE);
            textView = (TextView) theView.findViewById(R.id.list_experience_title);
            textView.setVisibility(View.GONE);
            textView = (TextView) theView.findViewById(R.id.list_level_title);
            textView.setText(titleValues[position]);
            textView = (TextView) theView.findViewById(R.id.list_level_value);
            textView.setText(getItem(position));
        }
        return theView;
    }

    private String getInfoFromInt(int info) {
        CharacterInfo characterInfo = CharacterHelper.getCharacter(selectedCharacter);
        switch (info) {
            case 0:
                return characterInfo.getName();
            case 1:
                return characterInfo.getRace();
            case 2:
                return characterInfo.getcClass();
            case 3:
                return String.valueOf(characterInfo.getCurrLevel());
            case 4:
                return String.valueOf(characterInfo.getExperience());
            case 5:
                return characterInfo.getGender();
            case 6:
                return String.valueOf(characterInfo.getAge());
            case 7:
                return characterInfo.getAlliance();
            case 8:
                return String.valueOf(characterInfo.getHeight());
            case 9:
                return String.valueOf(characterInfo.getWeight());
            case 10:
                return characterInfo.getSize();
            default:
                return "null";
        }
    }

    public void setCharacter(int character) {
        selectedCharacter = character;
    }

    public void setListType(CharacterListView.ListType listType) {
        this.listType = listType;
    }
}

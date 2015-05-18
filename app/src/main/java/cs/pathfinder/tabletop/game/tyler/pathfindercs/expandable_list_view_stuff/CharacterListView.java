package cs.pathfinder.tabletop.game.tyler.pathfindercs.expandable_list_view_stuff;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Tyler on 5/2/2015.
 */
public class CharacterListView extends ListView {

    private ListType listType;

    public enum ListType {
        DETAILS,
        STATS,
        ITEMS,
    }

    public CharacterListView(Context context, ListType listType) {
        super(context);
        this.listType = listType;
    }



    public CharacterListView(Context context) {
        super(context);
    }

    public CharacterListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CharacterListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}

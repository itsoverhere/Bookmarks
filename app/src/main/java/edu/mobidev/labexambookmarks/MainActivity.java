package edu.mobidev.labexambookmarks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static final String EXTRA_BOOKMARK = "bookmark";
    static final String EXTRA_BOOKMARK_URL = "bookmark_url";
    static final String EXTRA_BOOKMARK_ID = "bookmark_id";

    static final int REQUEST_ADD_BOOKMARK = 0;
    static final int REQUEST_VIEW_BOOKMARK = 1;

    ListView lvBookmarks;
    Button buttonAdd;
    ArrayAdapter<Bookmark> bookmarksAdapter;
    ArrayList<Bookmark> bookmarks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBookmarks = (ListView) findViewById(R.id.lv_bookmarks);
        buttonAdd = (Button) findViewById(R.id.button_add);

        bookmarks = new ArrayList<>();
        bookmarksAdapter
                = new CustomAdapter(getBaseContext(), R.layout.list_item, bookmarks);

        lvBookmarks.setAdapter(bookmarksAdapter);
        lvBookmarks.setOnItemClickListener(chooseBookmark);

        buttonAdd.setOnClickListener(goToAddActivity);
    }

    AdapterView.OnItemClickListener chooseBookmark = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Bookmark bookmark = (Bookmark) parent.getItemAtPosition(position);
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), ViewBookmarkActivity.class);
            intent.putExtra(EXTRA_BOOKMARK, bookmark.getTitle());
            intent.putExtra(EXTRA_BOOKMARK_URL, bookmark.getUrl());
            intent.putExtra(EXTRA_BOOKMARK_ID, position);
            startActivityForResult(intent, REQUEST_VIEW_BOOKMARK);
        }
    };

    View.OnClickListener goToAddActivity = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), AddBookmarkActivity.class);
            startActivityForResult(intent, REQUEST_ADD_BOOKMARK);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_ADD_BOOKMARK && resultCode == RESULT_OK){
            // Add bookmark to ArrayList bookmarks
            String bookmark = data.getExtras().getString(EXTRA_BOOKMARK);
            String url = data.getExtras().getString(EXTRA_BOOKMARK_URL);
            if(bookmark!=null && url!=null){
                bookmarks.add(new Bookmark(bookmark, url));
                bookmarksAdapter.notifyDataSetChanged();
            }
        }else if(requestCode == REQUEST_VIEW_BOOKMARK && resultCode == RESULT_OK){
            // check if item was deleted
            int position = data.getExtras().getInt(EXTRA_BOOKMARK_ID);
            Log.i("MA" , "remove position " + position);
            bookmarks.remove(position);
            bookmarksAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}

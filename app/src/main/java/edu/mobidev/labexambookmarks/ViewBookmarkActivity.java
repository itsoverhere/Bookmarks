package edu.mobidev.labexambookmarks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewBookmarkActivity extends AppCompatActivity {

    Button buttonDelete;
    Button buttonOpen;
    TextView tvLink;
    TextView tvBookmark;

    int bookmarkPosition;
    String bookmark, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookmark);

        buttonDelete = (Button) findViewById(R.id.button_delete);
        buttonOpen = (Button) findViewById(R.id.button_open);
        tvLink = (TextView) findViewById(R.id.tv_url);
        tvBookmark = (TextView) findViewById(R.id.tv_bookmark);

        bookmark = getIntent().getExtras().getString(MainActivity.EXTRA_BOOKMARK);
        url = getIntent().getExtras().getString(MainActivity.EXTRA_BOOKMARK_URL);
        bookmarkPosition = getIntent().getExtras().getInt(MainActivity.EXTRA_BOOKMARK_ID);

        tvBookmark.setText(bookmark);
        tvLink.setText(url);
        buttonDelete.setOnClickListener(deleteBookmark);
        buttonOpen.setOnClickListener(openBookmark);
    }

    View.OnClickListener deleteBookmark = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.putExtra(MainActivity.EXTRA_BOOKMARK_ID, bookmarkPosition);
            setResult(RESULT_OK, intent);
            finish();
        }
    };

    View.OnClickListener openBookmark = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(tvLink.getText().toString()));
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_bookmark, menu);
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

package edu.mobidev.labexambookmarks;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddBookmarkActivity extends AppCompatActivity {

    Button buttonAdd;
    EditText etBookmark;
    EditText etUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bookmark);

        buttonAdd = (Button) findViewById(R.id.button_add);
        etBookmark = (EditText) findViewById(R.id.et_bookmark);
        etUrl = (EditText) findViewById(R.id.et_url);

        buttonAdd.setOnClickListener(addNote);
    }

    View.OnClickListener addNote = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String bookmark = etBookmark.getText().toString().trim();
            String url = etUrl.getText().toString().trim();
            Intent intent = new Intent();

            if(bookmark.isEmpty() || url.isEmpty()){
                setResult(RESULT_CANCELED);
                Toast.makeText(getBaseContext(), "Please submit a title and a URL.", Toast.LENGTH_SHORT).show();
            }else if(!url.startsWith("http://")||!url.startsWith("https://")){
                url = "http://"+url;
                intent.putExtra(MainActivity.EXTRA_BOOKMARK, bookmark);
                intent.putExtra(MainActivity.EXTRA_BOOKMARK_URL, url);
                setResult(RESULT_OK, intent);
            }else{
                intent.putExtra(MainActivity.EXTRA_BOOKMARK, bookmark);
                intent.putExtra(MainActivity.EXTRA_BOOKMARK_URL, url);
                setResult(RESULT_OK, intent);
            }

            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_bookmark, menu);
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

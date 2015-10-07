package edu.mobidev.labexambookmarks;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by courtneyngo on 9/29/15.
 */
public class CustomAdapter extends ArrayAdapter<Bookmark> {

    ArrayList<Bookmark> bookmarks;

    public CustomAdapter(Context context, int resource, List<Bookmark> objects) {
        super(context, resource, objects);
        bookmarks = (ArrayList<Bookmark>) objects;
    }

    private class BookmarkHolder{
        TextView tvBookmark;
        TextView tvUrl;

        public BookmarkHolder(){}
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_item, parent, false);
            BookmarkHolder bookmarkHolder = new BookmarkHolder();
            bookmarkHolder.tvBookmark = (TextView) convertView.findViewById(R.id.tv_bookmark);
            bookmarkHolder.tvUrl = (TextView) convertView.findViewById(R.id.tv_url);
            convertView.setTag(bookmarkHolder);
        }

        Bookmark bookmark = bookmarks.get(position);
        BookmarkHolder bookmarkHolder = (BookmarkHolder) convertView.getTag();
        bookmarkHolder.tvBookmark.setText(bookmark.getTitle());
        bookmarkHolder.tvUrl.setText(bookmark.getUrl());

        return convertView;
    }
}

package com.adil.meedoo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.adil.meedoo.R;
import com.adil.meedoo.helpers.DateHelper;
import com.adil.meedoo.models.ToDo;

import java.util.List;

/**
 * Created by adil on 12/29/15.
 */
public class ListStoryAdapter extends ArrayAdapter<ToDo> {

    public ListStoryAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListStoryAdapter(Context context, int resource, List<ToDo> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_story_view, null);
        }

        ToDo td = getItem(position);

        if (td != null) {
            TextView tvPriority = (TextView) v.findViewById(R.id.priority);
            TextView tvItemText = (TextView) v.findViewById(R.id.item_text);
            TextView tvDueDate = (TextView) v.findViewById(R.id.due_date);

            tvPriority.setText(td.getPriority().toString());
            tvItemText.setText(td.getText());
            tvDueDate.setText(DateHelper.getDateAsString(td.getDueDate()));
        }

        return v;
    }

}

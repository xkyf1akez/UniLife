package integratedproject.unilife_v1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kieran Brown on 4/18/2018.
 */

public class SearchAdapter extends ArrayAdapter<SearchDataModel> implements View.OnClickListener {
    private ArrayList<SearchDataModel> dataSet;
    private Context context;

    private static class ViewHolder {
        TextView username;
        TextView name;
        TextView department;
    }

    public SearchAdapter(ArrayList<SearchDataModel> data, Context context) {
        super(context, R.layout.friends_item, data);
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        //send friend request
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SearchDataModel dataModel = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.friends_item, parent, false);

            viewHolder.username = (TextView)convertView.findViewById(R.id.username);
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);
            viewHolder.department = (TextView)convertView.findViewById(R.id.department);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
            result = convertView;
        }

        viewHolder.username.setText(dataModel.getUsername());
        viewHolder.name.setText(dataModel.getName());
        viewHolder.department.setText(dataModel.getDepartment());

        return convertView;
    }
}

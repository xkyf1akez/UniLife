package integratedproject.unilife_v1;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Kieran Brown on 4/18/2018.
 */

public class CalendarFriendsAdaptor extends ArrayAdapter<ScheduleDataModel> implements View.OnClickListener{
    private ArrayList<ScheduleDataModel> dataSet;
    private Context context;

    //internal class for the fields on the listView
    private static class ViewHolder {
        TextView username;
        TextView name;
        TextView availability;
    }

    public CalendarFriendsAdaptor(ArrayList<ScheduleDataModel> data, Context context) {
        super(context, R.layout.friends_schedule_item, data);
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ScheduleDataModel dataModel = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            //converts Views in the listView to an internal format for getting data upon click
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.friends_schedule_item, parent, false);

            viewHolder.username = (TextView)convertView.findViewById(R.id.username);
            viewHolder.name = (TextView)convertView.findViewById(R.id.name);
            viewHolder.availability = (TextView)convertView.findViewById(R.id.availability);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
            result = convertView;
        }

        //sets details from the given item in the list
        viewHolder.username.setText(dataModel.getUsername());
        viewHolder.name.setText(dataModel.getName());
        viewHolder.availability.setText(dataModel.getAvailability());

        return convertView;
    }
}

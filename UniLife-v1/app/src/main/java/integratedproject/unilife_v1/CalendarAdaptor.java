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

public class CalendarAdaptor extends ArrayAdapter<CalendarDataModel> implements View.OnClickListener{
    private ArrayList<CalendarDataModel> dataSet;
    private Context context;

    //internal class for the fields on the listView
    private static class ViewHolder {
        TextView textTitle;
        TextView textLocation;
        TextView textTime;
        TextView textGoing;
        TextView textCategory;
    }

    public CalendarAdaptor(ArrayList<CalendarDataModel> data, Context context) {
        super(context, R.layout.calender_item, data);
        this.dataSet = data;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CalendarDataModel dataModel = getItem(position);
        ViewHolder viewHolder;

        final View result;

        if(convertView == null) {
            //converts Views in the listView to an internal format for getting data upon click
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.calender_item, parent, false);

            viewHolder.textTitle = (TextView)convertView.findViewById(R.id.title);
            viewHolder.textCategory = (TextView)convertView.findViewById(R.id.category);
            viewHolder.textLocation = (TextView)convertView.findViewById(R.id.location);
            viewHolder.textTime = (TextView)convertView.findViewById(R.id.time);
            viewHolder.textGoing = (TextView)convertView.findViewById(R.id.attending);

            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
            result = convertView;
        }

        //sets details from the given item in the list
        viewHolder.textGoing.setText(dataModel.getAttending());
        viewHolder.textLocation.setText(dataModel.getLocation());
        viewHolder.textCategory.setText(dataModel.getCategory());
        viewHolder.textTime.setText(dataModel.getTime());
        viewHolder.textTitle.setText(dataModel.getTitle());

        return convertView;
    }
}

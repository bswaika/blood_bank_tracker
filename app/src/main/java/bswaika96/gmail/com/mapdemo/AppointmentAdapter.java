package bswaika96.gmail.com.mapdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Baladitya SWaika on 25-11-2017.
 */

public class AppointmentAdapter extends BaseAdapter {

    ArrayList names;
    ArrayList addresses;
    ArrayList contacts;
    ArrayList dates;
    ArrayList times;
    ArrayList types;
    LayoutInflater layoutInflater;

    AppointmentAdapter(Context c, ArrayList n, ArrayList a, ArrayList co, ArrayList d, ArrayList t, ArrayList ty ){
        names = n;
        addresses = a;
        contacts = co;
        dates = d;
        times = t;
        types = ty;

        layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return names.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = layoutInflater.inflate(R.layout.appointment_list_view,null);
        TextView nameText = (TextView) v.findViewById(R.id.nameText);
        TextView addressText = (TextView) v.findViewById(R.id.addressText);
        TextView contactText = (TextView) v.findViewById(R.id.contactText);
        TextView dateText = (TextView) v.findViewById(R.id.dateText);
        TextView timeText = (TextView) v.findViewById(R.id.timeText);
        TextView typeText = (TextView) v.findViewById(R.id.typeText);

        String name = (String)names.get(position);
        String address = (String)addresses.get(position);
        String contact = (String)contacts.get(position);
        String date = (String)dates.get(position);
        String time = (String)times.get(position);
        String type = (String)types.get(position);

        nameText.setText(name);
        addressText.setText(address);
        contactText.setText(contact);
        dateText.setText(date);
        timeText.setText(time);
        typeText.setText(type.toUpperCase());

        return v;
    }
}

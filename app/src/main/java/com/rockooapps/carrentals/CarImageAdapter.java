package com.rockooapps.carrentals;

/**
 * Created by Rakesh on 7/25/2016.
 */
import android.content.Context;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CarImageAdapter extends  ArrayAdapter<CarElements> {

    private final int THUMBSIZE = 96;

    private static class ViewHolder {
        ImageView imgIcon;
        TextView name;
    }
    public CarImageAdapter(Context context,ArrayList<CarElements> cars) {
        super(context,0, cars );

    }
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;

            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(getContext())
                        .inflate(R.layout.custom_list_car_details, parent, false);
                viewHolder.name =
                        (TextView) convertView.findViewById(R.id.grid_text);
                viewHolder.imgIcon =
                        (ImageView) convertView.findViewById(R.id.grid_image);
                convertView.setTag(viewHolder);
            } else {

                viewHolder = (ViewHolder) convertView.getTag();
            }
            // Get the data item for this position
            CarElements cars = getItem(position);
            // set description text
            viewHolder.name.setText(cars.toString());
            // set image icon
            viewHolder.imgIcon.setImageBitmap(ThumbnailUtils
                    .extractThumbnail(BitmapFactory.decodeFile(cars.getPath()),
                           THUMBSIZE, THUMBSIZE));
            // Return the completed view to render on screen
            return convertView;
        }
}

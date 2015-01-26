package com.yahoo.netram.instagramviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by netram on 1/25/15.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {

    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> photos) {
        super(context, android.R.layout.simple_list_item_1, photos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto instagramPhoto = getItem(position);

        //check for recycled view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }

        TextView etPhotoCaption = (TextView) convertView.findViewById(R.id.etPhotoCaption);
        TextView etUserName = (TextView) convertView.findViewById(R.id.etUserName);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        RoundedImageView ivUserPhoto = (RoundedImageView) convertView.findViewById(R.id.ivUserPhoto);

        etPhotoCaption.setText(instagramPhoto.getCaption());
        etUserName.setText(instagramPhoto.getUserName());
        ivPhoto.getLayoutParams().height = instagramPhoto.getHeight();

        //reset image from recycled view
        ivPhoto.setImageResource(0);

        Picasso.with(getContext()).load(instagramPhoto.getImageUrl()).into(ivPhoto);
        Picasso.with(getContext()).load(instagramPhoto.getUserImageUrl()).into(ivUserPhoto);

        return convertView;
    }
}

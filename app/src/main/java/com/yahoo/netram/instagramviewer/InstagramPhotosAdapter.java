package com.yahoo.netram.instagramviewer;

import android.content.Context;
import android.text.format.DateUtils;
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

        TextView tvPhotoCaption = (TextView) convertView.findViewById(R.id.tvPhotoCaption);
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvUserName);
        TextView tvNumLikes = (TextView) convertView.findViewById(R.id.tvNumLikes);
        TextView tvPhotoTime = (TextView) convertView.findViewById(R.id.tvPhotoTime);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        RoundedImageView ivUserPhoto = (RoundedImageView) convertView.findViewById(R.id.ivUserPhoto);

        tvPhotoCaption.setText(instagramPhoto.getCaption());
        tvUserName.setText(instagramPhoto.getUserName());
        tvNumLikes.setText(Integer.toString(instagramPhoto.getNumLikes()));
        tvPhotoTime.setText(DateUtils.getRelativeTimeSpanString(instagramPhoto.getCreateTime() * 1000, System.currentTimeMillis(), 0L, DateUtils.FORMAT_ABBREV_ALL));
        ivPhoto.getLayoutParams().height = instagramPhoto.getHeight();

        //reset image from recycled view
        ivPhoto.setImageResource(0);

        Picasso.with(getContext()).load(instagramPhoto.getImageUrl()).into(ivPhoto);
        Picasso.with(getContext()).load(instagramPhoto.getUserImageUrl()).into(ivUserPhoto);

        return convertView;
    }
}

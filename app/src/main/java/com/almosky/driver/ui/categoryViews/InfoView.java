package com.almosky.driver.ui.categoryViews;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.almosky.driver.R;
import com.almosky.driver.models.CategoryItems;
import com.almosky.driver.ui.ItemDetailsAddActivity;
import com.bumptech.glide.Glide;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.expand.ChildPosition;
import com.mindorks.placeholderview.annotations.expand.ParentPosition;


@Layout(R.layout.feed_item)
public class InfoView {




    @ParentPosition
    private int mParentPosition;

    @ChildPosition
    private int mChildPosition;

    @View(R.id.titleTxt)
    private TextView titleTxt;

    @View(R.id.captionTxt)
    private TextView captionTxt;


    @View(R.id.imageView)
    private ImageView imageView;

    private CategoryItems.Detail.Item mInfo;
    private Context mContext;

    public InfoView(Context context, CategoryItems.Detail.Item info) {
        mContext = context;
        mInfo = info;
    }



    @Resolve
    private void onResolved() {
        titleTxt.setText(mInfo.getItemName());
       // captionTxt.setText("100");
        System.out.println("inRes"+mInfo.getItemImage());

        Glide.with(mContext).load("http://almosky.abrappsworld.com/ItemImages/"+mInfo.getItemImage()).into(imageView);
     //   Glide.with(mContext).load("http://innosyz.com/itemimages/"+mInfo.getItemImage()).into(imageView);
        System.out.println("http://almosky.abrappsworld.com/ItemImages/"+mInfo.getItemImage());


            titleTxt.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, ItemDetailsAddActivity.class);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("catId", mInfo.getCategoryId());
            intent.putExtra("catname", mInfo.getCategoryName());
            intent.putExtra("itemId", mInfo.getItemId());
            intent.putExtra("itemname", mInfo.getItemName());
            intent.putExtra("url", "http://almosky.abrappsworld.com/ItemImages/"+mInfo.getItemImage());
            mContext.startActivity(intent);
        });
    }
}

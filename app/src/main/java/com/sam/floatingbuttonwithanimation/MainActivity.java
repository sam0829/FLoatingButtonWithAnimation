package com.sam.floatingbuttonwithanimation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.sam.floatingbuttonwithanimation.fab.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ListView mListView;
    FloatingActionButton mFloatingButton;

    private boolean isFabToCenter = false;
    private Animation anim_to_bottom;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        mListView = (ListView) findViewById(R.id.lvDummy);
        mFloatingButton = (FloatingActionButton) findViewById(R.id.fabAdd);
        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        anim_to_bottom = AnimationUtils.loadAnimation(context, R.anim.move_to_bottom);
        anim_to_bottom.setInterpolator((new
                AccelerateDecelerateInterpolator()));
        anim_to_bottom.setFillAfter(true);
        anim_to_bottom.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isFabToCenter = false;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        final Animation anim = AnimationUtils.loadAnimation(context, R.anim.move_to_center);
        anim.setInterpolator((new
                AccelerateDecelerateInterpolator()));
        anim.setFillAfter(true);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent = new Intent(context, AddTopicActivity.class);
                startActivity(intent);
                isFabToCenter = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        mFloatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mFloatingButton.startAnimation(anim);

            }
        });

        setUpDummyData();

    }

    private void setUpDummyData(){

        ArrayList<DummyData> dummyDataListHolder = new ArrayList<>();
        for(int i=0; i< 15; i++){
            dummyDataListHolder.add(new DummyData("Title  :  " + i, "Some Description  :  " + i));
        }

        mListView.setAdapter(new DummyDataAdapter(context, dummyDataListHolder));
    }

    private class DummyDataAdapter extends BaseAdapter {

        private LayoutInflater mInflater;
        private ArrayList<DummyData> data;

        public DummyDataAdapter(Context context, ArrayList<DummyData> data) {
            mInflater = LayoutInflater.from(context);
            this.data = data;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        class ViewHolder {
            TextView tvDummyTitle, tvDummyDesc;

        }

        @Override
        public View getView(final int position, View v, ViewGroup parent) {
            final ViewHolder h;
            if (v == null) {
                v = mInflater.inflate(R.layout.dummy_data_row, parent,
                        false);
                h = new ViewHolder();
                h.tvDummyTitle = (TextView) v.findViewById(R.id.tvTitle);
                h.tvDummyDesc = (TextView) v.findViewById(R.id.tvDesc);

                v.setTag(h);
            } else {
                h = (ViewHolder) v.getTag();
            }

            h.tvDummyTitle.setText(data.get(position).getDummyTitle());
            h.tvDummyDesc.setText(data.get(position).getDummyDescription());
            return v;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

        if (isFabToCenter) {
            mFloatingButton.startAnimation(anim_to_bottom);
        }
    }
}

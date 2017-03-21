package com.example.scrollviewdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.scrollviewdemo.PersonalScrollView.onTurnListener;
import com.example.scrollviewdemo.RotateAnimation.InterpolatedTimeListener;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private ZoomScrollView personalScrollView;

    private TableLayout tl_main;
    private ViewPager banner;

    protected void initView() {
        setContentView(R.layout.main);
        personalScrollView = (ZoomScrollView) findViewById(R.id.personalScrollView);
        tl_main = (TableLayout) findViewById(R.id.tl_main);

        this.banner = (ViewPager) findViewById(R.id.banner);

        //设置图片加载器
        //设置图片集合
        ArrayList<String> images= new ArrayList<>();
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489675654&di=5680d008249cf934b0372efd8a927ef0&imgtype=jpg&er=1&src=http%3A%2F%2Fa.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F7acb0a46f21fbe091bd6251369600c338744ad29.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489675682&di=83d7ba930f78816ea24a2f87237a927e&imgtype=jpg&er=1&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2F8435e5dde71190ef3ffc98c0cc1b9d16fdfa60ad.jpg");
        images.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1489080970652&di=ce3bc889ab224ae347cc1ef342809815&imgtype=0&src=http%3A%2F%2Fe.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fac345982b2b7d0a280dcd3ccc9ef76094b369a4e.jpg");


        ImageAdapter adapter=new ImageAdapter(this,images);
        banner.setAdapter(adapter);
    }



    static class ImageAdapter extends PagerAdapter{

        private Context context;
        private ArrayList<ImageView> imageViews;
        private ArrayList<String> paths;

        public ImageAdapter(Context context, ArrayList<String> paths) {
            this.context = context;
            this.paths = paths;
            imageViews=new ArrayList<>();
            for (String path:paths) {
                ImageView imageView=new ImageView(context);
                Glide.with(context).load(path).into(imageView);
                imageViews.add(imageView);
            }

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView imageView = imageViews.get(position);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(imageViews.get(position));
        }

        @Override
        public int getCount() {
            return paths.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }


    public void showTable() {
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                android.widget.TableRow.LayoutParams.FILL_PARENT,
                android.widget.TableRow.LayoutParams.FILL_PARENT);
        layoutParams.gravity = Gravity.CENTER;

        for (int i = 0; i < 30; i++) {
            TableRow tableRow = new TableRow(this);
            TextView textView = new TextView(this);
            textView.setText("jjhappyforever is " + i);
            textView.setTextSize(20);
            textView.setPadding(10, 10, 10, 10);

            tableRow.addView(textView, layoutParams);
            tl_main.addView(tableRow);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        personalScrollView.setHeaderView(banner);

        showTable();

    }


}

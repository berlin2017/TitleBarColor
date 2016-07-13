package com.berlin.scrollviewtest;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyListView listView;
    private MyScrollView myScrollView;
    private TextView textView;
    private int imageHeight = 200;
    private List<String>list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myScrollView = (MyScrollView) findViewById(R.id.scrollview);
        listView = (MyListView) findViewById(R.id.listview);
        textView = (TextView) findViewById(R.id.main_title);
        myScrollView.setMyScrollListener(new MyScrollListener() {
            @Override
            public void onScrollChanged(int l, int y, int oldl, int oldt) {
                if (y <= 0) {
                    textView.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明(仿知乎滑动效果)
                    textView.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                } else {
                    textView.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                }
            }
        });
        for (int i=0;i<20;i++){
            list.add("This is "+i);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(arrayAdapter);
        setListViewHeight(listView);
    }

    public void setListViewHeight(ListView listView1){
        ListAdapter listAdapter = listView1.getAdapter();
        if (listAdapter==null){
            return;
        }
        int totalHeight = 0;
        for (int i=0;i<listAdapter.getCount();i++){
            View v = listAdapter.getView(i,null,listView1);
            listView1.measure(0,0);
            totalHeight+=v.getMeasuredHeight();
        }

        ViewGroup.LayoutParams lp = listView1.getLayoutParams();
        lp.height = totalHeight+listView1.getDividerHeight()*(listAdapter.getCount()-1);
        listView1.setLayoutParams(lp);
    }

}

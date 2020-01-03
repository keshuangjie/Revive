package com.jimmy.revive.sample.nestedlistview;

import android.os.Bundle;
import androidx.core.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jimmy.revive.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyc on 2015/12/31.
 */
public class GoNetScrollViewPagerActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_act_inner_viewpager);

        initListView();
    }


    private void initListView() {
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.test_list_item, android.R.id.text1, testData()));
    }

    private String[] testData() {
        List<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("我是第" + i + "个");
        }
        String[] result = new String[]{};
        return datas.toArray(result);
    }

}

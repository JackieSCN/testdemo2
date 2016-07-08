package test.example.com.testrecycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import test.example.com.testrecycleview.adapter.DemoAdapter;
import test.example.com.testrecycleview.divider.HorizontalDividerItemDecoration;
import test.example.com.testrecycleview.divider.VerticalDividerItemDecoration;
import test.example.com.testrecycleview.entity.DemoData;

/**
 * 显示各个layout布局切换
 */
public class DemoActivity extends Activity implements View.OnClickListener{
    private DemoAdapter mAdapter;
    private RecyclerView mRecycle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo1);
        initBody();
    }

    private void initBody() {
        initButton();
        List<DemoData> a = getFake();
        mRecycle = (RecyclerView) findViewById(R.id.group_member_recycle_view);
        mAdapter = new DemoAdapter(this, a);
        mRecycle.setAdapter(mAdapter);
        mRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycle.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).
                color(getResources().getColor(R.color.group_member_bg)).
                size(getResources().getDimensionPixelSize(R.dimen.group_item_divider)).
                showLastDivider().
                build());
        //为了gridview或者横向的view添加分割线
        mRecycle.addItemDecoration(new VerticalDividerItemDecoration.Builder(this).
                color(getResources().getColor(R.color.group_member_bg)).
                size(getResources().getDimensionPixelSize(R.dimen.group_item_divider)).
                showLastDivider().
                build());
    }

    private void initButton(){
        TextView hor = (TextView) findViewById(R.id.list_hor);
        TextView ver = (TextView) findViewById(R.id.list_ver);
        TextView grid = (TextView) findViewById(R.id.list_grid);
        TextView stragger = (TextView) findViewById(R.id.list_stragger_grid);
        hor.setOnClickListener(this);
        ver.setOnClickListener(this);
        grid.setOnClickListener(this);
        stragger.setOnClickListener(this);
    }

    private List<DemoData> getFake() {
        List<DemoData> a = new ArrayList<>();
        for (int i = 0;i<40;i++){
            a.add(new DemoData(i+""));
        }
        return a;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.list_hor:
                mRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            break;
            case R.id.list_ver:
                mRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
                break;
            case R.id.list_grid:
                mRecycle.setLayoutManager(new GridLayoutManager(this, 2));
                break;
            case R.id.list_stragger_grid:
                mRecycle.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
                break;
        }
    }
}

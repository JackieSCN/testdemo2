package test.example.com.testrecycleview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.example.com.testrecycleview.adapter.DemoAdapter;
import test.example.com.testrecycleview.adapter.DemoPullAdapter;
import test.example.com.testrecycleview.divider.HorizontalDividerItemDecoration;
import test.example.com.testrecycleview.entity.DemoData;
import test.example.com.testrecycleview.helper.OnLoadScrollListener;
import test.example.com.testrecycleview.util.ProgressDialog;
import test.example.com.testrecycleview.widget.PullToRefreshBase;
import test.example.com.testrecycleview.widget.WanRecycleView;

/**
 * 上下拉刷新
 */
public class DemoRefreshActivity extends Activity{
    private static final String TAG = "DemoRefreshActivity";
    private WanRecycleView mWanRecycle;
    private RecyclerView mRecycle;
    List<DemoData> a;
    private DemoPullAdapter mAdapter;
    private Handler mHandler ;
    private int num = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_demo);
        mHandler = new Handler();
        initBody();
    }

    private void initBody() {
        a = getFake();
        initRecycle();
    }
    private void refreshItems(){
        mAdapter.refreshData(getFake());
        mWanRecycle.onRefreshComplete();
    }
    private void loadMore1(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadItem();
            }
        }, 1000);
    }
    private void loadItem(){
        for (int i = 0; i < 10; i++) {
            num++;
            a.add(new DemoData(num + ""));
        }
        mAdapter.notifyItemRangeInserted(mAdapter.getItemCount(), 10);
        mWanRecycle.onRefreshComplete();
    }
    private void initRecycle(){
        mWanRecycle = (WanRecycleView) findViewById(R.id.group_member_recycle_view);
        mWanRecycle.setMode(PullToRefreshBase.Mode.BOTH);
        mWanRecycle.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                refreshItem();
                Toast.makeText(DemoRefreshActivity.this, "刷新", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                loadMore1();
                Toast.makeText(DemoRefreshActivity.this, "加载更多", Toast.LENGTH_SHORT).show();
            }
        });
        mAdapter = new DemoPullAdapter(this, a);
        mRecycle = mWanRecycle.getRefreshableView();
        mRecycle.setAdapter(mAdapter);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecycle.setHasFixedSize(true);
        mRecycle.setLayoutManager(layoutManager);
        mRecycle.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).
                color(getResources().getColor(R.color.group_member_bg)).
                size(getResources().getDimensionPixelSize(R.dimen.group_item_divider)).
                showLastDivider().
                build());

    }

    private List<DemoData> getFake() {
        List<DemoData> a = new ArrayList<>();
        num = 0;
        for (int i = 0; i < 10; i++) {
            num++;
            a.add(new DemoData(num + ""));
        }
        return a;
    }

    public void refreshItem() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshItems();
            }
        }, 1000);
    }
}

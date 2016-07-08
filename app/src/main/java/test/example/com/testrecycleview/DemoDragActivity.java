package test.example.com.testrecycleview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

import test.example.com.testrecycleview.adapter.DemoDragAdapter;
import test.example.com.testrecycleview.divider.HorizontalDividerItemDecoration;
import test.example.com.testrecycleview.entity.DemoData;
import test.example.com.testrecycleview.helper.OnStartDragListener;
import test.example.com.testrecycleview.helper.SimpleItemTouchHelperCallback;

/**
 * 滑动删除以及拖动效果
 */
public class DemoDragActivity extends Activity implements OnStartDragListener {
    private RecyclerView mRecycle;
    private ItemTouchHelper mItenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_demo);
        initBody();
    }

    private void initBody() {
        List<DemoData> a = getFake();
        mRecycle = (RecyclerView) findViewById(R.id.group_member_recycle_view);
        DemoDragAdapter adapter = new DemoDragAdapter(this, a,this);
        mRecycle.setAdapter(adapter);
        mRecycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecycle.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).
                color(getResources().getColor(R.color.group_member_bg)).
                size(getResources().getDimensionPixelSize(R.dimen.group_item_divider)).
                showLastDivider().
                build());

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        mItenHelper = new ItemTouchHelper(callback);
        mItenHelper.attachToRecyclerView(mRecycle);
    }

    private List<DemoData> getFake() {
        List<DemoData> a = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            a.add(new DemoData(i + ""));
        }
        return a;
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItenHelper.startDrag(viewHolder);
    }
}

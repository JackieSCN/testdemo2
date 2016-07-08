package test.example.com.testrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.example.com.testrecycleview.entity.DemoData;
import test.example.com.testrecycleview.R;

/**
 * Created by jackie.sun on 2015/11/26.
 */
public class DemoAdapter extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<DemoData> mData;
    private LayoutInflater mInflater;

    public DemoAdapter(Context mContext, List<DemoData> data) {
        this.mContext = mContext;
        this.mData = data;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = mInflater.inflate(R.layout.group_adapter_item1, parent, false);
            return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
            DemoData memebr = mData.get(position);
            ((ItemViewHolder) holder).name.setText(memebr.getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ItemViewHolder extends ViewHolder {
        TextView name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.group_member_name);
        }
    }

}

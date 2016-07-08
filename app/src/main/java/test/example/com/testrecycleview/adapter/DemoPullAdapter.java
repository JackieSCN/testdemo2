package test.example.com.testrecycleview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import test.example.com.testrecycleview.R;
import test.example.com.testrecycleview.entity.DemoData;

/**
 * Created by jackie.sun on 2015/11/26.
 */
public class DemoPullAdapter extends RecyclerView.Adapter<ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private Context mContext;
    private List<DemoData> mData;
    private LayoutInflater mInflater;

    public DemoPullAdapter(Context mContext, List<DemoData> data) {
        this.mContext = mContext;
        this.mData = data;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    public void refreshData(List<DemoData> data){
        mData.clear();
        mData.addAll(data);
        notifyDataSetChanged();
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            View view = mInflater.inflate(R.layout.group_adapter_refresh_item, parent, false);
            return new ItemViewHolder(view);
        } else if (viewType == TYPE_FOOTER) {
            View view = mInflater.inflate(R.layout.group_member_item_footer_layout, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            DemoData memebr = mData.get(position);
            //        initImageLoader.getImageLoader().displayImage(url, ((ItemViewHolder) holder).icon, initImageLoader.getOptions());
            ((ItemViewHolder) holder).name.setText(memebr.getName());
        } else if (holder instanceof FooterViewHolder) {
            ((FooterViewHolder) holder).exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    class ItemViewHolder extends ViewHolder {
        TextView name;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.group_member_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext,"aaa",Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    class FooterViewHolder extends ViewHolder {
        Button exit;

        public FooterViewHolder(View itemView) {
            super(itemView);
            exit = (Button) itemView.findViewById(R.id.group_exit);
        }
    }

}

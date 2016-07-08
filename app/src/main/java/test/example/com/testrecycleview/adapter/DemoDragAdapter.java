package test.example.com.testrecycleview.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import test.example.com.testrecycleview.entity.DemoData;
import test.example.com.testrecycleview.helper.ItemTouchHelperForAdapter;
import test.example.com.testrecycleview.helper.ItemTouchHelperForViewHolder;
import test.example.com.testrecycleview.helper.OnStartDragListener;
import test.example.com.testrecycleview.R;

/**
 * Created by jackie.sun on 2015/11/26.
 */
public class DemoDragAdapter extends RecyclerView.Adapter<DemoDragAdapter.ItemViewHolder> implements ItemTouchHelperForAdapter {
    private Context mContext;
    private List<DemoData> mData;
    private LayoutInflater mInflater;
    private OnStartDragListener mListener;

    public DemoDragAdapter(Context mContext, List<DemoData> data, OnStartDragListener listener) {
        this.mContext = mContext;
        this.mData = data;
        this.mInflater = LayoutInflater.from(mContext);
        this.mListener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.group_adapter_item2, parent, false);
        ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        DemoData memebr = mData.get(position);
        holder.name.setText(memebr.getName());
        holder.handle.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mData, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperForViewHolder {
        TextView name;
        ImageView handle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.group_member_name);
            handle = (ImageView) itemView.findViewById(R.id.handle);
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0xffffffff);
        }
    }

}

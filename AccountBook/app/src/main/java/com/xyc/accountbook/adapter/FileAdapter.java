package com.xyc.accountbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xyc.accountbook.R;
import com.xyc.accountbook.bean.FileBean;

/**
 * Created by xieyusheng on 2018/8/17.
 */

public class FileAdapter extends BaseAdapter<FileBean> {

    private LayoutInflater mLayoutInflater;
    private OnFileItemClickListener mOnClickListener;

    public FileAdapter(LayoutInflater layoutInflater) {
        this.mLayoutInflater = layoutInflater;
    }

    public void setOnFileItemClickListener(OnFileItemClickListener listener) {
        mOnClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FileAdapter.FileViewHolder(mLayoutInflater.inflate(R.layout.item_file, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        FileBean fileBean = getItem(position);
        if (holder instanceof FileAdapter.FileViewHolder) {
            ((FileAdapter.FileViewHolder) holder).bindBean(fileBean);
        } else {
            throw new IllegalStateException("Illegal state Exception onBindviewHolder");
        }
    }

    private class FileViewHolder extends RecyclerView.ViewHolder {

        private TextView tvFileName;
        private TextView tvFilePath;

        public FileViewHolder(View itemView) {
            super(itemView);
            tvFileName = (TextView) itemView.findViewById(R.id.fileName);
            tvFilePath = (TextView) itemView.findViewById(R.id.filePath);
        }

        public void bindBean(final FileBean fileBean) {
            tvFileName.setText(fileBean.getFileName());
            tvFilePath.setText("位置：" + fileBean.getFilePath());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickListener.onItemClick(fileBean);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    mOnClickListener.onItemLongClicked(fileBean);
                    return true;
                }
            });
        }
    }

    public interface OnFileItemClickListener {
        void onItemClick(FileBean fileBean);

        void onItemLongClicked(FileBean fileBean);
    }
}

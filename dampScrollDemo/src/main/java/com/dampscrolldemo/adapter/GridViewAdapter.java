/**
 * @author xieyue 
 * @date 创建时间：2015年1月29日 上午11:13:52
 * @version 1.0
 */
package com.dampscrolldemo.adapter;

import java.util.List;

import android.R.integer;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.dampscrolldemo.R;

public class GridViewAdapter extends BaseAdapter {

	private Context mContext;
	private List<Drawable> datas;
	private LayoutInflater mInflater;
	private int mWidth;
	private int mHeight;
	
	public GridViewAdapter(Context mContext, List<Drawable> datas, int width) {
		this.mContext = mContext;
		this.datas = datas;
		mInflater = LayoutInflater.from(mContext);
		setItemSize(width);
	}
	
	/**
	 * 根据屏幕大小设置gridview的item大小
	 * @param width
	 */
	private void setItemSize(int width) {
		this.mWidth = width / 2;
		this.mHeight = mWidth * 1260 / 980;
	}
	
	@SuppressLint("NewApi")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView==null){
			convertView = mInflater.inflate(R.layout.item_gridview, null);
			holder = new ViewHolder();
			holder.itemView = (ImageView) convertView.findViewById(R.id.item_image);
			convertView.setTag(holder);
		}else{
			holder=(ViewHolder) convertView.getTag();
		}
		
		holder.itemView.setBackground(datas.get(position));
		AbsListView.LayoutParams param = new AbsListView.LayoutParams(mWidth, mHeight);
		convertView.setLayoutParams(param);
		return convertView;
	}
	
	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	class ViewHolder {
		 ImageView itemView;
	}
	
}

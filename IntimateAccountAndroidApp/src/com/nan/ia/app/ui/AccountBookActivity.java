/**
 * @ClassName:     AccountBookActivity.java
 * @Description:   TODO(用一句话描述该文件做什么) 
 * 
 * @author         weijiangnan create on 2015年5月22日 
 */

package com.nan.ia.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.TextView;

import com.nan.ia.app.R;
import com.nan.ia.app.biz.BizFacade;
import com.nan.ia.app.data.AppData;
import com.nan.ia.app.ui.EditAccountBookActivity.EditAccountBookType;
import com.nan.ia.app.utils.Utils;
import com.nan.ia.app.widget.CustomDialogBuilder;
import com.nan.ia.app.widget.CustomSwipeListView;
import com.nan.ia.app.widget.CustomToast;
import com.nan.ia.common.entities.AccountBook;

public class AccountBookActivity extends BaseActionBarActivity {
	CustomSwipeListView mListView = null;
	Scroller mScroller;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_account_book);
		
		mListView = (CustomSwipeListView) findViewById(R.id.list_account_book);
		mListView.setAdapter(new AccountBookAdapter(this));
		mListView.setOffsetLeft(getWindowManager().getDefaultDisplay().getWidth() -
				getResources().getDimension(R.dimen.list_account_book_item_swipe_menu_width));
		
		ImageView imageView = new ImageView(this);
		imageView.setImageResource(R.drawable.btn_add_white);
		imageView.setClickable(true);
		imageView.setLayoutParams(new LayoutParams(Utils.dip2px(this, 32), Utils.dip2px(this, 32)));
		imageView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditAccountBookActivity.TransData transData = new EditAccountBookActivity.TransData();
				transData.editAccountBookType = EditAccountBookType.NEW;
				
				Intent intent = new Intent(AccountBookActivity.this, EditAccountBookActivity.class);
				startActivity(createTransDataIntent(intent, transData));
			}
		});
		
		mActionBar.customRightView(this, imageView);
	}
	
    @Override
	protected void onStop() {
    	mListView.closeOpenedItems();
		super.onStop();
	}

	private static class AccountBookAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private AccountBookActivity mActivity;

        public AccountBookAdapter(AccountBookActivity activity) {
            // Cache the LayoutInflate to avoid asking for a new one each time.
            mInflater = LayoutInflater.from(activity);
            mActivity = activity;
        }

        /**
         * The number of items in the list is determined by the number of speeches
         * in our array.
         *
         * @see android.widget.ListAdapter#getCount()
         */
        public int getCount() {
            return AppData.getAccountBooks().size();
        }

        /**
         * Since the data comes from an array, just returning the index is
         * sufficent to get at the data. If we were using a more complex data
         * structure, we would return whatever object represents one row in the
         * list.
         *
         * @see android.widget.ListAdapter#getItem(int)
         */
        public Object getItem(int position) {
            return position;
        }

        /**
         * Use the array index as a unique id.
         *
         * @see android.widget.ListAdapter#getItemId(int)
         */
        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.list_item_account_book, null);

                holder = new ViewHolder();
                View frontView = convertView.findViewById(R.id.id_front);
                holder.textAccountBookName = (TextView) frontView.findViewById(R.id.text_account_book_name);
                holder.textAccountBookDescription = (TextView) frontView.findViewById(R.id.text_account_book_description);
                
                holder.btnEdit = convertView.findViewById(R.id.btn_edit);
                holder.btnDelete = convertView.findViewById(R.id.btn_delete);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.textAccountBookName.setText(AppData.getAccountBooks().get(position).getName());
            holder.textAccountBookDescription.setText(AppData.getAccountBooks().get(position).getDescription());

            holder.btnEdit.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					editAccountBook(position);
				}
			});
            
            holder.btnDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					deleteAccountBook(position);
				}
			});
            
            return convertView;
        }

        static class ViewHolder {
            TextView textAccountBookName;
            TextView textAccountBookDescription;
        	View btnDelete;
        	View btnEdit;
        }
        
        void editAccountBook(int position) {
			if (0 > position || position >= AppData.getAccountBooks().size()) {
				return;
			}

			EditAccountBookActivity.TransData transData = new EditAccountBookActivity.TransData();
			transData.editAccountBookType = EditAccountBookType.EDIT;
			transData.accountBook = AppData.getAccountBooks().get(
					position);
			
			Intent intent = new Intent(mActivity, EditAccountBookActivity.class);
			mActivity.startActivity(mActivity.createTransDataIntent(intent, transData));
        }
        
		void deleteAccountBook(int position) {
			if (0 > position || position >= AppData.getAccountBooks().size()) {
				return;
			}

			final AccountBook accountBook = AppData.getAccountBooks().get(
					position);
			
			if (accountBook.getAccountBookId() == AppData.getCurrentAccountBookId()) {
				// 无法删除当前账本
				CustomToast.showToast(R.string.msg_can_not_delete_account_book);
				return;
			}
			
			final CustomDialogBuilder dialogBuilder = CustomDialogBuilder
					.getInstance(mActivity);
			String msg = String.format(mActivity.getString(R.string.fmt_msg_makesure_delete_account_book),
					accountBook.getName());
			dialogBuilder.withButton2Drawable(R.drawable.selector_btn_inverse)
					.withMessage(msg)
					.withButton2TextColor(mActivity.getResources().getColor(R.color.app_main_color))
					.withButton1Drawable(R.drawable.selector_btn_red)
					.withButton1Text(mActivity.getString(R.string.btn_delete))
					.withButton2Text(mActivity.getString(R.string.btn_cancel))
					.setButton1Click(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							BizFacade.getInstance().deleteAccountBook(
									accountBook.getAccountBookId());
							dialogBuilder.dismiss();
							AccountBookAdapter.this.notifyDataSetChanged();
						}
					}).setButton2Click(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							dialogBuilder.dismiss();
						}
					}).show();
			
			// 关闭菜单
			mActivity.mListView.closeOpenedItems();
        }
    }
}
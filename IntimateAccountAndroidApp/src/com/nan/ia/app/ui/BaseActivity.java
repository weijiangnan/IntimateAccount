package com.nan.ia.app.ui;

import java.io.Serializable;

import com.nan.ia.app.utils.LogUtils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

public class BaseActivity extends FragmentActivity {
	protected static final String EXTRA_KEY_TRANS_DATA = "EXTRA_KEY_TRANS_DATA";
	
	private static final String TAG = "BaseActivity";
	/******************************** 【Activity LifeCycle For Debug】 *******************************************/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		LogUtils.d(TAG, this.getClass().getSimpleName()
				+ " onCreate() invoked!!");
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}

	@Override
	protected void onStart() {
		LogUtils.d(TAG, this.getClass().getSimpleName() + " onStart() invoked!!");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		LogUtils.d(TAG, this.getClass().getSimpleName()
				+ " onRestart() invoked!!");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		LogUtils.d(TAG, this.getClass().getSimpleName()
				+ " onResume() invoked!!");
		super.onResume();
	}

	@Override
	protected void onPause() {
		LogUtils.d(TAG, this.getClass().getSimpleName() + " onPause() invoked!!");
		super.onPause();
	}

	@Override
	protected void onStop() {
		LogUtils.d(TAG, this.getClass().getSimpleName() + " onStop() invoked!!");
		super.onStop();
	}

	@Override
	public void onDestroy() {
		LogUtils.d(TAG, this.getClass().getSimpleName()
				+ " onDestroy() invoked!!");
		super.onDestroy();
	}
	
	protected Intent createTransDataIntent(Intent intent, Serializable transData) {
		if (null == intent) {
			return null;
		}
		
		intent.putExtra(EXTRA_KEY_TRANS_DATA, transData);
		return intent;
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T readTransData() {
		return (T) getIntent().getSerializableExtra(EXTRA_KEY_TRANS_DATA);
	}
}

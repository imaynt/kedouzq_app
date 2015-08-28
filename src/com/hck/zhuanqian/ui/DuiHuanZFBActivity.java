package com.hck.zhuanqian.ui;

import java.util.ArrayList;
import java.util.List;

import com.hck.httpserver.JsonHttpResponseHandler;
import com.hck.httpserver.RequestParams;
import com.hck.zhuanqian.R;
import com.hck.zhuanqian.bean.UserBean;
import com.hck.zhuanqian.data.MyData;
import com.hck.zhuanqian.net.Request;
import com.hck.zhuanqian.util.LogUtil;
import com.hck.zhuanqian.view.AlertDialogs;
import com.hck.zhuanqian.view.MyToast;
import com.hck.zhuanqian.view.Pdialog;
import com.hck.zhuanqian.view.AlertDialogs.OneBtOnclick;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DuiHuanZFBActivity extends BaseActivity implements OnClickListener {
	private static final int DUIHUAN_1ZFB = 0;
	private static final int DUIHUAN_5ZFB = 1;
	private static final int DUIHUAN_10ZFB = 2;
	private static final int DUIHUAN_20ZFB = 3;
	private static final int DUIHUAN_50ZFB = 4;
	private static final int DUIHUAN_100ZFB = 5;
	private List<Button> buttons;
	private int postion;
	private TextView myJinBiTextView, needPoinTextView;
	private int money;
	private int needPoint;
	private int userPoint;
	private Button subMitBtn;
	private TextView errorTextView;
	private EditText zfbEditText, userEditText;
	private int zhifubaoSize;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_duihuan_zfb);
		initTitle("兑换支付宝");
		initData();
		initView();
	}

	private void initData() {
		userPoint = (int) (MyData.getData().getUserBean().getAllKeDouBi());
		money = userPoint / 1000;
	}

	private void initView() {
		buttons = new ArrayList<>();
		buttons.add((Button) findViewById(R.id.duihuan_1));
		buttons.add((Button) findViewById(R.id.duihuan_5));
		buttons.add((Button) findViewById(R.id.duihuan_10));
		buttons.add((Button) findViewById(R.id.duihuan_20));
		buttons.add((Button) findViewById(R.id.duihuan_50));
		buttons.add((Button) findViewById(R.id.duihuan_100));
		findViewById(R.id.duihuan_1).setOnClickListener(this);
		findViewById(R.id.duihuan_5).setOnClickListener(this);
		findViewById(R.id.duihuan_10).setOnClickListener(this);
		findViewById(R.id.duihuan_20).setOnClickListener(this);
		findViewById(R.id.duihuan_50).setOnClickListener(this);
		findViewById(R.id.duihuan_100).setOnClickListener(this);
		myJinBiTextView = (TextView) findViewById(R.id.duihuan_my_jinbi);
		needPoinTextView = (TextView) findViewById(R.id.duihuan_need_point);
		myJinBiTextView.setText(MyData.getData().getUserBean().getAllKeDouBi()
				+ "个" + " 至少可兑换支付宝" + money + "元");
		subMitBtn = (Button) findViewById(R.id.duihuan_submit);
		subMitBtn.setOnClickListener(this);
		errorTextView = (TextView) findViewById(R.id.duihuan_error_text);
		zfbEditText = (EditText) findViewById(R.id.duihuan_zfb_name);
		zfbEditText.setText(MyData.getData().getUserBean().getZhifubao());
		userEditText = (EditText) findViewById(R.id.duihuan_zfb_user_name);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.duihuan_1:
			postion = 0;
			zhifubaoSize = 1;
			updateUi();
			break;
		case R.id.duihuan_5:
			postion = 1;
			zhifubaoSize = 5;
			updateUi();
			break;
		case R.id.duihuan_10:
			zhifubaoSize = 10;
			postion = 2;
			updateUi();
			break;
		case R.id.duihuan_20:
			zhifubaoSize = 20;
			postion = 3;
			updateUi();
			break;
		case R.id.duihuan_50:
			zhifubaoSize = 50;
			postion = 4;
			updateUi();
			break;
		case R.id.duihuan_100:
			zhifubaoSize = 100;
			postion = 5;
			updateUi();
			break;
		case R.id.duihuan_submit:
			checkoutData();
			break;
		default:
			break;
		}

	}

	private void checkoutData() {
		String qqString = zfbEditText.getText().toString();
		String userName = userEditText.getText().toString();
		if (TextUtils.isEmpty(qqString)) {
			MyToast.showCustomerToast("qq号码不能为空");
			return;
		}
		sendDataToServer(qqString, userName);
	}

	private void sendDataToServer(String zfb, String userName) {
		UserBean userBean = MyData.getData().getUserBean();
		if (zhifubaoSize <= 0) {
			MyToast.showCustomerToast("兑换数量不能为0个");
			return;
		}
		Pdialog.showDialog(this, "正在下单 请稍等..", false);
		params = new RequestParams();
		params.put("id", userBean.getId() + "");
		params.put("money", needPoint + "");
		params.put("content", "支付宝账号：" + zfb + "h兑换人民币" + zhifubaoSize + "元");
		params.put("shangjia1", userBean.getShangjia1() + "");
		params.put("shangjia2", userBean.getShangjia2() + "");
		params.put("shangjia3", userBean.getShangjia3() + "");
		params.put("userName", userBean.getName());
		params.put("size", zhifubaoSize + "");
		Request.addOrder(params, new JsonHttpResponseHandler() {
			public void onFinish(String url) {
				Pdialog.hiddenDialog();
			};

			public void onSuccess(int statusCode, org.json.JSONObject response) {
				LogUtil.D(response.toString());
				try {
					isOK = response.getBoolean("isok");
					if (isOK) {
						int jinbi = response.getInt("jinbi");
						if (jinbi == -1) {
							AlertDialogs.alert(DuiHuanZFBActivity.this, "失败",
									"金币不足，请到首页下拉更新用户信息", true,

									new OneBtOnclick() {

										@Override
										public void callBack(int tag) {
											finish();
										}
									}, 1);
						} else {
							updateUser();
							remindNeedPoint();
							showSuccessDialog();
						}

					} else {
						MyToast.showCustomerToast("网络异常 下单失败");
					}
				} catch (Exception e) {
					MyToast.showCustomerToast("网络异常 下单失败");
				}

			};

			public void onFailure(Throwable error, String content) {
				LogUtil.D(content + error);
				MyToast.showCustomerToast("网络异常 下单失败");
			};
		});
	}

	private void updateUser() {
		long jinbi = MyData.getData().getUserBean().getAllKeDouBi();
		long zhuanqian = MyData.getData().getUserBean().getAllMoney();
		MyData.getData().getUserBean().setAllKeDouBi(jinbi - needPoint);
		MyData.getData().getUserBean().setAllMoney(zhuanqian + zhifubaoSize);
		initData();
	}

	private void showSuccessDialog() {
		AlertDialogs.alert(this, "下单成功",
				"下单成功 1-2天内处理完成" + "若有疑问 请在意见反馈处 进行反馈", true,

				new OneBtOnclick() {

					@Override
					public void callBack(int tag) {
						finish();
					}
				}, 1);
	}

	private void updateUi() {
		changeBtnbg();
		remindNeedPoint();
	}

	private void changeBtnbg() {
		for (int i = 0; i < buttons.size(); i++) {
			if (i == postion) {
				buttons.get(postion).setBackgroundResource(
						R.drawable.duihuan_btn_bg_press);

			} else {
				buttons.get(i).setBackgroundResource(
						R.drawable.duihuan_btn_normol);
			}
		}
	}

	private void remindNeedPoint() {
		switch (postion) {
		case DUIHUAN_1ZFB:
			needPoint = 1000;
			break;
		case DUIHUAN_5ZFB:
			needPoint = (int) (5000 * 0.98);
			break;
		case DUIHUAN_10ZFB:
			needPoint = (int) (10000 * 0.95);
			break;
		case DUIHUAN_20ZFB:
			needPoint = (int) (20000 * 0.9);
			break;
		case DUIHUAN_50ZFB:
			needPoint = (int) (50000 * 0.85);
			break;
		case DUIHUAN_100ZFB:
			needPoint = (int) (100000 * 0.80);
			break;

		default:
			break;
		}
		if (userPoint < needPoint) {
			needPoinTextView.setText("需要金币: " + needPoint);
			subMitBtn.setEnabled(false);
			errorTextView.setVisibility(View.VISIBLE);

		} else {
			needPoinTextView.setText("需要金币: " + needPoint + "个");
			subMitBtn.setEnabled(true);
			errorTextView.setVisibility(View.GONE);
		}

	}

}

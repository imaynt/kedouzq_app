package com.hck.zhuanqian.ui;

import org.json.JSONObject;

import u.aly.br;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.hck.httpserver.JsonHttpResponseHandler;
import com.hck.kedouzq.R;
import com.hck.zhuanqian.adapter.KindAdapter;
import com.hck.zhuanqian.data.Contans;
import com.hck.zhuanqian.data.KindData;
import com.hck.zhuanqian.net.Request;
import com.hck.zhuanqian.util.JsonUtils;
import com.hck.zhuanqian.util.LogUtil;
import com.hck.zhuanqian.view.MyToast;
import com.hck.zhuanqian.view.Pdialog;

public class KindActivity extends BaseActivity {
    private ListView listView;
    private View errorView;
    private KindData kBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kind);
        initView();
        getKindFromServer();
        setListener();
    }

    private void initView() {
        initTitle("免费获取金币换钱");
        listView = (ListView) findViewById(R.id.kind_listview);
        errorView = LayoutInflater.from(this).inflate(R.layout.error_view, null);
    }

    private void getKindFromServer() {
        Pdialog.showDialog(this, "获取数据中...", true);
        Request.getKind(new JsonHttpResponseHandler() {
            @Override
            public void onFailure(Throwable error, String content) {
                super.onFailure(error, content);
                listView.addHeaderView(errorView);
                MyToast.showCustomerToast("获取数据失败");
            }

            @Override
            public void onSuccess(int statusCode, JSONObject response) {
                super.onSuccess(statusCode, response);
                try {
                    kBean = JsonUtils.parse(response.toString(), KindData.class);
                    updateUI();
                } catch (Exception e) {
                    listView.addHeaderView(errorView);
                }
            }

            @Override
            public void onFinish(String url) {
                super.onFinish(url);
                Pdialog.hiddenDialog();
            }
        });
    }

    private void updateUI() {
        listView.setAdapter(new KindAdapter(kBean.getKindBeans(), this));
    }

    private void setListener() {
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                try {
                    int type = kBean.getKindBeans().get(arg2).getAid();
                    int maxSize = kBean.getKindBeans().get(arg2).getNum();
                    startActivity(type, maxSize);
                } catch (Exception e) {
                    MyToast.showCustomerToast("未知错误 请重试");
                }

            }

        });
    }

    private void startActivity(int type, int maxSize) {
        Intent intent = new Intent();
        intent.putExtra("type", type);
        intent.putExtra("maxNum", maxSize);
        switch (type) {
        case Contans.AD_DIANLE:
            intent.setClass(this, DLActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_DIANCAI:
            intent.setClass(this, DianCaiActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_DUOMENG:
            intent.setClass(this, DuoMengActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_DATOUNIAO:
            intent.setClass(this, DaTouNiaoActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_YOUMI:
            intent.setClass(this, YouMiActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_YEGUO:
            intent.setClass(this, YeGuoActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_GUOMENG:
            intent.setClass(this, GuoMengActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_ZHONGYI:
            intent.setClass(this, ZhongYiActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_BEIDUO:
            intent.setClass(this, BeiDuoActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_KEKE:
            intent.setClass(this, KeKeActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_KIND_JINGDAI:
            intent.setClass(this, JinDaiActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_ID_WANPU:
            intent.setClass(this, WanPuActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_ID_LEDIAN:
            intent.setClass(this, LeDianActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_KIND_DIQI:
            intent.setClass(this, DiQiActivity.class);
            startActivity(intent);
            break;
        case Contans.AD_KIND_XIAOTANG:
            intent.setClass(this, TangGuoActivity.class);
            startActivity(intent);
            break;
        default:
            break;
        }
    }
}

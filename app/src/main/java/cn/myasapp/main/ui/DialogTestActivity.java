package cn.myasapp.main.ui;

import android.app.Dialog;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cp.mylibrary.custom.TitleBarView;
import com.cp.mylibrary.dialog.BasicDialog;
import com.cp.mylibrary.dialog.DialogHelper;

import com.cp.mylibrary.dialog.EditTextDialog;
import com.cp.mylibrary.dialog.ShareDialog;
import com.cp.mylibrary.dialog.WaitDialog;
import com.cp.mylibrary.interf.ShareListener;
import com.cp.mylibrary.pullto.XRefreshView;
import com.cp.mylibrary.utils.LogCp;
import com.cp.mylibrary.utils.ShowToastUtil;

import org.kymjs.kjframe.ui.BindView;

import cn.myasapp.main.R;
/**
 * Created by Jerry on 2016/6/28.
 * <p>
 * 测试对话框
 */
public class DialogTestActivity extends BaseActivity {


    private Dialog simplecDialog;


    @BindView(id = R.id.dialog_test_title)
    private TitleBarView dialog_test_title;


    @BindView(id = R.id.dialog_wait, click = true)
    private TextView dialog_wait;
    @BindView(id = R.id.dialog_share, click = true)
    private TextView dialog_share;


    @BindView(id = R.id.refresh_view_dialog)
    private XRefreshView refresh_view_dialog;


    private ShareDialog mDialog;


    @Override
    public void setRootView() {
        super.setRootView();

        setContentView(R.layout.activity_test_dialog);
    }

    @Override
    protected void initView() {
        super.initView();

        dialog_test_title.setTitleStr("测试对话框");
        dialog_test_title.setTitleBackClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        refresh_view_dialog.setXRefreshViewListener(myPullToListner);

    }

    @Override
    public void widgetClick(View v) {
        super.widgetClick(v);

        switch (v.getId()) {
            case R.id.dialog_wait:


                // DialogHelper.getWaitDialog(this,"加载中").show();

                //    DialogHelper.getCancelableWaitDialog(this,"加载中").show();

                //     DialogHelper.getPinterestDialog(this);
//
//                simplecDialog = DialogHelper.configDialog(DialogTestActivity.this,
//                        "温馨提示", "确认删除", "删除", "消",
//                        new View.OnClickListener() {
//
//                            @Override
//                            public void onClick(View arg0) {
//                                switch (arg0.getId()) {
//                                    case R.id.base_config_dialog_sure_btn_b:
//
//                                        simplecDialog.dismiss();
//
//                                        break;
//                                    case R.id.base_config_dialog_cannel_btn_b:
//                                        simplecDialog.dismiss();
//
//                                        break;
//                                    default:
//                                        break;
//                                }
//
//                            }
//                        }).getConfigDialog();
//                simplecDialog.show();




                 // 提示对话框

                 simplecDialog = DialogHelper.getTiShiDialog(DialogTestActivity.this,"标题","提示","知道了", new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {

                          switch (v.getId())
                          {
                               case R.id.base_tishi_dialog_sure_btn_b:
//
//                                simplecDialog.dismiss();
//
//                                EditText editText = DialogHelper.getDialogEditText();
//                                String str = editText.getText().toString();
//                                ShowToastUtil.showToast(DialogTestActivity.this," ,," + str);
//
                              break;

                          }

                     }
                 }).getConfigDialog();

                simplecDialog.show();


//                EditTextDialog dialog = (EditTextDialog) DialogHelper.getEditTextDialog(DialogTestActivity.this, "来啊，", "cc");
//                dialog.show();
//                dialog.getEditText();


//
//                simplecDialog = DialogHelper.EditTextDialog(DialogTestActivity.this, "delete ", null,null, " 不超过", "confim ", "ttest", new View.OnClickListener() {
//
//
//                    @Override
//                    public void onClick(View v) {
//
//                        switch (v.getId()) {
//                            case R.id.base_config_dialog_sure_btn:
//
//                                simplecDialog.dismiss();
//
//                                EditText editText = DialogHelper.getDialogEditText();
//                                String str = editText.getText().toString();
//                                ShowToastUtil.showToast(DialogTestActivity.this," ,," + str);
//
//                                break;
//                            case R.id.base_config_dialog_cannel_btn:
//                                simplecDialog.dismiss();
//
//                                break;
//                            default:
//                                break;
//                        }
//
//                    }
//                }  ).getConfigDialog();
//
//                simplecDialog.show();
//


                break;

            case R.id.dialog_share:

//                if (mDialog == null)
//                    mDialog = new ShareDialog(this  ,this);
//                mDialog.setShareInfo("标题","内容","www.baidu.com","http://img0.imgtn.bdimg.com/it/u=1126541908,2603454962&fm=21&gp=0.jpg");
//                mDialog.setCancelable(true);
//                mDialog.setCanceledOnTouchOutside(true);
//                mDialog.setTitle(R.string.share_to);
//                mDialog.show();
//
//


                WaitDialog waitDialog = DialogHelper.getWaitDialog(DialogTestActivity.this,"加载中...");
                 waitDialog.show();


//            Dialog    simplecDialog = BasicDialog.versionDialog(DialogTestActivity.this, "发现新版本:",
//                        "有新处，，，", "立即更新", "下次再说", new View.OnClickListener() {
//
//                            @Override
//                            public void onClick(View arg0) {
//
//
//
//
//                            }
//                        }).getConfigDialog();
//
//                simplecDialog.show();


                break;

        }
    }


    public XRefreshView.XRefreshViewListener myPullToListner = new XRefreshView.XRefreshViewListener() {


        @Override
        public void onRefresh() {

            LogCp.i(LogCp.CP, DialogTestActivity.class + " 刷新了，， ");


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    refresh_view_dialog.stopRefresh();
                }
            }, 2000);


        }

        @Override
        public void onLoadMore(boolean isSilence) {

        }

        @Override
        public void onRelease(float direction) {

        }

        @Override
        public void onHeaderMove(double offset, int offsetY) {

        }


    };

}

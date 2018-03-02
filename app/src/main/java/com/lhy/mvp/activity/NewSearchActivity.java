package com.lhy.mvp.activity;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lhy.mvp.R;
import com.lhy.mvp.adapter.FragmentTabAdapter;
import com.lhy.mvp.fragment.SearchPostFragmet;
import com.lhy.mvp.fragment.SearchUserFragmet;
import com.lhy.mvp.module.base.BaseActivity;
import com.lhy.mvp.widget.EditTextWithDel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewSearchActivity extends BaseActivity implements View.OnClickListener {

    public List<Fragment> fragments = new ArrayList<Fragment>();
    @BindView(R.id.et_search_text)
    EditTextWithDel etSearchText;
    @BindView(R.id.ll_search)
    LinearLayout llSearch;
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.tab_rb_stock)
    RadioButton tabRbStock;
    @BindView(R.id.tab_rb_user)
    RadioButton tabRbUser;
    @BindView(R.id.tab_rb_post)
    RadioButton tabRbPost;
    @BindView(R.id.tabs_rg)
    RadioGroup tabsRg;
    @BindView(R.id.tab_content)
    FrameLayout tabContent;


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_new_search;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        fragments.add(new SearchUserFragmet());
        fragments.add(new SearchPostFragmet());
        fragments.add(new SearchPostFragmet());
        etSearchText.setHint("搜索");
        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content, tabsRg);
        tabAdapter.setOnRgsExtraCheckedChangedListener(new FragmentTabAdapter.OnRgsExtraCheckedChangedListener() {
            @Override
            public void OnRgsExtraCheckedChanged(RadioGroup radioGroup, int checkedId, int index) {
                if (checkedId == R.id.tab_rb_stock) {
                    etSearchText.setHint("搜索");
                } else if (checkedId == R.id.tab_rb_user) {
                    etSearchText.setHint("搜索");
                } else if (checkedId == R.id.tab_rb_post) {
                    etSearchText.setHint("搜索");
                }
            }
        });

        initListener();
    }

    @Override
    protected void updateViews(boolean isRefresh) {

    }



    private void initListener() {
        tvCancel.setOnClickListener(this);
        etSearchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
        }
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.slide_top_colose);
    }
}

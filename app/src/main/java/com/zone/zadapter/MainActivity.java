package com.zone.zadapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Administrator on 2016/5/5.
 */
public class MainActivity extends Activity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_main);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_ListView:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.bt_RecyclerView_Linear:
                startActivity(new Intent(this, RecyclerActivity.class).putExtra("type","Linear"));
                break;
            case R.id.bt_RecyclerView_Grid:
                startActivity(new Intent(this, RecyclerActivity.class).putExtra("type","Grid"));
                break;
            case R.id.bt_RecyclerView_StaggeredGrid:
                startActivity(new Intent(this, RecyclerActivity.class).putExtra("type","StaggeredGrid"));
                break;
            case R.id.bt_Recycler_Decoration:
                startActivity(new Intent(this, DecorationActivity.class));
                break;
            case R.id.bt_scroller_List:
                startActivity(new Intent(this, ScrollerListActivity.class));
                break;
            case R.id.bt_scroller_Grid:
                startActivity(new Intent(this, ScrollerGridActivity.class));
                break;
            case R.id.bt_scroller_Recycler:
                startActivity(new Intent(this, ScrollerRecActivity.class));
                break;
            case R.id.bt_ListViewType:
                startActivity(new Intent(this, MessAcitivity.class));
                break;
        }
    }
}

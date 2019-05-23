package cn.edu.gdpt.xxgcx.topline170000hwh.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextInsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.ButtonEnum;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;

import cn.edu.gdpt.xxgcx.topline170000hwh.R;
import cn.edu.gdpt.xxgcx.topline170000hwh.activity.AndroidCountActivity;
import cn.edu.gdpt.xxgcx.topline170000hwh.activity.JavaCountActivity;
import cn.edu.gdpt.xxgcx.topline170000hwh.utils.BuilderManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountFragment extends Fragment {
    private BoomMenuButton bmb;
    public CountFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_count, container, false);
        bmb = (BoomMenuButton) view.findViewById(R.id.bmb);
        assert bmb != null;
        //设置点击圆形菜单后显示的多个按钮为圆形且带文本的
        bmb.setButtonEnum(ButtonEnum.TextInsideCircle);
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_9_1); //设置右下角圆形菜单中有9个圆形
        //设置点击右下角圆形菜单后显示的按钮为9个圆形Button
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_9_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            addBuilder();
        }
        return view;
    }
    private void addBuilder() {
        bmb.addBuilder(new TextInsideCircleButton.Builder()
                .normalImageRes(BuilderManager.getImageResource())
                .normalTextRes(BuilderManager.getTextResource())
                .listener(new OnBMClickListener() {
                    @Override
                    public void onBoomButtonClick(int index) {
                        switch (index) {
                            case 0:
                                Intent intent = new Intent(getActivity(), AndroidCountActivity.class);
                                startActivity(intent);
                                break;
                            case 1:
                                Intent intent1 = new Intent(getActivity(), JavaCountActivity.class);
                                startActivity(intent1);
                                break;
                        }
                    }
                }));
    }
}

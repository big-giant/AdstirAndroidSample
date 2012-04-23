package com.adstir.sample;

import com.ngigroup.adstir.AdstirTerminate;
import com.ngigroup.adstir.AdstirView;

import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class AdStirSampleActivity extends Activity {
    private AdstirView adstirView;
    LinearLayout layout = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        layout = (LinearLayout) findViewById(R.id.layout_main); // �L����}��������layout��id���w�肵�Ă��������B
        adstirView = new AdstirView(this,1); // �gNo���w��̏ꍇ�̓f�t�H���g�g���g�p����܂�
        layout.addView(adstirView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)); // layout��addView���Ă��������B

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
                // Activity��onDestroy()����AdstirTerminate�N���X�����������Ă��������B
        new AdstirTerminate(this);
    }

        // AdstirView��stop���\�b�h�����s���邱�Ƃɂ��A�s�v�ȒʐM��}���邱�Ƃ��o���܂��B
    @Override
    protected void onPause() {
            super.onPause();
            adstirView.stop();
          //�eView���擾����removeView���s
            ViewGroup parent = (ViewGroup)adstirView.getParent();
            if ( parent != null ) {
                parent.removeView(adstirView);
            }
        }

        // AdstirView��start���\�b�h�����s���邱�Ƃɂ��A�ʐM���ĊJ���邱�Ƃ��o���܂��B
    @Override
    protected void onResume() {
            super.onResume();
            int index = 0;
            while(layout.getChildAt(index) != null){
                if(layout.getChildAt(index) == adstirView){
                    return;
                }
                index++;
            }
            adstirView = null;
            adstirView = new AdstirView(this,1);
            layout.addView(adstirView, new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            adstirView.start();
    }
}
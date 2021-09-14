package ar.edu.itba.pam.mygrocery.home.ui;

import android.view.View;

public class OnAddProductCancelListener implements View.OnClickListener {
    private  Runnable onClick;

    public OnAddProductCancelListener(final Runnable onClick) {
        this.onClick = onClick;
    }
    @Override
    public void onClick(View view) {
        onClick.run();
    }
}



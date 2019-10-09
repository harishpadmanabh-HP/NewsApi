package com.example.newsapi;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;

/**
 * {@link android.view.View.OnClickListener} used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 */
public class NavigationIconClickListener implements View.OnClickListener {

    private final AnimatorSet animatorSet = new AnimatorSet();
    private Context context;
    private View sheet;
    MaterialButton materialButton;
    private Interpolator interpolator;
    private int height;
    private boolean backdropShown = false;
    private Drawable openIcon;
    private Drawable closeIcon;
    String TAG="NavigationIconClickListener";

    NavigationIconClickListener(Context context, View sheet) {
        this(context, sheet, null);
    }

    NavigationIconClickListener(Context context, View sheet, @Nullable Interpolator interpolator) {
        this(context, sheet, interpolator, null, null);
        //Toast.makeText(context, ""+sheet, Toast.LENGTH_SHORT).show();

    }


    NavigationIconClickListener(
            Context context, View sheet, @Nullable Interpolator interpolator,
            @Nullable Drawable openIcon, @Nullable Drawable closeIcon) {
        this.context = context;
        this.sheet = sheet;
        this.interpolator = interpolator;
        this.openIcon = openIcon;
        this.closeIcon = closeIcon;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
    }



    @Override
    public void onClick(View view) {
        Log.e(TAG, String.valueOf(view));
        backdropShown = !backdropShown;
        Log.e(TAG, String.valueOf(backdropShown));
        if(view instanceof ImageView)
        {
            Toast.makeText(context, "instanceof ImageView", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(context, "not instanceof ImageView", Toast.LENGTH_SHORT).show();




        // Cancel the existing animations
        animatorSet.removeAllListeners();
        animatorSet.end();
        animatorSet.cancel();

        updateIcon(view);
        Log.e(TAG, "updateicon");


        final int translateY = height -
                context.getResources().getDimensionPixelSize(R.dimen.shr_product_grid_reveal_height);
        Log.e(TAG, String.valueOf(translateY));
        Log.e(TAG, "ht  "+(height));



        ObjectAnimator animator = ObjectAnimator.ofFloat(sheet, "translationY", backdropShown ? translateY : 0);
        animator.setDuration(500);
        if (interpolator != null) {
            animator.setInterpolator(interpolator);
        }
        animatorSet.play(animator);
        animator.start();
    }

    private void updateIcon(View view) {
        if (openIcon != null && closeIcon != null) {
            if (!(view instanceof ImageView)) {
                //throw new IllegalArgumentException("updateIcon() must be called on an ImageView");
                   // updatetoolbaricon(view);

                Toast.makeText(context, "not instanceof ImageView", Toast.LENGTH_SHORT).show();


            }
            if (backdropShown) {
                ((ImageView) view).setImageDrawable(closeIcon);
            } else {
                ((ImageView) view).setImageDrawable(openIcon);
            }
        }
    }

    private void updatetoolbaricon(View view) {
        Toast.makeText(context, "cant update "+view, Toast.LENGTH_LONG).show();
        openIcon=ContextCompat.getDrawable(context, R.drawable.ic_gps_fixed_black_24dp);
        closeIcon=context.getResources().getDrawable(R.drawable.shr_close_menu);
    }
}

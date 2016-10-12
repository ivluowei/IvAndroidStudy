package cn.lw.study.utils;

/**
 * Created by lw on 2016/6/20.
 */
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ToastUtils
{
    public static boolean isShow = true;
    private static Toast mToast;

    public static void show(Context paramContext, int paramInt1, int paramInt2)
    {
        if (isShow)
            Toast.makeText(paramContext, paramInt1, paramInt2).show();
    }

    public static void show(Context paramContext, CharSequence paramCharSequence, int paramInt)
    {
        if (isShow)
            Toast.makeText(paramContext, paramCharSequence, paramInt).show();
    }

    public static void showLong(Context paramContext, int paramInt)
    {
        if (isShow)
            Toast.makeText(paramContext, paramInt, 1).show();
    }

    public static void showLong(Context paramContext, CharSequence paramCharSequence)
    {
        if (isShow)
            Toast.makeText(paramContext, paramCharSequence, 1).show();
    }

    public static void showShort(Context paramContext, int paramInt)
    {
        if (isShow)
            Toast.makeText(paramContext, paramInt, 0).show();
    }

    public static void showShort(Context paramContext, CharSequence paramCharSequence)
    {
        if (isShow)
            Toast.makeText(paramContext, paramCharSequence, 0).show();
    }

    public static void showToast(Context paramContext, String paramString)
    {
        if ((TextUtils.isEmpty(paramString)) || (paramContext == null))
            return;
        Toast.makeText(paramContext, paramString, 0).show();
    }
}
package cn.lw.study.http;

import android.os.Handler;
import android.os.Looper;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.lw.study.utils.DateUtils;
import cn.lw.study.utils.L;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 只对 okhttp异步get，post方法做了封装，同步没写
 * Created by luow on 2016/10/21.
 */
public class OkhttpUtils {
    private static OkHttpClient mOkHttpClient;

    private static OkhttpUtils mOkhttpUtils;

    private static Handler mHandler;

    private static Gson gson;

    public OkhttpUtils() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS);
        mOkHttpClient = builder.build();

        mOkHttpClient = new OkHttpClient();

        gson = new Gson();

        mHandler = new Handler(Looper.getMainLooper());

    }

    /**
     * 单列模式 OkhttpUtils实例化
     *
     * @return
     */
    private static OkhttpUtils getInstance() {
        if (mOkhttpUtils == null) {
            mOkhttpUtils = new OkhttpUtils();
        }
        return mOkhttpUtils;
    }

    //****************************对外暴露的方法****************************

    public static void get(String url, DataCallBack dataCallBack, Object tag) {
        getInstance().getAsyn(url, dataCallBack, tag);
    }

    public static void post(String url, Map<String, String> params, DataCallBack callBack, Object tag) {
        getInstance().postAsyn(url, params, callBack, tag);
    }

    public static void downLoadFile(String url, String destDir, DataCallBack callBack, Object tag) {
    //    getInstance().downLoadAsyn(url, destDir, callBack, tag);
    }


    //****************************内部调用的方法****************************

    /**
     * 异步get请求
     *
     * @param url
     */
    private static void getAsyn(String url, final DataCallBack callBack, Object tag) {
        L.e("----------网络请求开始[ 时间:" + DateUtils.getCurrentDate()
                + " 标识:" + tag.toString() + " ]----------");
        Request request = buildRequest(url, null, HttpMethodType.GET);
        doRequest(request, callBack, tag);
    }

    /**
     * 异步post请求
     *
     * @param url
     * @param params
     * @param callBack
     */
    private static void postAsyn(String url, Map<String, String> params, final DataCallBack callBack, Object tag) {
        L.e("----------网络请求开始[ 时间:" + DateUtils.getCurrentDate()
                + " 标识:" + tag.toString() + " ]----------");
        Request request = buildRequest(url, params, HttpMethodType.POST);

        doRequest(request, callBack, tag);
    }

    /**
     * 文件下载
     *
     * @param url
     * @param destDir
     * @param callBack
     * @param tag
     */
    private void downLoadAsyn(String url, String destDir, final DataCallBack callBack, final Object tag) {
        L.e("----------网络请求开始[ 时间:" + DateUtils.getCurrentDate()
                + " 标识:" + tag.toString() + " ]----------");
        L.i("---1.请求url地址---");
        L.d(url);
        Request request = new Request.Builder().url(url).build();
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                deliverDataError(null, e, callBack, tag);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }

    /**
     * 请求回调
     *
     * @param request
     * @param callBack
     */
    private static void doRequest(Request request, final DataCallBack callBack, final Object tag) {
        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                L.i("---4.请求出错,出错信息如下---");
                L.d(e + "");
                deliverDataError(null, e, callBack, tag);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    // callBack.type 传的类型
                    if (callBack.type == String.class) {
                        LogSu(result);
                        deliverDataSuccess(response, result, callBack, tag);
                    } else {
                        try {
                            LogSu(result + "");
                            Object object = gson.fromJson(result, callBack.type);
                            deliverDataSuccess(response, object, callBack, tag);
                        } catch (JsonParseException e) {
                            LogEr(e);
                            deliverDataError(response, e, callBack, tag);
                        }
                    }

                } else {
                    LogErCode(response.code(), response.message());
                    deliverDataError(response, null, callBack, tag);
                }
            }
        });
    }


    /**
     * 设置 Request
     *
     * @param url
     * @param params
     * @param type
     * @return
     */
    private static Request buildRequest(String url, Map<String, String> params, HttpMethodType type) {
        L.i("---1.请求url地址---");
        L.d(url);
        Request.Builder request = new Request.Builder();

        request.url(url);

        if (type == HttpMethodType.GET) {

            request.get();

        } else if (type == HttpMethodType.POST) {
            L.i("---2.请求参数---");
            if (params != null) {
                L.d(params.toString());
            }

            RequestBody requestBody = requestBodyParams(params, url);
            request.post(requestBody);

        }

        return request.build();
    }

    /**
     * 参数
     *
     * @param params
     * @return
     */
    private static RequestBody requestBodyParams(Map<String, String> params, String url) {
        FormBody.Builder formBody = new FormBody.Builder();

        StringBuffer requestStr = new StringBuffer();
        requestStr.append(url);
        requestStr.append("?");

        if (params != null) {

            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = null;
                if (entry.getValue() == null) {
                    value = "";
                } else {
                    value = entry.getValue();
                }
                formBody.add(key, value);
            }

            Object[] ks = params.keySet().toArray();
            for (int i = 0; i < ks.length; i++) {
                requestStr.append(ks[i].toString() + "=" + params.get(ks[i].toString()));
                if (i != (params.size() - 1)) {
                    requestStr.append("&");
                }
            }
            L.i("---3.请求详情---");
            L.d(requestStr.toString());
        }

        return formBody.build();
    }

    //****************************数据分发方法***************************************
    private static void deliverDataSuccess(final Response response, final Object result, final DataCallBack callBack, Object tag) {
        L.e("----------网络请求结束[ 时间:" + DateUtils.getCurrentDate() + " 标识:"
                + tag.toString() + " ]----------");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onSuccessResponse(response, result);
            }
        });
    }

    private static void deliverDataError(final Response response, final Exception e, final DataCallBack callBack, Object tag) {
        L.e("----------网络请求结束[ 时间:" + DateUtils.getCurrentDate() + " 标识:"
                + tag.toString() + " ]----------");
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                callBack.onErrorResponse(response, e);
            }
        });
    }

    private enum HttpMethodType {
        GET,
        POST
    }

    /**
     * 根据文件URL地址获取文件的路径文件名
     *
     * @param url
     * @return
     */
    private String getFilName(String url) {
        int separatorIndex = url.lastIndexOf("/");
        String path = (separatorIndex < 0) ? url : url.substring(separatorIndex + 1, url.length());
        return path;
    }


    private static void LogEr(JsonParseException e) {
        L.i("---4.请求出错,出错信息如下---");
        L.d(e + "");
    }

    private static void LogSu(String result) {
        L.i("---4.请求成功,服务器返回数据如下---");
        L.d(result);
    }

    private static void LogErCode(int code, String message) {
        L.i("---4.请求出错,出错信息如下---");
        L.d(code + "----" + message);
    }
}
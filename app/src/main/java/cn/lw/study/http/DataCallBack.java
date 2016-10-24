package cn.lw.study.http;

import com.google.gson.internal.$Gson$Types;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by luow on 2016/10/21.
 */
public abstract class DataCallBack<T> {
    public Type type;

    /**
     * 这个方法是将泛型<T> 转为 你传的类型
     * @param subClass
     * @return
     */
    static Type getSuperClassTypeParameter(Class<?> subClass) {
        Type superClass = subClass.getGenericSuperclass();
        if (superClass instanceof Class) {
            throw new RuntimeException("Missing type parameter");
        }
        ParameterizedType parameterized = (ParameterizedType) superClass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }

    public DataCallBack() {
        type = getSuperClassTypeParameter(getClass());
    }

    protected abstract void onSuccessResponse(Response response, T result);

    protected abstract void onErrorResponse(Response response,Exception e);
}

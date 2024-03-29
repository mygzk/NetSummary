package com.org.rxsimple.net.fast;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * create by guozhk on 2019/3/19 09:42
 **/
public class FastJsonConverterFactory  extends Converter.Factory{
    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

   /* *//**
     * 需要重写父类中responseBodyConverter，该方法用来转换服务器返回数据
     *//*
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseBodyConverter<>(type);
    }

    *//**
     * 需要重写父类中responseBodyConverter，该方法用来转换发送给服务器的数据
     *//*
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConverter<>();
    }*/


    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
      //  return super.responseBodyConverter(type, annotations, retrofit);
        return new FastJsonResponseBodyConverter<>(type);
    }


    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
      //  return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        return new FastJsonRequestBodyConverter<>();

    }
}

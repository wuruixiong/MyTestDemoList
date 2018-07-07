package com.wrx.mytest;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2017/5/5.
 */

public class RetrofitAndRxJavaSampleActivity extends Activity{

    private TextView mConTv;
    private Dialog mWaitDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_ret_sample);
        mConTv = (TextView) findViewById(R.id.rr_content_text);

        findViewById(R.id.rxs_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rxTest();
            }
        });
        findViewById(R.id.ret_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reTest();
            }
        });
        findViewById(R.id.ret_rx_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rerxTest();
            }
        });
    }

    private void rxTest() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
            }
            @Override
            public void onNext(String s) {
                mConTv.append(s);
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onComplete() {
            }
        };
        //使用Observable.create()创建被观察者
        Observable observable = Observable.create(new ObservableOnSubscribe() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("Wrold");
                emitter.onComplete();
            }
        });
        //订阅
        observable.subscribe(observer);
    }

    // 调用方法
    private void reTest () {
        // 异步调用
        Call<UserBean> asyncCall = getUserService.getUser("123456");
        showWait();
        asyncCall.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                // 这个回调产生在主线程
                UserBean userBean = response.body();
                Log.e("RetrofitTest", "async:" + userBean.getId() + "");
                mConTv.setText("Name:" + userBean.getLogin());
                hideWait();
            }
            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
            }
        });

        // 同步调用，因为不允许在主线程进行网络请求，所以只能在子线程里同步调用
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Call<UserBean> syncCall = getUserService.getUser("654321");
                    UserBean userBean = syncCall.execute().body();
                    Log.e("RetrofitTest", "sync:" +userBean.getId() + "");
                } catch (Exception e) {
                }
            }
        }).start();
    }

    private void rerxTest() {
        Observable<UserBean> observable = getUserSerObservable.getUser("654321");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<UserBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        showWait();
                    }
                    @Override
                    public void onNext(UserBean jsonStatus) {
                        if (jsonStatus != null) {
                            mConTv.setText("Name:" + jsonStatus.getLogin());
                        } else {
                            mConTv.setText("网络请求失败");
                        }
                    }
                    @Override
                    public void onError(Throwable throwable) {
                    }
                    @Override
                    public void onComplete() {
                        hideWait();
                    }
                });
    }


    public interface GetUserService {
        @GET("users/{user}")
        Call<UserBean> getUser(@Path("user") String user);
    }
    public interface GetUserSerObservable {
        @GET("users/{user}")
        Observable<UserBean> getUser(@Path("user") String user);
    }
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/") // 定义访问的主机地址
            .addConverterFactory(GsonConverterFactory.create())  //解析方法
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
    GetUserService getUserService = retrofit.create(GetUserService.class);
    GetUserSerObservable getUserSerObservable = retrofit.create(GetUserSerObservable.class);
    class UserBean {
        private String login;
        private int id;
        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    private void showWait() {
        if (mWaitDialog == null) {
            mWaitDialog = new ProgressDialog(this);
        }
        if (!mWaitDialog.isShowing()) {
            mWaitDialog.show();
        }
    }
    private void hideWait() {
        if (mWaitDialog != null && mWaitDialog.isShowing()) {
            mWaitDialog.dismiss();
        }
    }

}

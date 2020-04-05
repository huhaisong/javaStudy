package com.example.rxjavastudy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Network;
import android.os.Bundle;
import android.util.Log;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test8();
    }

    private void test8() {

    }


    //fromArray 创建被观察者
    private void test7() {
        ArrayList<String> list = new ArrayList<>();
        list.add("Hello");
        list.add("Hi");
        list.add("Aloha");
        String[] strings = {"Hello", "Hi", "Aloha"};
        Observable observable = Observable.fromArray(strings);
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //just 创建被观察者
    private void test6() {
        Observable observable = Observable.just("Hello", "Hi", "Aloha");
        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //必须两个observable 都触发了onnext之后（不分先后）才会执行BiFunction；
    private void test5() {
        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.e(TAG, "subscribe: observable1" );
                emitter.onNext("string");
            }
        });

        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.e(TAG, "subscribe: observable2" );
                Thread.sleep(3000);
                emitter.onNext(1);
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<String, Integer, String>() {
            @Override
            public String apply(@NonNull String mobileAddress, @NonNull Integer categoryResult) throws Exception {
                return "合并后的数据为：string:"+mobileAddress+"integer："+categoryResult;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "accept: 成功：" + s+"\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: 失败：" + throwable+"\n");
                    }
                });
    }

    //观察者
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onError(Throwable e) {
            Log.e("test", "onError");
        }

        @Override
        public void onComplete() {
            Log.e("test", "onComplete");
        }

        @Override
        public void onSubscribe(@NonNull Disposable d) {
            Log.e("test", "onSubscribe");
        }

        @Override
        public void onNext(String integer) {
            Log.e("test", "onNext----->" + integer);
        }
    };

    Subscriber<Integer> subscriber = new Subscriber<Integer>() {
        @Override
        public void onSubscribe(Subscription s) {

        }

        @Override
        public void onNext(Integer integer) {

        }

        @Override
        public void onError(Throwable t) {

        }

        @Override
        public void onComplete() {

        }
    };

    private static final String TAG = "MainActivity";

    //一般我们的retrofit都会直接得到创建好的observable；  rxjava 基本用法
    public void test() {
        Observable.create(new ObservableOnSubscribe<String>() { // 第一步：初始化Observable
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.e(TAG, "Current thread is " + Thread.currentThread().getName());
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1+"");
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2+"");
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3+"");
                e.onComplete();
                Log.e(TAG, "Observable emit 4" + "\n");
                e.onNext(4+"");
            }
        }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    //map可以先对返回的数据进行处理再返回； map用法
    public void test2() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("啊啊啊");
            }
        }).map(new Function<String, String>() {//第一个是接受的类型，第二个是返回的类型
            @Override
            public String apply(String s) throws Exception {
                return "这是经过处理过后的内容：" + s;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        Log.e(TAG, "doOnNext: 保存成功：" + s + Thread.currentThread().getName() + "\n");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String data) throws Exception {
                        Log.e(TAG, "成功:" + data + Thread.currentThread().getName() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: " + throwable.getMessage());
                    }
                });
    }

    //concat只有第一个被观察者发送complete才会执行第二个被观察者 concat用法
    public void test3() {
        Observable<String> cache = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                Log.e(TAG, "create当前线程:" + Thread.currentThread().getName());
                // 在操作符 concat 中，只有调用 onComplete 之后才会执行下一个 Observable
                if (false) { // 如果缓存数据不为空，则直接读取缓存数据，而不读取网络数据
                    Log.e(TAG, "\nsubscribe: 读取缓存数据:\n");
                    e.onNext("\nsubscribe: 缓存数据:\n");
                } else {
                    Log.e(TAG, "\nsubscribe: 读取网络数据:");
                    e.onComplete();
                }
            }
        });

        Observable<String> network = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("\n网络数据:\n");
            }
        });
        // 两个 Observable 的泛型应当保持一致
        Observable.concat(cache, network)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String tngouBeen) throws Exception {
                        Log.e(TAG, "subscribe 成功:" + Thread.currentThread().getName());
                        Log.e(TAG, "accept: 读取数据成功:" + tngouBeen);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 失败:" + Thread.currentThread().getName());
                        Log.e(TAG, "accept: 读取数据失败：" + throwable.getMessage());
                    }
                });
    }

    //map 必须是在onNext的时候才触发它的Function，function 返回Observable并继续执行subscribe内容；flatmap用法
    public void test4() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.e(TAG, "subscribe: " + Thread.currentThread().getName());
                Thread.currentThread().sleep(3000);
                emitter.onNext("网络json1");
                Throwable throwable = new Throwable("网络出错");
                emitter.onComplete();
//                emitter.onError(throwable);
            }
        })// 发起网络请求
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取食品列表的请求结果
                .observeOn(Schedulers.io()) // 回到 io 线程去处理获取食品详情的请求
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(@NonNull String foodList) throws Exception {
                        Log.e(TAG, "apply1: " + foodList);
                        return Observable.create(new ObservableOnSubscribe<String>() {
                            @Override
                            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                                Log.e(TAG, "subscribe: " + Thread.currentThread().getName());
                                emitter.onNext("网络json2");
                            }
                        });
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String foodDetail) throws Exception {
                        Log.e(TAG, "accept: success ：" + foodDetail.toString());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: error :" + throwable.getMessage());
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        Log.e(TAG, "complete");
                    }
                });
    }
}

package com.org.rxsimple;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by guozhk on 2018/4/20.
 */

public class TestClass {
    public static void main(String[] agrs) {
        TestClass testClass = new TestClass();
        testClass.testDefer();
    }


    /**
     * 简单应用
     */
    private void test() {

        Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                long id = Thread.currentThread().getId();
                printer("=observable===subscribe=0=id:" + id);
                printer("=observable===subscribe=0=");
                emitter.onNext(0);
                printer("=observable===subscribe=1=");
                emitter.onNext(1);
                printer("=observable===subscribe=2=");
                emitter.onNext(2);
                printer("=observable===subscribe=3=");
                emitter.onComplete();
                printer("=observable===subscribe=4=");
                emitter.onNext(4);


            }
        });


        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                long id = Thread.currentThread().getId();
                printer("=observer===onSubscribe==id:" + id);
            }

            @Override
            public void onNext(Integer integer) {
                long id = Thread.currentThread().getId();
                printer("=observer===onNext==id:" + id);
                printer("=observer===onNext==" + integer);
            }

            @Override
            public void onError(Throwable e) {
                long id = Thread.currentThread().getId();
                printer("=observer===onError==id:" + id);
                printer("=observer===onError==" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                long id = Thread.currentThread().getId();
                printer("=observer===onComplete==id:" + id);
                printer("=observer===onComplete==");
            }
        };


        observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .subscribe(observer);

//        observable.subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//
//            }
//        });

        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        });


        observable.subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });


    }

    /**
     * map 操作符
     * 变换操作符
     */
    private void testMap() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onNext(0);
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onNext(4);

            }
        }).map(new Function<Integer, String>() {

            @Override
            public String apply(Integer integer) throws Exception {
                return "s-" + integer;
            }
        }).subscribe(new Observer<String>() {


            public void onSubscribe(Disposable d) {
                printer("=observer===onSubscribe==id:");
            }

            @Override
            public void onNext(String integer) {
                printer("=observer===onNext==" + integer);
            }

            @Override
            public void onError(Throwable e) {
                printer("=observer===onError==" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                printer("=observer===onComplete==");
            }


        });

    }


    /**
     * flatmap 操作符
     * 变换操作符
     * 一个发送事件的上游Observable变换为多个发送事件的Observables，然后将它们发射的事件合并后放进一个单独的Observable里.
     * <p>
     * flatmap 无序
     * concatMap 有序
     */
    private void testFlatMap() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("0");
                emitter.onNext("1");

                //  emitter.onComplete();


            }
        }).flatMap(new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value :" + s);
                }

                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Observer<String>() {

            public void onSubscribe(Disposable d) {
                printer("=observer===onSubscribe==id:");
            }

            @Override
            public void onNext(String integer) {
                printer("=observer===onNext==" + integer);
            }

            @Override
            public void onError(Throwable e) {
                printer("=observer===onError==" + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                printer("=observer===onComplete==");
            }

        });


//                .subscribe(new Consumer<String>() {
//
//            @Override
//            public void accept(String s) throws Exception {
//                printer("=observer===onNext==" + s);
//            }
//        });
    }


    private void testFlatMap1() {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
            }
        }).concatMap(new Function<Integer, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(Integer integer) throws Exception {
                final List<String> list = new ArrayList<>();
                for (int i = 0; i < 3; i++) {
                    list.add("I am value " + integer);
                }
                return Observable.fromIterable(list).delay(10, TimeUnit.MILLISECONDS);
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                printer(s);
            }
        });

    }


    /**
     * @param
     */

    private void testDefer() {
//        Observable.defer(new Callable<ObservableSource<String>>() {
//            @Override
//            public ObservableSource<String> call() throws Exception {
//                String[] s= {"adad","a123"};
//                return Observable.fromArray(s);
//            }
//        }).subscribe(new Consumer<String>() {
//            @Override
//            public void accept(String s) throws Exception {
//                printer(s);
//            }
//        });


        Integer[] items = {1, 2, 3, 4, 5};
        Integer[] items1 = {1, 2, 3, 4, 5};
        Integer[] items2 = {1, 2, 3, 4, 5};
        Integer[] items3 = {1, 2, 3, 4, 5};
        Integer[] items4 = {1, 2, 3, 4, 5};

//        String s="s";
//        Observable.fromArray(items).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer ints) throws Exception {
//
////                for (Integer i:ints){
////                    printer(i+"");
////                }
//                printer(ints+"");
//            }
//        });

//        Observable.just(s,items).subscribe(new Consumer<Serializable>() {
//            @Override
//            public void accept(Serializable serializable) throws Exception {
//
//            }
//        });

//        Observable.just(items).subscribe(new Consumer<int[]>() {
//            @Override
//            public void accept(int[] ints) throws Exception {
//
//                for (int i:ints){
//                    printer(i+"");
//                }
//
//            }
//        });


//        Observable.just(items).map(new Function<Integer[], String[]>() {
//
//            @Override
//            public String[] apply(Integer[] integers) throws Exception {
//                String[] strings ={"s","d","f"};
//                return strings;
//            }
//        }).subscribe(new Consumer<String[]>() {
//
//            @Override
//            public void accept(String[] strings) throws Exception {
//                for (String i:strings){
//                    printer(i+"");
//                }
//            }
//        });


//        Observable.timer(5, TimeUnit.SECONDS).subscribe(new Observer<Long>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                printer("onSubscribe");
//            }
//
//            @Override
//            public void onNext(Long aLong) {
//                printer("onNext");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                printer("onError");
//            }
//
//            @Override
//            public void onComplete() {
//                printer("onComplete");
//            }
//
//
//        });
//

        Observable.timer(3,TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                printer("onComplete");
            }
        });




//        Observable.range(2,5).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                printer("integer:"+integer);
//            }
//        });


//        Observable.just(items,items2).elementAt(0).subscribe(new Observer<>());






//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.newThread()) // timer 默认在新线程，所以需要切换回主线程
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(@NonNull Long aLong) throws Exception {
//                        printer("integer:"+aLong);
//                    }
//                });

        Observable.just(1,2,3,4,5,6)
                //.buffer(3,2)
                .flatMap(new Function<Integer, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(Integer integer) throws Exception {
                        return Observable.just(integer+"---");

                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just(s+"++++++");
                    }
                });
//                .map(new Function<String, Object>() {
//                })
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) throws Exception {
//                        printer("integer:"+s);
//                    }
//                });


    }

    public static void printer(String str) {
        System.out.println(str);

    }


}

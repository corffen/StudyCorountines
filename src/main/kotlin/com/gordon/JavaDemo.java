package com.gordon;

import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import org.jetbrains.annotations.NotNull;

public class JavaDemo {

    public static void main(String[] args) throws InterruptedException {
        BuildersKt.launch(GlobalScope.INSTANCE, EmptyCoroutineContext.INSTANCE,
                CoroutineStart.DEFAULT,
                new Function2<CoroutineScope, Continuation<? super Unit>, Object>() {
                    @Override
                    public Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        Chapter2SuspendKt.getUserInfo(new Continuation<String>() {
                            @NotNull
                            @Override
                            public CoroutineContext getContext() {
                                return EmptyCoroutineContext.INSTANCE;
                            }

                            @Override
                            public void resumeWith(@NotNull Object o) {
                                System.out.println(o);
                            }
                        });
                        return null;
                    }
                });

        Thread.sleep(2000);

    }
}

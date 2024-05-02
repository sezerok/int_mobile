package ru.mirea.kharakhorinvd.mireaproject;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class TaskSevice extends Worker {
    public TaskSevice(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        int i = 100000000;
        while (i>0){
            i-=7;
        }
//будем считать что происходит жоская фоновая задача))))))))

        return Result.success();
    }
}

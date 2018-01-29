
package com.example.android.sunshine.sync;
// DONE (9) Create a class called SunshineSyncUtils

import android.content.Context;
import android.content.Intent;

//  DONE (10) Create a public static void method called startImmediateSync
    //  DONE (11) Within that method, start the SunshineSyncIntentService
    public class SunshineSyncUtils {
        public static void startImmediateSync(Context context) {
            Intent serviceIntent = new Intent(context, SunshineSyncIntentService.class);
            context.startService(serviceIntent);
        }
    }
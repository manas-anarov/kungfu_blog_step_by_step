package com.example.samuray.myapplication;



import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class InternetUtil {

    public static   boolean myInternetConnection(Context context){

        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mWifi.isConnected()) {
            return true;
        }
        return false;
    }

    public static boolean isOnline(Context context){


        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connManager.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else {
            return false;
        }

    }


    public static boolean isInternetOnline(Context context){


//        if ( InternetUtil.myInternetConnection(context) || InternetUtil.isOnline(context)){

        if (InternetUtil.isOnline(context)){

            return true;
        }else {
            Toast.makeText(context, "Check Internet", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}

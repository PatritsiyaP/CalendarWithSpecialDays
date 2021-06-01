package pesheva.patricia.com.projectx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;

import java.util.ArrayList;

import pesheva.patricia.com.projectx.R;

public class page1 extends AppCompatActivity {

    GridView gridview;
    private ArrayList<String> osNameList = new ArrayList<>();
    private ArrayList<String> osNameList_desc = new ArrayList<>();
    private ArrayList<String> osNameList_data = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

        page1.this.setTitle("СПИСЪК С ПРАЗНИЦИ");


        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

            for(int i=1;i<24;i++){
                String mtitle = databaseAccess.getitembyitemT(i);
                String mdesc = databaseAccess.getitembyitemD(i);
                String mdata = databaseAccess.getitembyitemDD(i);
                Log.d("ppp","adres "+mtitle);
                if(mtitle.equalsIgnoreCase("")){
                    Log.d("pp" +
                            "","cat empty ");


                }else{
                    Log.d("pp","cat full "+mtitle);
                    osNameList.add(mtitle);
                    osNameList_desc.add(mdesc);
                    osNameList_data.add(mdata);

                }
            }






        databaseAccess.close();

        gridview = (GridView) findViewById(R.id.page1_gridview);
        gridview.setAdapter(new page1_GridviewAdapter(this, osNameList, osNameList_desc));

//        // Create an explicit intent for an Activity in your app
//        Intent intent = new Intent(this, page3.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "12")
//                .setSmallIcon(R.drawable.ic_launcher_foreground)
//                .setContentTitle("My notification")
//                .setContentText("Hello World!")
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                // Set the intent that will fire when the user taps the notification
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true);


    }
}

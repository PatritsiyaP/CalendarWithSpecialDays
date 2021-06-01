package pesheva.patricia.com.projectx;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;


import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import pesheva.patricia.com.projectx.R;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
// test
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    Button btnDate ;
    final Calendar myCalendar = Calendar. getInstance () ;

    String[]  monts= {"Jan","Feb" ,"Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
    String[]  montsfull= {"Януари","Февруари" ,"Март","Април","Май","Юни","Юли","Август","Септември","Октомври","Ноември","Декември"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ///////////////////////////////////////////////
        final TextView tx1 = (TextView) findViewById(R.id.textView2);
        final TextView tx2 = (TextView) findViewById(R.id.textView3);
        tx2.setText("Моля изберете ден от календара!");

        Calendar rightNow = Calendar.getInstance();


        final CompactCalendarView compactCalendarView = (CompactCalendarView) findViewById(R.id.compactcalendar_view);
        compactCalendarView.setUseThreeLetterAbbreviation(true);

        // Set first day of week to Monday, defaults to Monday so calling setFirstDayOfWeek is not necessary
        // Use constants provided by Java Calendar class
        compactCalendarView.setFirstDayOfWeek(Calendar.MONDAY);
        String ym = String.valueOf(rightNow.getTime().getMonth());
        tx1.setText(""+montsfull[4]);

        Event[] myev =  new Event[24];

        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();

        for(int i=1;i<24;i++){
            String mtitle = databaseAccess.getitembyitemT(i);
            String mdesc = databaseAccess.getitembyitemD(i);
            String mdata = databaseAccess.getitembyitemDD(i);
            Log.d("YAS","adres "+mtitle);
            if(mtitle.equalsIgnoreCase("")){
                Log.d("YAS","cat empty ");


            }else{
                Log.d("YAS","cat full "+mtitle);
                String[] separated = mdata.split("\\.");

                myev[i] = new Event(Color.GREEN, getLongAsDate(2021,Integer.parseInt(separated[1].replace(" ", "")),Integer.parseInt(separated[0].replace(" ", ""))), ""+mtitle+"\n\n"+mdesc);
                compactCalendarView.addEvent(myev[i] );

//                osNameList.add(mtitle);
//                osNameList_desc.add(mdesc);
//                osNameList_data.add(mdata);

            }
        }






        databaseAccess.close();



        // define a listener to receive callbacks when certain events happen.
        compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                List<Event> events = compactCalendarView.getEvents(dateClicked);
                Log.d("YAS", "Day was clicked: " + dateClicked + " with events " + events);
                if(events.size()==0){
                    tx2.setText("НЯМА ЗАДАДЕН ПРАЗНИК");
                }else {
                    tx2.setText(""+events.get(0).getData());
                    addNotification("ПРАЗНИЗИ",""+events.get(0).getData());
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                Log.d("YAS", "Month was scrolled to: " + firstDayOfNewMonth);
                for(int i=0;i<monts.length;i++){
                    if(firstDayOfNewMonth.toString().contains(monts[i])){
                        tx1.setText(montsfull[i]);
                    }
                }
            }
        });



    }


    private void addNotification(String title,String text) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setContentTitle(title)
                        .setContentText(text);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }


    public long getLongAsDate(int year, int month, int date) {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.YEAR, year);
        return calendar.getTimeInMillis();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_list) {
            // Handle the camera action
            Intent intent = new Intent(MainActivity.this, page1.class);
            startActivity(intent);
        } else if (id == R.id.nav_contact) {


            String  message = "Приложение за празници : "+"\nAPI level: "+Build.VERSION.SDK_INT ;

            String subject ="Отзиви от потребители на празници";


            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"patritsiya.pesheva54@gmail.com"});
            i.putExtra(Intent.EXTRA_SUBJECT, subject);
            i.putExtra(Intent.EXTRA_TEXT   , ""+"\n\n"+"_______________________\n"+message);
            try {
                startActivity(Intent.createChooser(i, "Send mail..."));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
            }


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void createNotificationChanel(){
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            CharSequence name= "mychanelid";
            String description = "chanel pp";
            int importance = NotificationManager.IMPORTANCE_DEFAULT ;
            NotificationChannel notificationChannel = new NotificationChannel( "notifyYpp" , name, importance) ;
            notificationChannel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);

        }
    }

    private void scheduleNotification (Notification notification , long delay) {
        Intent notificationIntent = new Intent( this, MyNotificationPublisher. class ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION_ID , 1 ) ;
        notificationIntent.putExtra(MyNotificationPublisher. NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent. getBroadcast ( this, 0 , notificationIntent , PendingIntent. FLAG_UPDATE_CURRENT ) ;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context. ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager. ELAPSED_REALTIME_WAKEUP , delay , pendingIntent) ;
    }
    private Notification getNotification (String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder( this, default_notification_channel_id ) ;
        builder.setContentTitle( "Scheduled Notification" ) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable. ic_launcher_foreground ) ;
        builder.setAutoCancel( true ) ;
        builder.setChannelId( NOTIFICATION_CHANNEL_ID ) ;
        return builder.build() ;
    }
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet (DatePicker view , int year , int monthOfYear , int dayOfMonth) {
            myCalendar .set(Calendar. YEAR , year) ;
            myCalendar .set(Calendar. MONTH , monthOfYear) ;
            myCalendar .set(Calendar. DAY_OF_MONTH , dayOfMonth) ;
            updateLabel() ;
        }
    } ;
    public void setDate (View view) {
        new DatePickerDialog(
                MainActivity. this, date ,
                myCalendar .get(Calendar. YEAR ) ,
                myCalendar .get(Calendar. MONTH ) ,
                myCalendar .get(Calendar. DAY_OF_MONTH )
        ).show() ;
    }
    private void updateLabel () {
        String myFormat = "dd/MM/yy" ; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat , Locale. getDefault ()) ;
        Date date = myCalendar .getTime() ;
        btnDate .setText(sdf.format(date)) ;
        scheduleNotification(getNotification( btnDate .getText().toString()) , date.getTime()) ;
        Toast.makeText(MainActivity.this,"time "+ date.getTime(),Toast.LENGTH_LONG).show();
        Log.d("pp","time "+ date.getTime());
    }
}

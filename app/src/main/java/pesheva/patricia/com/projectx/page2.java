package pesheva.patricia.com.projectx;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;


import pesheva.patricia.com.projectx.R;

public class page2  extends AppCompatActivity {
    //for banner


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);

        page2.this.setTitle("PAGE2");


        CalendarView simpleCalendarView = (CalendarView) findViewById(R.id.calendarView); // get the reference of CalendarView
        long selectedDate = simpleCalendarView.getDate(); // get selected date in milliseconds
        simpleCalendarView.setDate(1620248400000L);
        simpleCalendarView.setDate(1620766800000L);


    }



}

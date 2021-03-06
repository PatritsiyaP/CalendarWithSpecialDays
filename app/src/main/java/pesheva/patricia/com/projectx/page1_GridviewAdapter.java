package pesheva.patricia.com.projectx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pesheva.patricia.com.projectx.R;

public class page1_GridviewAdapter extends BaseAdapter {

    ArrayList<String> result;
    Context context;
    ArrayList<String> imageId;
    private static LayoutInflater inflater=null;
    public page1_GridviewAdapter(Context con, ArrayList<String> osNameList, ArrayList<String> osNameList_desc) {
        // TODO Auto-generated constructor stub
        result=osNameList;
        context=con;
        imageId=osNameList_desc;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    public class Holder
    {
        TextView os_text;
        TextView os_text_desc;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        Holder holder=new Holder();
        View rowView;

        rowView = inflater.inflate(R.layout.page1_gridviewitem, null);
        holder.os_text =(TextView) rowView.findViewById(R.id.os_texts);
        holder.os_text_desc =(TextView) rowView.findViewById(R.id.os_text_desc);

        holder.os_text.setText(result.get(position));
        holder.os_text_desc.setText(imageId.get(position));

        rowView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
              //  Toast.makeText(context, "You Clicked "+result.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        return rowView;
    }

}

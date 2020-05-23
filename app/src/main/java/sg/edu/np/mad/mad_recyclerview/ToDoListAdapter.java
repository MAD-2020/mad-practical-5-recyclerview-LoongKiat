package sg.edu.np.mad.mad_recyclerview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListViewHolder> {
    ArrayList<ToDoTask> data;
    Context context;
    TextView displayTask;

    public ToDoListAdapter(ArrayList<ToDoTask> input, Context context){
        this.context = context;
        data = input;
    }

    @Override
    public ToDoListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_to_do_task,parent,false);

        Log.d("test","onCreate");
        return new ToDoListViewHolder(item);

    }

    @Override
    public void onBindViewHolder(final ToDoListViewHolder holder, final int position){
        ToDoTask s = data.get(position);
        holder.txt.setText(s.task);

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                String task = holder.txt.getText().toString();
                View item = LayoutInflater.from(v.getContext()).inflate(R.layout.alert_dialog,null);
                builder.setView(item)
                        .setTitle("Delete")
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("test","clicked on YES");
                        data.remove(position);
                        notifyDataSetChanged();
                    }
                })
                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("test","clicked on NO");
                        dialog.dismiss();
                    }
                });
                displayTask = item.findViewById(R.id.textView2);
                displayTask.setText(task + "?");
                AlertDialog alert =  builder.create();
                alert.show();
            }
        });
        Log.d("test","onBind");
    }

    @Override
    public int getItemCount(){
        return data.size();
    }

}

package sg.edu.np.mad.mad_recyclerview;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToDoListViewHolder extends RecyclerView.ViewHolder {
    TextView txt;
    CheckBox cb;
    View v;

    public ToDoListViewHolder(@NonNull View itemView) {
        super(itemView);

        v = itemView;
        txt = itemView.findViewById(R.id.task);
        cb = itemView.findViewById(R.id.checkBox);
    }
}

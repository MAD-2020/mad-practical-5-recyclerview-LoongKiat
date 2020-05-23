package sg.edu.np.mad.mad_recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context = MainActivity.this;
    Button addBtn;
    EditText enteredTask;
    String newTask;
    RecyclerView rv;
    ArrayList<ToDoTask> data;
    ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.recyclerView);
        addBtn = findViewById(R.id.addButton);
        enteredTask = findViewById(R.id.editText);
        data = new ArrayList<>();
        ToDoTask task = new ToDoTask();
        task.task = "badminton";
        data.add(task);

        addBtn.setOnClickListener(buttonClick);

        adapter = new ToDoListAdapter(data,context);
        rv.setAdapter(adapter);

        LinearLayoutManager layout = new LinearLayoutManager(this);
        rv.setLayoutManager(layout);
        rv.setItemAnimator(new DefaultItemAnimator());
    }

    private View.OnClickListener buttonClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d("test","added!");
            newTask = enteredTask.getText().toString();
            showNewEntry(rv,data);
            addTask(data,newTask,adapter);
        }
    };

    private void addTask(ArrayList<ToDoTask> data, String s,ToDoListAdapter adapter){
        ToDoTask task = new ToDoTask();
        task.task = s;
        data.add(task);
        adapter.notifyDataSetChanged();
    }
    /**
     * Upon calling this method, the keyboard will retract
     * and the recyclerview will scroll to the last item
     *
     * @param rv RecyclerView for scrolling to
     * @param data ArrayList that was passed into RecyclerView
     */
    private void showNewEntry(RecyclerView rv, ArrayList data){
        //scroll to the last item of the recyclerview
        rv.scrollToPosition(data.size() - 1);

        //auto hide keyboard after entry
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(rv.getWindowToken(), 0);
    }
}

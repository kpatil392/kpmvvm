package com.example.mvvm_1.task;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvm_1.R;
import com.example.mvvm_1.databinding.KpActivityAddtaskBinding;

public class KpAddTaskActivity extends AppCompatActivity {
    KpActivityAddtaskBinding binding;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(KpAddTaskActivity.this, R.layout.kp_activity_addtask);
        binding.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        
    }

    private void save() {
      
        final String sTask =  binding.editTextTask.getText().toString().trim();
        final String sDesc =  binding.editTextDesc.getText().toString().trim();
        final String sFinishBy =  binding.editTextFinishBy.getText().toString().trim();


        if (sTask.isEmpty()) {
            binding.editTextTask.setError("Task required");
            binding.editTextTask.requestFocus();
            return;
        }
        if (sDesc.isEmpty()) {
            binding.editTextDesc.setError("Desc required");
            binding.editTextDesc.requestFocus();
            return;
        }

        if (sFinishBy.isEmpty()) {
            binding.editTextFinishBy.setError("Finish by required");
            binding.editTextFinishBy.requestFocus();
            return;
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(false);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insert(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), KpMainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }
}

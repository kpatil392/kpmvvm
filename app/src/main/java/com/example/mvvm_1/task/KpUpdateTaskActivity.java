package com.example.mvvm_1.task;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvm_1.R;
import com.example.mvvm_1.databinding.KpActivityUpdatetaskBinding;

public class KpUpdateTaskActivity extends AppCompatActivity {
    
    KpActivityUpdatetaskBinding binding;
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(KpUpdateTaskActivity.this, R.layout.kp_activity_updatetask);
        final Task task = (Task) getIntent().getSerializableExtra("task");

        loadTask(task);

        binding.buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(task);
            }
        });

        binding.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(KpUpdateTaskActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(task);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }

    private void loadTask(Task task) {
        binding.editTextTask.setText(task.getTask());
        binding.editTextDesc.setText(task.getDesc());
        binding.editTextFinishBy.setText(task.getFinishBy());
        binding.checkBoxFinished.setChecked(task.isFinished());
    }

    private void updateTask(final Task task) {
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
        

        class UpdateTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                task.setTask(sTask);
                task.setDesc(sDesc);
                task.setFinishBy(sFinishBy);
                task.setFinished(binding.checkBoxFinished.isChecked());
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .update(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(KpUpdateTaskActivity.this, KpMainActivity.class));
            }
        }

        UpdateTask ut = new UpdateTask();
        ut.execute();
    }


    private void deleteTask(final Task task) {
        class DeleteTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .delete(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(KpUpdateTaskActivity.this, KpMainActivity.class));
            }
        }

        DeleteTask dt = new DeleteTask();
        dt.execute();

    }

}
package com.example.mvvm_1.insertData;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.OnScrollListener;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Toast;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.mvvm_1.R;
import com.example.mvvm_1.databinding.ActivityMainBinding;
import com.google.gson.Gson;



import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static androidx.recyclerview.widget.RecyclerView.*;

public class MainActivity extends AppCompatActivity {
    boolean onScrolled = false;

    private int sPastVisiblesItems;
    private int sVisibleItemCount;
    private int sTotalItemCount;
    LinearLayoutManager sLayoutManager;




    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    LinearLayoutManager mLayoutManager;

    ActivityMainBinding binding;
    NoteViewModel viewModel;
    NotesAdapter noteAdapter;
    ArrayList<Example.Product> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        notes = new ArrayList<>();

        
        mLayoutManager = new LinearLayoutManager(this);
        binding.rvview.setLayoutManager(mLayoutManager);

      /*  sLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        binding.rvview.setLayoutManager(sLayoutManager);*/
        getData(0);
        binding.floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddNewNoteActivity.class);
                startActivityForResult(intent, Constant.NOTE_ReQUESTCODE);
            }
        });
        
     
       /* viewModel.getAllNotesDAta().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                binding.rvview.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                noteAdapter=new NotesAdapter(MainActivity.this,notes);
                binding.rvview.setAdapter(noteAdapter);
                noteAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, notes.get(0).getId()+"---->>"+notes.size(), Toast.LENGTH_SHORT).show();

            }
        });*/

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constant.NOTE_ReQUESTCODE && resultCode == RESULT_OK) {
            viewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
            Random random = new Random();
            final String id = String.valueOf(random.nextInt(1000));
            String note = data.getStringExtra(Constant.NOTENAME);
            Note note1 = new Note(id, note);
            viewModel.Insert(note1);
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(MainActivity.this, "not asaved", Toast.LENGTH_SHORT).show();

        }
    }

    void getData(int offset) {

        AndroidNetworking.post("https://geecomindia.in/index.php/api/v1/explore-supply-v2")
                .addHeaders("Authentication-control", "")
                .addBodyParameter("lang_code", "en")
                .addBodyParameter("user_id", "652317339961")
                .addBodyParameter("supply_type", "1")
                .addBodyParameter("pincode", "452001")
                .addBodyParameter("lat", String.valueOf(22.2121212))
                .addBodyParameter("lon", String.valueOf(72.24545))
                .addBodyParameter("product_offset", String.valueOf(offset))
                .setTag("getexploreSupply")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //Log.i("Resp", response.toString());
                        Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();

                        final Example example = new Gson().fromJson(String.valueOf(response), Example.class);
                        if (example.getStatus() == 200) {
                            Log.i("Resp",example.getProduct_offset()+"--");
                            notes.addAll(example.getProducts());

                          
                            noteAdapter = new NotesAdapter(MainActivity.this, notes);
                            binding.rvview.setAdapter(noteAdapter);
                            noteAdapter.notifyDataSetChanged();
                        }
                        binding.rvview.addOnScrollListener(new RecyclerView.OnScrollListener() {
                            @Override
                            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                if (dy > 0) { //check for scroll down
                                    visibleItemCount = mLayoutManager.getChildCount();
                                    totalItemCount = mLayoutManager.getItemCount();
                                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                                    if (loading) {
                                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                            loading = false;
                                            Log.v("...", "Last Item Wow !");
                                            // Do pagination.. i.e. fetch new data
                                        }
                                    }
                                }
                            }
                        });
                       /* binding.rvview.addOnScrollListener(new OnScrollListener() {
                            @Override
                            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                                super.onScrollStateChanged(recyclerView, newState);
                                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                                    onScrolled = true;
                                }
                            }

                            @Override
                            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                                super.onScrolled(recyclerView, dx, dy);

                                sVisibleItemCount = sLayoutManager.getChildCount();
                                sTotalItemCount = sLayoutManager.getItemCount();
                                sPastVisiblesItems = sLayoutManager.findFirstVisibleItemPosition();
                                if (onScrolled && (sVisibleItemCount + sPastVisiblesItems == sTotalItemCount)) {
                                    getData(example.getProduct_offset());
                                    onScrolled=false;
                                }
                            }
                        });*/
                    }

                    @Override
                    public void onError(ANError anError) {

                    }
                });
    }

}

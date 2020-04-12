package com.example.mvvm_1.insertData;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.mvvm_1.R;
import com.example.mvvm_1.databinding.AddnewNoteBinding;

public class AddNewNoteActivity extends AppCompatActivity {
    AddnewNoteBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(AddNewNoteActivity.this, R.layout.addnew_note);
        binding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                if(TextUtils.isEmpty(binding.edtNote.getText()))
                {
                    setResult(RESULT_CANCELED,intent);
                }else {
                    intent.putExtra(Constant.NOTENAME,binding.edtNote.getText().toString());
                    setResult(RESULT_OK,intent);
                }
                finish();
                         
            }
        });
    }
}

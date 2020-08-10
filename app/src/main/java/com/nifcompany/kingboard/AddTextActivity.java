package com.nifcompany.kingboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nifcompany.kingboard.database.DatabaseHelper;
import com.nifcompany.kingboard.json.post.PostText;

public class AddTextActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = database.getReference("message");
    private DatabaseHelper dbHelper;

    EditText edtJudul;
    EditText edtDeskripsi;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text);

        dbHelper = new DatabaseHelper(this.getApplicationContext(), "db_king_board");
        dbHelper.getWritableDatabase();
        Log.d("Create Database", "Database");


        edtJudul        = findViewById(R.id.EdtJudul);
        edtDeskripsi    = findViewById(R.id.EdtDeskripsi);

        btnSave         = findViewById(R.id.BtnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PostText postText = new PostText();
                String judul = edtJudul.getText().toString();
                String deskripsi    = edtDeskripsi.getText().toString();

                postText.setJudul(judul);
                postText.setText(deskripsi);

                databaseReference.push().setValue(postText);

                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();

                values.put("judul", judul);
                values.put("teks", deskripsi);

                db.insert("tbl_text", null, values);
                db.close();
            }
        });
    }
}
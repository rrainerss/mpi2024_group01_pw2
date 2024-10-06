package com.android.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button dialog_button = (Button) findViewById(R.id.dialog);
        dialog_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String[] choices = {
                        getString(R.string.name_surname_one),
                        getString(R.string.name_surname_two),
                        getString(R.string.name_surname_three) };

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.dialog_title)
                    .setPositiveButton(R.string.ok, null)
                    .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Toast.makeText(MainActivity.this, "You closed Dialog", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setMultiChoiceItems(choices, null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    Toast.makeText(MainActivity.this, "Checked " + choices[which], Toast.LENGTH_SHORT).show();
                                    //selectedItems.add(which);
                                }
                                else {
                                    Toast.makeText(MainActivity.this, "Unchecked " + choices[which], Toast.LENGTH_SHORT).show();
                                    //selectedItems.remove(which);
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "You clicked OK", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Button go_to_button = (Button) findViewById(R.id.go_to_second);
        go_to_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity2.class));
            }
        });
    }
}


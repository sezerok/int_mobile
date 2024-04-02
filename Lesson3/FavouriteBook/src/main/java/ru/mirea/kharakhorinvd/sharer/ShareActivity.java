package ru.mirea.kharakhorinvd.sharer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_activity);
        Bundle extras = getIntent().getExtras();
        TextView ageView = findViewById(R.id.textView2);
        String university = extras.getString(MainActivity.KEY);
        ageView.setText(String.format("Моя любимая книга: %s", university));
    }
    public void onClickSend(View view){
        Intent data = new Intent();
        EditText textEdit = findViewById(R.id.editTextText);
        String text = textEdit.getText().toString();
        data.putExtra(MainActivity.USER_MESSAGE, text);
        setResult(Activity.RESULT_OK, data);
        finish();
    }
}

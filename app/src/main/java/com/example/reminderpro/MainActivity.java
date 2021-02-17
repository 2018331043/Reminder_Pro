package com.example.reminderpro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TaskListAdapter.ResultListener {
    private ImageView addbutton;
    private Button addTask;
    private EditText task;
    private RecyclerView tasks;
    private ArrayList<Tasks> listofTasks=new ArrayList<Tasks>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addbutton=findViewById(R.id.addButton);
        tasks=findViewById(R.id.recyclerViewTasks);
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialogBuilder= new AlertDialog.Builder(MainActivity.this);
                View dialogforTaskAdd = getLayoutInflater().inflate(R.layout.taskwriterlayout,null);
                addTask= dialogforTaskAdd.findViewById(R.id.button);
                task=dialogforTaskAdd.findViewById(R.id.editTextTextPersonName);
                dialogBuilder.setView(dialogforTaskAdd);
                AlertDialog alertDialog= dialogBuilder.create();
                alertDialog.show();
                addTask.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Tasks newTask= new Tasks(task.getText().toString(),false);
                        listofTasks.add(newTask);
                        saveTaskData();
                    }
                });
            }
        });
        //Check how this works by playing with it
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        tasks.setLayoutManager(layoutManager);
        loadTaskData();

        TaskListAdapter adapter=new TaskListAdapter(MainActivity.this,listofTasks,MainActivity.this);
        tasks.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    @Override
    public void onResultClick (final int positon){
        Integer s = positon;

        //Intent intent = new Intent(this, ParentSearchResultProfileActivity.class);
        // startActivity(intent);
    }
    private void loadTaskData(){
        listofTasks.clear();
        SharedPreferences sharedPreferences =getSharedPreferences("TaskList",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sharedPreferences.getString("ListOfTheTasks",null);
        Type type=new TypeToken<ArrayList<Tasks>>() {}.getType();
        listofTasks=gson.fromJson(json,type);
        if(listofTasks==null){
            listofTasks=new ArrayList<>();
        }
    }
    private void saveTaskData(){
        SharedPreferences sharedPreferences =getSharedPreferences("TaskList",MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Gson gson=new Gson();
        String json=gson.toJson(listofTasks);
        editor.putString("ListOfTheTasks",json);
        editor.apply();
    }

}
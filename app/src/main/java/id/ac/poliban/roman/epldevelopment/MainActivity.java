package id.ac.poliban.roman.epldevelopment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import id.ac.poliban.roman.epldevelopment.adapter.ClubAdapter;
import id.ac.poliban.roman.epldevelopment.dao.impl.ClubDaoImplSQLite;
import id.ac.poliban.roman.epldevelopment.domain.Club;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(getSupportActionBar()!=null){
            getSupportActionBar().setTitle("EPL Development");
            getSupportActionBar().setSubtitle("roman studios");
        }

        findViewById(R.id.fab).setOnClickListener(v -> createNewClub());
    }

    private void createNewClub() {
        startActivity(new Intent(this, DetailActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()){
            case R.id.mi_action_row :
                //
                break;
            case R.id.mi_action_card :
                //
                break;
            case R.id.mi_action_grid :
                //
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ClubDaoImplSQLite db = new ClubDaoImplSQLite(this);
        List<Club> data = new ArrayList<>(db.getAllClub());
        ClubAdapter adapter = new ClubAdapter(data);
        RecyclerView rvClub = findViewById(R.id.rv_club);
        rvClub.setLayoutManager(new LinearLayoutManager(this));
        rvClub.setAdapter(adapter);
    }
}

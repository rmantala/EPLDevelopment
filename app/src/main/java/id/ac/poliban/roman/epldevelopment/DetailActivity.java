package id.ac.poliban.roman.epldevelopment;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.ac.poliban.roman.epldevelopment.dao.impl.ClubDaoImplSQLite;
import id.ac.poliban.roman.epldevelopment.domain.Club;

public class DetailActivity extends AppCompatActivity {
    private EditText etID, etClubName, etFounded, etGrounded, etManager, etDescription, etLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        etID = findViewById(R.id.et_id);
        etClubName = findViewById(R.id.et_club_name);
        etFounded = findViewById(R.id.et_founded);
        etGrounded = findViewById(R.id.et_grounded);
        etManager = findViewById(R.id.et_manager);
        etDescription = findViewById(R.id.et_description);
        etLogo = findViewById(R.id.et_logo);


        if(getSupportActionBar()!=null){
            if(getIntent().getExtras()==null){
                getSupportActionBar().setTitle("New Club...");
                getSupportActionBar().setSubtitle("roman studios");
                clearForm();
            }
            else{
                getSupportActionBar().setTitle("Updating Club....");
                getSupportActionBar().setSubtitle("roman studios");
                populateForm();
            }
        }


        findViewById(R.id.bt_save).setOnClickListener(v -> saveAction());
    }

    private void populateForm() {
        Club club = (Club) getIntent().getSerializableExtra("club");

        Glide.with(this)
                .load(club.getUrlLogo())
                .apply(new RequestOptions().override(180, 180))
                .into((ImageView)findViewById(R.id.civ_logo));

        etID.setText(String.valueOf(club.getId()));
        etClubName.setText(club.getClubName());
        etFounded.setText(club.getFounded());
        etGrounded.setText(club.getGrounded());
        etManager.setText(club.getManager());
        etDescription.setText(club.getDescription());
        etLogo.setText(club.getUrlLogo());

        etID.setEnabled(false);
    }

    private void clearForm() {
        //sembunyikan image logo
        ((ImageView)findViewById(R.id.civ_logo)).setVisibility(View.GONE);

        etID.setText("");
        etClubName.setText("");
        etFounded.setText("");
        etGrounded.setText("");
        etManager.setText("");
        etDescription.setText("");
        etLogo.setText("");

        etID.setEnabled(false);
        etClubName.requestFocus();
    }

    private void saveAction() {
        if(etClubName.getText().toString().isEmpty() || etDescription.getText().toString().isEmpty()){
            Toast.makeText(this, "club name & description cannot null!", Toast.LENGTH_SHORT).show();
            return;
        }

        ClubDaoImplSQLite db = new ClubDaoImplSQLite(this);

        if(getIntent().getExtras()==null) {
            //simpan data club baru
            db.insert(new Club(
                    etClubName.getText().toString().trim(),
                    etFounded.getText().toString().trim(),
                    etGrounded.getText().toString().trim(),
                    etManager.getText().toString().trim(),
                    etDescription.getText().toString().trim(),
                    etLogo.getText().toString().trim()
            ));
        }
        else{
            //ubah data club
            db.update(new Club(
                    Integer.parseInt(etID.getText().toString().trim()),
                    etClubName.getText().toString().trim(),
                    etFounded.getText().toString().trim(),
                    etGrounded.getText().toString().trim(),
                    etManager.getText().toString().trim(),
                    etDescription.getText().toString().trim(),
                    etLogo.getText().toString().trim()
            ));
        }

        db.close();
        finish();
    }
}

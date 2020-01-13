package id.ac.poliban.roman.epldevelopment.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ac.poliban.roman.epldevelopment.DetailActivity;
import id.ac.poliban.roman.epldevelopment.R;
import id.ac.poliban.roman.epldevelopment.dao.impl.ClubDaoImplSQLite;
import id.ac.poliban.roman.epldevelopment.domain.Club;

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.ViewHolder> {
    private List<Club> data;
    private ClubDaoImplSQLite db;

    public ClubAdapter(List<Club> data) {
        this.data = data;
    }

    @NonNull
    @Override
    //create view penampung
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_row_club, parent, false);
        return new ViewHolder(view);
    }

    //binding view with data
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Club club = data.get(position);

        Glide.with(holder.itemView.getContext())
                .load(club.getUrlLogo())
                .apply(new RequestOptions().override(70, 70))
                .into(holder.civLogo);
        holder.tvClubName.setText(club.getClubName());
        holder.tvDescription.setText(club.getDescription());

        holder.itemView.setOnClickListener(v -> {
            //buat intent ke DetailActivity.class
            Intent intent = new Intent(holder.itemView.getContext(), DetailActivity.class);
            //kirim club object melalui intent
            intent.putExtra("club", club);
            //start activity
            holder.itemView.getContext().startActivity(intent);
        });

        holder.itemView.setOnLongClickListener(v -> {
            new AlertDialog.Builder(holder.itemView.getContext())
                    .setTitle("Delete confirmation")
                    .setMessage("hapus " + club.getClubName() + " ?")
                    .setPositiveButton("YES", (dialog, which) -> {
                        db = new ClubDaoImplSQLite(holder.itemView.getContext());
                        db.delete(club.getId());
                        data.remove(position);
                        notifyDataSetChanged();
                        db.close();
                    })
                    .setNegativeButton("NO", null)
                    .show();

            return true;
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //definisikan setiap view pada item list dari RecyclerView
    class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView civLogo;
        TextView tvClubName, tvDescription;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            civLogo = itemView.findViewById(R.id.civ_logo);
            tvClubName = itemView.findViewById(R.id.tv_club_name);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }
}

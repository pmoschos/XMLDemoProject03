package gr.aueb.cf.xmldemoproject03.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import gr.aueb.cf.xmldemoproject03.R;
import gr.aueb.cf.xmldemoproject03.models.Travelerinformation;
import gr.aueb.cf.xmldemoproject03.models.Travelers;

public class TravelerAdapter extends RecyclerView.Adapter<TravelerAdapter.TravelerViewHolder>{

    private List<Travelerinformation> travelerinformationList;
    private Context context;

    public TravelerAdapter(List<Travelerinformation> travelerinformationList, Context context) {
        this.travelerinformationList = travelerinformationList;
        this.context = context;
    }

    @NonNull
    @Override
    public TravelerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.traveler_card, parent, false);
        return new TravelerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TravelerViewHolder holder, int position) {
        Travelerinformation travelerinformation = travelerinformationList.get(position);
        holder.tv_id.setText(travelerinformation.getId());
        holder.tv_name.setText(travelerinformation.getName());
        holder.tv_email.setText(travelerinformation.getEmail());
        holder.tv_address.setText(travelerinformation.getAddress());
        holder.tv_createData.setText(travelerinformation.getCreatedat());
    }

    @Override
    public int getItemCount() {
        return travelerinformationList.size();
    }

    public class TravelerViewHolder extends RecyclerView.ViewHolder {

        private MaterialTextView tv_id;
        private MaterialTextView tv_name;
        private MaterialTextView tv_email;
        private MaterialTextView tv_address;
        private MaterialTextView tv_createData;

        public TravelerViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_id = itemView.findViewById(R.id.tv_id);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_address = itemView.findViewById(R.id.tv_address);
            tv_createData = itemView.findViewById(R.id.tv_createData);

        }
    }
}

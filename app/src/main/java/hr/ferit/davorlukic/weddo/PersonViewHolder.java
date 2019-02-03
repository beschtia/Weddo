package hr.ferit.davorlukic.weddo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView tvName;
    private ImageView imRemove;
    private PersonClickInterface clickListener;

    public PersonViewHolder(@NonNull View itemView, PersonClickInterface listener) {
        super(itemView);
        this.clickListener = listener;
        tvName = itemView.findViewById(R.id.tvPersonName);
        imRemove = itemView.findViewById(R.id.ivRemove);
        imRemove.setOnClickListener(this);
    }

    public void setName(String name){
        tvName.setText(name);
    }


    @Override
    public void onClick(View v) {
        clickListener.onPersonClicked(getAdapterPosition());
    }
}

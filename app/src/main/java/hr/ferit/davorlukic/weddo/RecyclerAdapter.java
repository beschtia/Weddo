package hr.ferit.davorlukic.weddo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class RecyclerAdapter extends RecyclerView.Adapter<PersonViewHolder> {

    private List<Person> personList = new ArrayList<>();
    private PersonClickInterface clickListener;

    public RecyclerAdapter(PersonClickInterface clickListener){
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View personView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cell_person, viewGroup,false);
        return new PersonViewHolder(personView, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder personViewHolder, int position) {
        personViewHolder.setName(personList.get(position).getName() + " " + personList.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }

    public void addData(List<Person> data){
        this.personList.clear();
        this.personList.addAll(data);
        notifyDataSetChanged();
    }

    public void addNewItem(Person person, int position) {
        if (position >= 0 && position <= personList.size()){
            personList.add(position, person);
            notifyItemInserted(position);
        }
    }

    public void removeItem(int position){
        if (position >= 0 && position < personList.size()){
            Realm realm = Realm.getDefaultInstance();

            realm.beginTransaction();
            personList.get(position).deleteFromRealm();
            realm.commitTransaction();

            personList.remove(position);
            notifyItemRemoved(position);

        }
    }
}

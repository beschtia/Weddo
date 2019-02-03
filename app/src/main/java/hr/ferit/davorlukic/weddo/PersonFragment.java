package hr.ferit.davorlukic.weddo;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.realm.Realm;
import io.realm.RealmResults;

public class PersonFragment extends Fragment implements PersonClickInterface{

    private RecyclerView recycler;
    private RecyclerAdapter adapter;
    private Button mAddPerson;

    public static PersonFragment newInstance(){
        return new PersonFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_guests,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mAddPerson = view.findViewById(R.id.btnAddPerson);
        recycler = view.findViewById(R.id.recyclerView);
        mAddPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonDialog dialog = new PersonDialog();
                dialog.show(getFragmentManager(), "PersonDialog");
            }
        });

        setupRecycler();
        setupRecyclerData();
    }

    private void setupRecycler() {
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerAdapter(this);
        recycler.setAdapter(adapter);
    }
    private void setupRecyclerData() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Person> persons=realm.where(Person.class).findAll();
        adapter.addData(persons);
    }

    public void onInsert(Person person){
        //setupRecyclerData();
        adapter.addNewItem(person, adapter.getItemCount());
    }

    public void deletePerson(int position){
        adapter.removeItem(position);
    }

    @Override
    public void onPersonClicked(int adapterPosition) {
        deletePerson(adapterPosition);
    }


    @Override
    public void onDetach() {
        super.onDetach();
        mAddPerson = null;
    }


}
